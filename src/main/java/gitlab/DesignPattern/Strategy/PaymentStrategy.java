package gitlab.DesignPattern.Strategy;

public enum PaymentStrategy {
    ALIPAY(new Alipay()),
    WECHATPAY(new Wechatpay());

    private Payment payment;

    PaymentStrategy(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public static void main(String[] args){
        Order order = new Order("700008", 6975);
        System.out.println(order.pay(PaymentStrategy.ALIPAY));
    }
}
