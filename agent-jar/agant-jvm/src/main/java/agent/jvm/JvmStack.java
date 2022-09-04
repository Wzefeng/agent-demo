package agent.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Arrays;
import java.util.List;

public class JvmStack {

    private static final long MB = 1024 * 1024;

    public static void printMemoryInfo() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // 打印堆内存信息
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        String memoryInfo = String.format("\ninit: %s\t max: %s\t committed: %s\t used: %s\t use rate: %s\n",
                heapMemoryUsage.getInit() / MB + "MB",
                heapMemoryUsage.getMax() / MB + "MB",
                heapMemoryUsage.getCommitted() / MB + "MB",
                heapMemoryUsage.getUsed() / MB + "MB",
                heapMemoryUsage.getUsed() * 100 / heapMemoryUsage.getCommitted() + "%");
        System.out.println(memoryInfo);

        // 打印非堆内存信息
        MemoryUsage nonHeapMemory = memoryMXBean.getNonHeapMemoryUsage();
        String nonHeapMemoryInfo = String.format("init: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                nonHeapMemory.getInit() / MB + "MB",
                nonHeapMemory.getMax() / MB + "MB",
                nonHeapMemory.getUsed() / MB + "MB",
                nonHeapMemory.getCommitted() / MB + "MB",
                nonHeapMemory.getUsed() * 100 / nonHeapMemory.getCommitted() + "%"
        );
        System.out.println(nonHeapMemoryInfo);
    }

    public static void printGCInfo() {
        List<GarbageCollectorMXBean> gcBeaList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbage : gcBeaList) {
            String info = String.format("name: %s\t count:%s\t took:%s\t pool name:%s",
                    garbage.getName(),
                    garbage.getCollectionCount(),
                    garbage.getCollectionTime(),
                    Arrays.deepToString(garbage.getMemoryPoolNames()));
            System.out.println(info);
        }
    }

}
