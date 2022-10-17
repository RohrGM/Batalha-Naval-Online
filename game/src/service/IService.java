package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import component.Player;
import component.Room;
import scene.SPrincipal;

public interface IService extends Remote{

	public String hello(String a) throws RemoteException;

	public Room joinRoom(Player player) throws RemoteException;

}
