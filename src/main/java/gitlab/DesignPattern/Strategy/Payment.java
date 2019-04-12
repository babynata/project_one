package gitlab.DesignPattern.Strategy;

public interface Payment {

    public PayState pay(Order order);

}
