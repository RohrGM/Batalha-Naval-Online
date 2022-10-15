import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements Service{

	private static final long serialVersionUID = 1L;
	
	private int call = 0;
	
	List<Client> clients = new ArrayList<>();
	
	public static void main(String[] args) {
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

	protected Server() throws RemoteException {
		super();
	}

	@Override
	public String hello(Client client) throws RemoteException {
		clients.add(client);
		call += 1;
		return "Hello World " + call;
	}
}