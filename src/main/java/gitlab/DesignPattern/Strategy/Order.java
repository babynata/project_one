package gitlab.DesignPattern.Strategy;

public class Order {

    private String uid;

    private long amount;

    public Order(String uid, long amount) {
        this.uid = uid;
        this.amount = amount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public PayState pay(PaymentStrategy paymentStrategy) {
        return paymentStrategy.getPayment().pay(this);
    }

    @Override
    public String toString() {
        return "Order{" +
                "uid='" + uid + '\'' +
                ", amount=" + amount +
                '}';
    }
}
