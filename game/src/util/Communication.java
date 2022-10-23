package util;

import Interface.ICommunication;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Communication {
	public static ICommunication getLocalService() {		
		try {
			return (ICommunication) Naming.lookup("rmi://localhost:1099/service");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public static ICommunication getRemoteService(String ip, String port){
		try {
			return (ICommunication) Naming.lookup(String.format("rmi://%s:%s/service", ip, port));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
