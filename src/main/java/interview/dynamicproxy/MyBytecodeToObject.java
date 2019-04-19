package interview.dynamicproxy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

public class MyBytecodeToObject {

    public static void main(String[] args) throws IOException {
        File file = new File(".");
//        System.out.println(file.getCanonicalFile());
//        FileInputStream inputStream = new FileInputStream(file.getCanonicalPath() + "/target/classes/interview/dynamicproxy/IHelloImpl.class");
        FileInputStream inputStream = new FileInputStream(file.getCanonicalPath() + "/MyNewIHelloImpl.class");
        byte[] result = new byte[1024];
        Integer len = inputStream.read(result);

        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.defineMyClass(result, 0, len);
        System.out.println(clazz.getCanonicalName());

        try {
            Object obj = clazz.newInstance();
            clazz.getDeclaredMethod("say", String.class).invoke(obj, "Atom");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
