package gitlab.DesignPattern.adapter;

public class Pay {

    private PaytypeInterface paytypeInterface;

    public Pay(PaytypeInterface paytypeInterface) {
        this.paytypeInterface = paytypeInterface;
    }

    public void pay(Long amount) {
        paytypeInterface.pay(amount);
    }

    public static void main(String[] args){
        PayTypeWechat wechat = new PayTypeWechat();
        Pay pay = new Pay(wechat);
        pay.pay(10000L);

        System.out.println("*****************");
        //by adapter
        /*
        * 因为pay只接入了PaytypeInterface接口，而PaytypeInterface接口不满足一些新的业务逻辑，需要实现新的接口，但又要被pay调用
        * 满足需要调用新接口的场景
        * */
        PaytypeAdapter adapter = new PaytypeAdapter(new PayTypeUnion());
        pay = new Pay(adapter);
        pay.pay(10000L);
    }
}
