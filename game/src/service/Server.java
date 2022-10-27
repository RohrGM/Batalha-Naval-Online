package service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interface.ICommunication;
import Interface.IEntity;
import component.EntityManager;
import component.Player;
import component.Room;
import entity.Bullet;
import entity.PlayerAttack;
import entity.PlayerDefend;
import enums.Mode;
import util.Vector2;

public class Server extends UnicastRemoteObject implements ICommunication {

	private static final long serialVersionUID = 3974011185418484023L;
	private Room roomLoby = new Room();
	private Map<String, List<IEntity>> playersEntities = new HashMap<>();

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
	public List<IEntity> updateEntities(IEntity entity, String id) throws RemoteException {
		List<IEntity> newEntities = new ArrayList<>();
		for (String key : this.playersEntities.keySet()) {

			if (key.equals(id)) {
				if (entity != null) {
					List<IEntity> list = this.playersEntities.get(key);
					if(entity.getClass() != PlayerAttack.class && entity.getClass() != PlayerDefend.class && entity.getClass() != Bullet.class ) {
						list.add(entity.clone());
						this.playersEntities.put(key, list);
					}					
				}
			} else {
				newEntities = this.playersEntities.get(key);
				this.playersEntities.put(key, new ArrayList<IEntity>());
			}
		}
		return newEntities;
	}

	@Override
	public List<Player> subscribe(Player player) throws RemoteException {
		return this.roomLoby.subscribe(player);
	}

	public List<Player> selectMode(Mode mode, long id) throws RemoteException {
		List<Player> players = this.roomLoby.selectMode(mode, id);
		if (Player.isReady(players)) {
			for (Player player : players) {
				playersEntities.put(Long.toString(player.getId()), new ArrayList<IEntity>());
			}
		}
		return this.roomLoby.selectMode(mode, id);
	}

	public List<Player> getPlayers() throws RemoteException {
		return this.roomLoby.getPlayers();
	}
}