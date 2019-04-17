package interview.dynamicproxy;

public class IHelloImpl implements IHello {
    @Override
    public void say(String name) {
        System.out.println(name+"says Hi");
    }
}
