package concurrency.example.atomic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@Data
public class AtomicExample5 {


    private volatile int count = 100;
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater
     = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    private static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {
        AtomicExample5 atomicExample5 = new AtomicExample5();

        if(updater.compareAndSet(example5,100, 120)){
            log.info("update success 1 {}", example5.getCount());
        }

        if(updater.compareAndSet(example5, 100, 120)){
            log.info("update success 2 {}", example5.getCount());
        }else {
            log.info("update failed  {}", example5.getCount());
        }
    }
}
