package thread.chapter9;

import javax.swing.*;
import java.util.stream.Stream;

public class ProduceConsumerSolution {

    private int i = 1;

    final private Object LOCK = new Object();

    private volatile boolean isProduce = false;


    private void produce(){
        synchronized (LOCK){
            while (isProduce){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+
                    " p-> "+(i++));
            isProduce = true;
            LOCK.notifyAll();
        }
    }

    private void consume(){
        synchronized (LOCK){
            //if -> while 即使LOCK被唤醒，还是会优先判定isProduce
            // 可否理解为isProduce 是内存公共判定标识，
            while (!isProduce){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+
                    " C -> "+i);
            isProduce = false;
            LOCK.notifyAll();
        }
    }

    public static void main(String[] args) {
        ProduceConsumerSolution pcs = new ProduceConsumerSolution();

        Stream.of("PT1","PT2","PT3").forEach(item->{
            new Thread(item){
                public void run(){
                    while (true){
                        pcs.produce();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });


        Stream.of("CT1","CT2","CT3").forEach(item->{
            new Thread(item){
                public void run(){
                    while (true){
                        pcs.consume();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });
    }
}
