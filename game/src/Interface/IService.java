package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import component.Player;
import component.Room;
import scene.SPrincipal;
import service.ListenerService;

public interface IService extends Remote{

	public String hello(String a) throws RemoteException;

	public void joinRoom(Player player, ListenerService listener) throws RemoteException;

}
