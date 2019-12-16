package concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class AtomicExample6 {
    // CAS后的ABA问题？？？
    AtomicStampedReference<AtomicExample6> stampedReference ;

    private static AtomicBoolean isHappened = new AtomicBoolean(true);
    private static int clientTotal = 5000;

    private static int threadTotal =200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i = 0; i < clientTotal; i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("count: {}",isHappened.get());
    }

    private static void test(){
        if(isHappened.compareAndSet(true, false)){
            log.info("execute");
        }
    }
}
