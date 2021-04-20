### 垃圾收集器
- Serial算法 几十兆内存
- Parallel 几个G (Parallel Scavenge  + Paraller Old)
- CMS, 几十个G, 没有STW (Stop-The-World), 并发回收
> 三色标记, 错标-incremental update （写屏障) -remark
- G1, 上百G内存， 三色标记，逻辑分代，物理不分代
> 三色标记 +SATB (Snapshot at the beginning)
- ZGC - Shenandoah 4T, 逻辑物理都不分代
> ColoredPointers (颜色指针，着色指针)
- Epsilon - 啥也不干 （调试，确认不用GC参与就能干完活)。


### 调优前的基础概念:
-


### 什么是调优
- 根据需求进行jvm规划和预调优
- 优化运行jvm运行环境(慢，卡顿)
- 解决jvm运行过程中出现的问题，比如OOM (Out Of Memory)

### jvm调优第一步， 了解jvm常用命令行参数
- jvm的命令行参数，参考： https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html

> -开头 标准 所有hotspot都支持, 如java -version
> -X开头 非标准，特定版本hotspot支持特定命令 如-Xms<size> -Xmx<size> (java -X)
> -XX开头, 不稳定， 下个版本可能取消
```
java -version
java -X //可以查看到非标参数
java -XX:+PringFlagsWithComments  // 只有debug版本 能用
java -XX:+PrintFlagsFinal
java -XX:+PrintFlagsInitial
java -XX:+PrintCommandLineFlags
java -XX:+PrintGC
```

### 一个案例理解常用工具
#### 测试代码
```java
public class FullGC_Problem {
    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "xx";
        int age = 1;
        Date birthDate = new Date();

        public void m() {

        }
    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50, new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);

        for (;;) {
            modelFit();
            Thread.sleep(100);
        }
    }

    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach( info -> {
            executor.scheduleWithFixedDelay( () -> {
                info.m();
            }, 2,3,TimeUnit.SECONDS)
        })
    }



    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();
        for(int i = 0; i<100; i++ ) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }
        return taskList;
    }
}
```

top
jps
jstack
jinfo
jstat -gc
jmap

arthas
|_ thread


jvisualvm: java自带