package project_one.guava.optional;

public class HasStatic {

    private static int x = 100;

    static {
        i=0;
//        System.out.println(i);
    }

    static int i=1;

    public static void main(String args[]) {

        HasStatic hs1 = new HasStatic();
        hs1.x++;
        HasStatic hs2 = new HasStatic();
        hs2.x++;
        hs1 = new HasStatic();
        hs1.x++;
        HasStatic.x--;
        System.out.println("x=" + x);

        System.out.println(i);
    }
}
