package project_one.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    protected HelloServiceImpl() throws RemoteException {
    }

    public String sayHello(String name) throws RemoteException {
        return String.format("Hello %s",name);
    }
}
