package agent.threadlocal;

import agent.threadlocal.track.TrackContext;
import agent.threadlocal.track.TrackManager;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

public class MyAdvice {

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {

        String linkId = TrackManager.getCurrentSpan();
        if (linkId == null) {
            linkId = UUID.randomUUID().toString();
            TrackContext.setLinkId(linkId);
        }
        String entrySpan = TrackManager.createEntrySpan();

        System.out.printf("[%s] %s.%s\n", entrySpan, className, methodName);
    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        TrackManager.getExitSpan();
    }
}
