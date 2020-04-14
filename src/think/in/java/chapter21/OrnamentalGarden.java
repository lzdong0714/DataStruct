package think.in.java.chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 演示任务的总结，演示volatile的可见性，作为线程标识
 */

class Count{
    private int count = 0;
    private Random random = new Random(53);


    public synchronized int increment(){
        int temp = count;
        if(random.nextBoolean()){
            Thread.yield();
        }
        return (count = ++ temp);
    }

    public synchronized int value(){return count;}
}

class Entrance implements Runnable{
    // 作为资源类，静态公共使用
    private static Count count = new Count();

    private static List<Entrance> entrances =
            new ArrayList<>(8);

    private int number = 0;

    private final int id ;

    private static volatile boolean canceled = false;
    // 原子性操作这里, cancel 是静态的，对所有对象公用，所以保证
    // 可见性，作为退出标识，用volatile ,只保证可见性（写操作，对所有读操作可见）
    // 不保证原子性，原子性有CAS，底层方法。参考Atomic类的使用。
    public static void cancel(){ canceled = true;}

    public Entrance(int id){
        this.id = id;
        entrances.add(this);
    }

    public void run(){
        while ( !canceled ){
            synchronized (this){
                ++ number;
            }
            // 线程执行入口进了一个人，总数添加一个人,所以这里
            // 多个入口并发进入，总的计数器累加，必须线程锁上，
            // Count作为资源类，资源方法increment线程锁定
            System.out.println(this + " Total " + count.increment());

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            }catch (InterruptedException e){
                System.out.println("sleep interrupted");
//                cancel(); // 添加interrupt方法来打断
            }
        }

        System.out.println("stopping " + this);
    }

    public synchronized int getValue(){return number;}

    public String toString(){
        return " Entrance " + id + ":" + getValue();
    }

    // 公共静态方法
    public static int getTotalCount(){
        return count.value();
    }

    public static int sumEntrances(){
        int sum = 0;
        for (Entrance entrance : entrances){
            sum += entrance.getValue();
        }
        return sum;
    }

}



public class OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
//        cancelThreadMethodByFlag(); // 用标识符的方式终止线程执行
        cancelThreadMethodByInterrupt();

    }

   private static void cancelThreadMethodByFlag() throws InterruptedException{
       ExecutorService exec = Executors.newCachedThreadPool();
       IntStream.rangeClosed(0,5)
               .forEach(index ->{
                   exec.execute(new Entrance(index));
               });
       // 主线程等待3秒中
       TimeUnit.SECONDS.sleep(3);
       // 执行线程退出 ，静态公共变量修改状态，while循环退出
       Entrance.cancel();
       exec.shutdown();
       if(!exec.awaitTermination(250, TimeUnit.SECONDS)){
           // 线程池等待255秒中，如果没有结束
           System.out.println("Some task is not terminated");
       }
       System.out.println("Total " + Entrance.getTotalCount());

   }



   private static void cancelThreadMethodByInterrupt() throws InterruptedException {
       ExecutorService exec = Executors.newCachedThreadPool();
       List<Future<?>> submits  = new ArrayList<>(8);
       IntStream.rangeClosed(0, 5).forEach(index -> {
          Future<?> submit  = exec.submit(new Entrance(index));
          submits.add(submit);
       });
       TimeUnit.SECONDS.sleep(3);
       IntStream.rangeClosed(0, 5).forEach(index ->{
           exec.execute(new Runnable() {
               @Override
               public void run() {
                submits.get(index).cancel(true);
               }
           });
       });
       System.out.println("Total " + Entrance.getTotalCount());
       System.out.println("Sum of Entrance " + Entrance.sumEntrances());

   }
}
