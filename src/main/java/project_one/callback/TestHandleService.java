package project_one.callback;

public class TestHandleService {

    private HandleService handleService = new HandleServiceImpl();

    private int a=1;

    public void test() {
        handleService.handle(new CallBackService<Object>() {
            public Object doBefore() {
                System.out.println("do in before");
                a++;
                return null;
            }

            public Object doInMiddle() {
                System.out.println("do in middle");
                return null;
            }

            public Object doAfter() {
                System.out.println("do in after");
                return null;
            }
        });
    }

    public static void main(String[] args){
        TestHandleService a = new TestHandleService();
        a.test();
    }
}
