package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import component.Player;
import component.Room;
import scene.SPrincipal;
import service.IService;

public class Server extends UnicastRemoteObject implements IService {

	private static final long serialVersionUID = 3974011185418484023L;

	private Room room = new Room();
	
	List<SPrincipal> clients = new ArrayList<>();

	public Server() throws RemoteException {
		super();
	}

	public void start() {
		try {
			Server server = new Server();
			try {
				System.out.println("Server starting...");
				System.setProperty("java.rmi.server.hostname", "localhost");
				LocateRegistry.createRegistry(1099);
				Naming.rebind("service", server);
				System.out.println("Server started");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String hello(String a) throws RemoteException {
		return "Hello World " + a;
	}

	@Override
	public Room joinRoom(Player player) throws RemoteException {
		System.out.println(room);
		room.addPlayer(player);
		return room;
	}

}