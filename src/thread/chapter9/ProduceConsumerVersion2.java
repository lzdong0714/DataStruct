package thread.chapter9;

import java.util.stream.Stream;

//多消费者和生产者条件下的锁问题
public class ProduceConsumerVersion2 {

    private int i = 1;

    final private Object LOCK = new Object();

    private volatile boolean isProduce = false;


    private void produce(){
        synchronized (LOCK){
            if (isProduce){
                try {
                    //线程进入wait状态
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                //唤醒线程
                LOCK.notify();
                System.out.println(Thread.currentThread().getName()+" P -> "+(i++));
                isProduce = true;

            }
        }
    }

    private void consume(){
        synchronized (LOCK){
            if(!isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                LOCK.notify();
                System.out.println(Thread.currentThread().getName()+" C -> " + i);
                isProduce = false;
            }
        }
    }


    public static void main(String[] args) {
        ProduceConsumerVersion2 pc2 = new ProduceConsumerVersion2();

        Stream.of("T1","T2").forEach(item->{
            new Thread(()->{
                while (true){
                    pc2.produce();
                }

            }).start();

            new Thread(()->{
                while (true){
                    pc2.consume();
                }
            }).start();
        });
        // p1 p2 c1 c2 用的是同一个锁
        //存在 p1 p2 c1 c2 都是wati状态，不同同于之前的死锁，
    }
}
