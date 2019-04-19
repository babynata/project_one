package interview.reference;


import interview.dynamicproxy.IHelloImpl;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation instP) {
        System.out.println("On premain,agentArgs:[ " + agentArgs + " ]");
        inst = instP;
    }

    public static Long sizeOf(Object object) {
        if (inst == null) {
            throw new IllegalStateException("");
        }
        return inst.getObjectSize(object);
    }

}
