package thread.base.chapter11;

import java.util.Arrays;

public class ThreadGroupAPI {
    public static void main(String[] args) {
        ThreadGroup tg1 =new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10_000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();


        ThreadGroup tg2 = new ThreadGroup("TG2");
        Thread t2 = new Thread(tg2,"T2"){
            @Override
            public void run() {
                System.out.println(">>>" + tg1.getName());
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);

                Arrays.asList(threads).forEach(System.out::println);
            }
        };

        t2.start();
    }
}
