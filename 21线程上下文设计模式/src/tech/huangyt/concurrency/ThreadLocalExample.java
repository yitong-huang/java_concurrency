package tech.huangyt.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

public class ThreadLocalExample {
    public static void main(String[] args) {
        // 创建ThreadLocal实例
        ThreadLocal<Integer> tLocal = new ThreadLocal<>();
        // 创建10个线程，使用tLocal
        IntStream.range(0, 10)
                .forEach(i -> new Thread(() -> {
                    try {
                        // 每个线程都会设置tLocal，但是彼此之间的数据是独立的
                        tLocal.set(i);
                        System.out.println(currentThread() + " set i " + tLocal.get());
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(currentThread() + " get i " + tLocal.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start());
    }
}
