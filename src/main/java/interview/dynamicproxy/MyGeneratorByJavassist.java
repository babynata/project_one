package interview.dynamicproxy;

import javassist.*;

import java.io.IOException;

public class MyGeneratorByJavassist {

    public static void main(String[] args) throws CannotCompileException, IOException, NotFoundException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("MyNewIHelloImpl2");
        CtMethod ctMethod = CtMethod.make("public void say(String name) {\n" +
                "        System.out.println(name+\"says Hi\");\n" +
                "    }",ctClass);
        ctMethod.insertBefore("System.out.println(\"inserted by javassist...\");");
        ctClass.addMethod(ctMethod);
        ctClass.writeFile();
    }
}
