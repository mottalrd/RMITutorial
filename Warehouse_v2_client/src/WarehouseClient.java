import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.naming.NamingException;

public class WarehouseClient {
	public static void main(String[] args) throws NamingException,
			RemoteException, NotBoundException {

		System.setProperty("java.security.policy", "client.policy");
		System.setSecurityManager(new SecurityManager());

		//Here I am assuming that the RMI registry is running on localhost
		//You may want to pass the IP:PORT where the registry is actually located
		Registry registry = LocateRegistry.getRegistry();

		System.out.print("RMI registry bindings: \n");
		String[] remoteObjects = registry.list();

		for (String remoteObject: remoteObjects)
			System.out.println(remoteObject);

		String url = "central_warehouse";
		Warehouse centralWarehouse = (Warehouse) registry.lookup(url);

		// String descr = "Blackwell Toaster";
		// double price = centralWarehouse.getPrice(descr);
		// System.out.println(descr + ": " + price);

		ArrayList<String> l = new ArrayList<String>();
		l.add("toaaaaster");
		Product p = centralWarehouse.getProduct(l);
		System.out.println("Description: " + p.getDescription());
	}
}
