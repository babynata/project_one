package project_one.callback;

public class HandleServiceImpl implements HandleService{


    public void handle(CallBackService<?> action) {
        action.doBefore();

        System.out.println("handling...");
        for (int i = 0; i < 10; i++) {
            action.doInMiddle();
        }

        action.doAfter();
    }
}
