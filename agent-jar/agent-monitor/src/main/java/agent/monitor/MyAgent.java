package agent.monitor;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String arg, Instrumentation instrumentation) {

        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module)
                -> builder.method(ElementMatchers.any()).intercept(MethodDelegation.to(MethodInterceptor.class));

        AgentBuilder.Listener listener = new AgentBuilder.Listener.Adapter() {
        };

        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("demo.app"))
                .transform(transformer)
                .with(listener)
                .installOn(instrumentation);
    }

}
