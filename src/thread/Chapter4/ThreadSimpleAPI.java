package thread.Chapter4;

import java.util.Optional;

public class ThreadSimpleAPI {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(1_1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"t1");

        Optional.of(t.getName()).ifPresent(System.out::println);
        //getId,获取实际的线程的id，实际就是JVN按顺序排的序号，
        /**
         * private static synchronized long nextThreadID() {
         *         return ++threadSeqNumber;
         *     }
         */
        Optional.of("第n个线程"+t.getId()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}
