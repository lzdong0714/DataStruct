package think.in.java.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

// 线程池的演示
public class CacheThreadPool {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newCachedThreadPool();
        IntStream.rangeClosed(1, 10).forEach(i ->{
            executors.execute(new LiftOff());
        });
        executors.shutdown();
    }
}
