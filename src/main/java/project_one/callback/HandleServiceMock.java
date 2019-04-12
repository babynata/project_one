package project_one.callback;

import org.springframework.beans.factory.InitializingBean;

public  abstract class HandleServiceMock implements HandleService{

    public void handle(CallBackService<?> action) {

        action.doBefore();

        for (int i=0;i<10;i++) {
            action.doInMiddle();
        }

        action.doInMiddle();

        action.doAfter();
    }

}
