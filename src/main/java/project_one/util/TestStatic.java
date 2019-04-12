package project_one.util;

public class TestStatic {

    private static int a=0;

    public static void main(String[] args){
        TestStatic aa=new TestStatic();
        System.out.println(TestStatic.a);
        ++TestStatic.a;
        System.out.println(TestStatic.a);
    }
}
