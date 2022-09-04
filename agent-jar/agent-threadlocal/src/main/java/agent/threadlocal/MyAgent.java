package agent.threadlocal;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String arg, Instrumentation instrumentation) {

        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) -> {
            return builder.visit(
                    Advice.to(MyAdvice.class)
                            .on(ElementMatchers.isMethod()
                                    .and(ElementMatchers.any()
                                            .and(ElementMatchers.not(ElementMatchers.named("main")))))
            );
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener.Adapter() {
            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
                System.out.println("onTransformation：" + typeDescription);
            }
        };

        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("demo.app"))
                .transform(transformer)
                .with(listener)
                .installOn(instrumentation);
    }
}
