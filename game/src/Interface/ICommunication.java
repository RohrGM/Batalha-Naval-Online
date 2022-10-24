package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import component.EntityManager;
import component.Player;
import enums.Mode;

public interface ICommunication extends Remote {

	public List<Player> subscribe(Player player) throws RemoteException;

	public List<Player> selectMode(Mode mode, long id) throws RemoteException;

	public List<Player> getPlayers() throws RemoteException;

	public List<IEntity> updateEntities(IEntity entity, String id) throws RemoteException;
}
