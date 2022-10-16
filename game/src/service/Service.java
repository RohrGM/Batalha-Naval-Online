package service;
import java.rmi.Remote;
import java.rmi.RemoteException;

import scene.Principal;


public interface Service extends Remote{
	
	public String hello(Principal menu) throws RemoteException;

}
