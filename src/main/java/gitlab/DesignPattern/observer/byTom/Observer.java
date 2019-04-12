package gitlab.DesignPattern.observer.byTom;

import java.lang.reflect.Method;

public class Observer {

    public void advice(Event event) {
        System.out.println("pull the trigger...."+event);
    }

    public static void main(String[] args){

        Observer observer = new Observer();
        Method advice=null;
        try {
            advice = Observer.class.getDeclaredMethod("advice", Event.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Subject subject = new Subject();
        subject.addListener(EventType.ON_ADD,observer,advice);
        subject.addListener(EventType.ON_REMOVE,observer,advice);
        subject.addListener(EventType.ON_EDIT,observer,advice);
        subject.addListener(EventType.ON_QUERY,observer,advice);

        subject.add();
        subject.remove();

    }
}
