package agent.monitor;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class MethodInterceptor {

    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();

        try {
            return callable.call();
        } finally {
            long cost = System.currentTimeMillis() - start;
            System.out.println(method.getName() + "方法执行耗时" + cost + "(ms)");
        }
    }
}
