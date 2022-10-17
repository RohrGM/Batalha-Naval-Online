package util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Interface.IService;

public final class Service {

	public static Interface.IService getLocalService() {		
		try {
			return (IService) Naming.lookup("rmi://localhost:1099/service");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public static Interface.IService getRemoteService(String ip, String port){
		try {
			return (IService) Naming.lookup(String.format("rmi://%s:%s/service", ip, port));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
