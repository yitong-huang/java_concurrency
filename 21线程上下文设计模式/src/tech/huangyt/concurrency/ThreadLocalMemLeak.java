package tech.huangyt.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadLocalMemLeak {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
        TimeUnit.SECONDS.sleep(30);
        threadLocal.set(new byte[1024 * 1024 * 1024]);
        threadLocal.set(new byte[1024 * 1024 * 1024]);
        threadLocal.set(new byte[1024 * 1024 * 1024]);
        threadLocal = null;
        Thread.currentThread().join();
    }

}
