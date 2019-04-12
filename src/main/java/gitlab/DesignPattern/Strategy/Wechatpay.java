package gitlab.DesignPattern.Strategy;

public class Wechatpay implements Payment {
    @Override
    public PayState pay(Order order) {
        System.out.println("welcome using wechat pay");
        return new PayState(200,"pay success",order.getAmount());
    }
}
