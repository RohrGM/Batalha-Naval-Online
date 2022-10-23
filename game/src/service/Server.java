package service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Interface.ICommunication;
import component.Player;
import component.Room;

public class Server extends UnicastRemoteObject implements ICommunication {

	private static final long serialVersionUID = 3974011185418484023L;
	private Room roomLoby = new Room();

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
	public List<Player> subscribe(Player player) throws RemoteException {
		return roomLoby.subscribe(player);
	}
}