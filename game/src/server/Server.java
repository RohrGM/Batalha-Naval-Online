package server;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import view.Menu;
import service.Service;

public class Server extends UnicastRemoteObject implements Service{

	private static final long serialVersionUID = 1L;
	
	List<Menu> clients = new ArrayList<>();
	
	public Server() throws RemoteException {
		super();
	}
	
	public void start() {
		try {
			Server server = new Server();
			String local = "//localhost/service";
			try {				
				System.out.println("Server starting...");
				Naming.rebind(local, server);
				System.out.println("Server started");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public String hello(Menu client) throws RemoteException {
		if(!clients.contains(client)) {
			clients.add(client);
		}
		
		return "Hello World";
	}
}