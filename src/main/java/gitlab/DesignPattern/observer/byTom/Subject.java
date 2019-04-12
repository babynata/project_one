package gitlab.DesignPattern.observer.byTom;

public class Subject extends EventListener{

    public void add() {
        System.out.println("invoking add method...");
        trigger(EventType.ON_ADD);
    }

    public void remove() {
        System.out.println("invoking remove method...");
        trigger(EventType.ON_REMOVE);
    }
}
