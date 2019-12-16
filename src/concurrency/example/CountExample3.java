package concurrency.example;

import concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class CountExample3 {
    public static int threadTotal = 200;

    public static int clientTotal = 5000;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int index = 0;index < clientTotal; index++)
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (InterruptedException e){
                    log.info("exception :{}",e);
                }
                countDownLatch.countDown();
            });

        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }


    private static void add(){
//        count.incrementAndGet();
        count.increment();
    }
}
