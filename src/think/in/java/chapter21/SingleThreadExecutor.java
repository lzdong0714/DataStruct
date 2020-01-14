package think.in.java.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

// 单个执行器的演示
public class SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        IntStream.rangeClosed(1, 5).forEach(i -> {
            executor.execute(new LiftOff());
        });
        executor.shutdown();
    }
}