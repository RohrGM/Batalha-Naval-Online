package util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import service.IService;

public final class Service {

	public static service.IService getLocalService() throws MalformedURLException, RemoteException, NotBoundException {
		return (IService) Naming.lookup("rmi://localhost:1099/service");
	}
	
	public static service.IService getRemoteService(String ip, String port) throws MalformedURLException, RemoteException, NotBoundException {
		return (IService) Naming.lookup(String.format("rmi://%s:%s/service", ip, port));
	}
}
