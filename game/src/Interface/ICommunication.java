package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import component.Player;

public interface ICommunication extends Remote {

	public String hello(String a) throws RemoteException;

	public List<Player> subscribe(Player player) throws RemoteException;
}
