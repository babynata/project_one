package interview.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyTest {

    public static void main(String[] args){
//        IHello iHello = new IHelloImpl();
        IHello iHello = new IHelloImpl2();
        iHello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), new Class[]{IHello.class}, new JDKProxyInvocationHandler(iHello));
        iHello.say("Jenny");
    }

    static class JDKProxyInvocationHandler implements InvocationHandler {

        private Object targer;

        public JDKProxyInvocationHandler(Object targer) {
            this.targer = targer;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("jdk proxy invoking");
            return method.invoke(targer,args);
        }
    }
}
