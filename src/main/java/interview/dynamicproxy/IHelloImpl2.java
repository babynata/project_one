package interview.dynamicproxy;

public class IHelloImpl2 implements IHello{

    @Override
    public void say(String name) {
        System.out.println("no one talks");
    }
}
