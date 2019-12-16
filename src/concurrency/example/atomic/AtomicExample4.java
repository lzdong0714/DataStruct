package concurrency.example.atomic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Data
public class AtomicExample4 {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    private AtomicReference<String> str = new AtomicReference<>("");


    public static void main(String[] args) {
        count.compareAndSet(0, 1);
        count.compareAndSet(2, 3);
        count.compareAndSet(1, 2);
        log.info("count:{}",count.get());

        AtomicExample4 example4 = new AtomicExample4();
        AtomicReference<String> str = example4.getStr();
        str.compareAndSet("","hello");
        str.compareAndSet("world","fuck");
        str.compareAndSet("fuck","biss");
        log.info("string is {}",str.get());
    }

}
