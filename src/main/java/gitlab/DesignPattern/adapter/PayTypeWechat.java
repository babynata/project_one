package gitlab.DesignPattern.adapter;

public class PayTypeWechat implements PaytypeInterface{

    @Override
    public void pay(Long amount) {
        System.out.println("pay by wechat.amount:["+amount+"]");
    }
}
