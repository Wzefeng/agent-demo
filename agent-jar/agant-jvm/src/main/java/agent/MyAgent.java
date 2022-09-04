package agent;

import agent.jvm.JvmStack;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyAgent {

    public static void premain(String args, Instrumentation instrumentation) {

        System.out.println("=================== JVM Agent ====================");

        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(() -> {
            System.out.println("=================== Memory&GC Info ====================");
            JvmStack.printMemoryInfo();
            JvmStack.printGCInfo();
        }, 0, 3, TimeUnit.SECONDS);
    }
}
