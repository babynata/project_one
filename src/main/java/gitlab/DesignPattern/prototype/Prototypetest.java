package gitlab.DesignPattern.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prototypetest implements Cloneable,Serializable {

    private String name;

    private List<String> list = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
//        Prototypetest prototypetest = null;
//        return prototypetest = (Prototypetest) super.clone();
//        prototypetest = (Prototypetest) super.clone();
//        Collections.copy(prototypetest.getList(),this.getList());
//        return prototypetest;
        return deepClone();
    }

    private Object deepClone() {
        Prototypetest prototypetest = null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            prototypetest = (Prototypetest) objectInputStream.readObject();



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return prototypetest;
    }

    public static void main(String[] args){

        Prototypetest prototypetest_1 = new Prototypetest();
        prototypetest_1.setName("Janice");

        List<String> list = new ArrayList<>();
        list.add("1");
        prototypetest_1.setList(list);

        Prototypetest prototypetest_2 = null;

        try {
            prototypetest_2 = (Prototypetest) prototypetest_1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(prototypetest_2.getName());
        System.out.println(prototypetest_2.getList().get(0));
        System.out.println(prototypetest_1.getList().hashCode());
        System.out.println(prototypetest_2.getList().hashCode());
        System.out.println(prototypetest_1==prototypetest_2);
        list.add("2");
        System.out.println(prototypetest_1.getList().get(0));
        System.out.println(prototypetest_2.getList().get(0));
        System.out.println(prototypetest_2.getName());
        prototypetest_1.setName("Atom");
        System.out.println(prototypetest_2.getName());
        System.out.println(prototypetest_1.getList()==prototypetest_2.getList());

        //deepClone

    }
}
