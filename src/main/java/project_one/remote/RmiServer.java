package project_one.remote;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        int port=1099;

        String url="rmi://localhost:1099/project_one.remote.HelloServiceImpl";

        LocateRegistry.createRegistry(port);

        Naming.bind(url,new HelloServiceImpl());

    }
}
