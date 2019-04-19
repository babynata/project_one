package interview.dynamicproxy;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.apache.zookeeper.Op;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyGenerator {

    public static void main(String[] args){
        ClassWriter classWriter = new ClassWriter(0);
        // class head
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "interview/dynamicproxy/IHelloImpl", null, "java/lang/Object", null);

        //consctructor
        {
            MethodVisitor cons = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            cons.visitCode();
            Label l0 = new Label();
            cons.visitLabel(l0);
            cons.visitVarInsn(Opcodes.ALOAD, 0);
            cons.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            cons.visitInsn(Opcodes.RETURN);
            Label l1 = new Label();
            cons.visitLabel(l1);
            cons.visitLocalVariable("this", "Linterview/dynamicproxy/IHelloImpl;", null, l0, l1, 0);
            cons.visitMaxs(1, 1);
            cons.visitEnd();
        }
        {
            //method
            MethodVisitor method = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "say", "(Ljava/lang/String;)V", null, null);
            method.visitCode();
            Label l0 = new Label();
            method.visitLabel(l0);
            method.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                    "Ljava/io/PrintStream;");
            method.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
            method.visitInsn(Opcodes.DUP);
            method.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            method.visitVarInsn(Opcodes.ALOAD, 1);
            method.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            method.visitLdcInsn(" says hi");
            method.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            method.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            method.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
                    "(Ljava/lang/String;)V",false);
            Label l1 = new Label();
            method.visitLabel(l1);
            method.visitInsn(Opcodes.RETURN);
            Label l2 = new Label();
            method.visitLabel(l2);
            method.visitLocalVariable("name", "Ljava/lang/String;", null, l0, l2, 1);
            method.visitMaxs(3, 2);
            method.visitEnd();
        }
        classWriter.visitEnd();

        byte[] result = classWriter.toByteArray();
        File file = new File("MyNewIHelloImpl.class");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
