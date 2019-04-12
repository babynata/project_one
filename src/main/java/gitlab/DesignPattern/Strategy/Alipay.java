package gitlab.DesignPattern.Strategy;

public class Alipay implements Payment {
    @Override
    public PayState pay(Order order) {
        System.out.println("welcome using alipay");
        return new PayState(200,"pay success",order.getAmount());
    }
}
