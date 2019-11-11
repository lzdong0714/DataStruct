package concurrency.chapter1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CountExample {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static long count = 0;

    public static void main(String[] args) {
        final Logger log = LoggerFactory.getLogger("main");
        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);

        for(int index = 0; index < clientTotal; index++){
            service.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception", e);
                }
            });
        }
        service.shutdown();
        log.info("count:{}",count);
    }

    private static void add(){
        count++;
    }

}
