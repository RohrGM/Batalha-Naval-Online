import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Service extends Remote{
	
	public String hello(Client client) throws RemoteException;

}
