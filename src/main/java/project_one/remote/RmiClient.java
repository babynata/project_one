package project_one.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String url="rmi://localhost:1099/project_one.remote.HelloServiceImpl";

        HelloService helloService = (HelloService) Naming.lookup(url);

        String result=helloService.sayHello("Atom");

        System.out.println(result);
    }
}
