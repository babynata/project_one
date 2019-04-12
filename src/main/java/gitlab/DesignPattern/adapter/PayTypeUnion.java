package gitlab.DesignPattern.adapter;

public class PayTypeUnion implements PaytypeInternationalInterface {

    @Override
    public void paypaypay(long amount) {
        System.out.println("international pay tune...");
        System.out.println("pay by union.amount:[" + amount + "]");
    }
}
