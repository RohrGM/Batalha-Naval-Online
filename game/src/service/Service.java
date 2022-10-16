package service;
import java.rmi.Remote;
import java.rmi.RemoteException;

import view.Menu;


public interface Service extends Remote{
	
	public String hello(Menu menu) throws RemoteException;

}
