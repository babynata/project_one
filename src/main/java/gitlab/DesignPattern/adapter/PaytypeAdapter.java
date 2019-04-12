package gitlab.DesignPattern.adapter;

public class PaytypeAdapter implements PaytypeInterface{

    private PaytypeInternationalInterface paytypeInternationalInterface;

    public PaytypeAdapter(PaytypeInternationalInterface paytypeInternationalInterface) {
        this.paytypeInternationalInterface = paytypeInternationalInterface;
    }

    @Override
    public void pay(Long amount) {
        paytypeInternationalInterface.paypaypay(amount);
    }
}
