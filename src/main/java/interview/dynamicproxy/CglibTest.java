package interview.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {

    public static void main(String[] args){
        testInterface();
        testImpl();
    }


    public static void testImpl() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(IHelloImpl.class);
        enhancer.setCallback(new IHelloMethodInterceptor());
        IHello iHello = (IHello) enhancer.create();
        iHello.say("Jenny");
    }

    public static void testInterface() {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{IHello.class});
        enhancer.setSuperclass(IHelloImpl.class);
        enhancer.setCallback(new IHelloMethodInterceptor());
        IHello iHello = (IHello) enhancer.create();
        iHello.say("Janice");
    }

    static class IHelloMethodInterceptor implements MethodInterceptor{

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("cglib invoking");
            return methodProxy.invokeSuper(o,objects);
        }
    }
}
