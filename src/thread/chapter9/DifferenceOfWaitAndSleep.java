package thread.chapter9;

import java.util.stream.Stream;

public class DifferenceOfWaitAndSleep {

    //这里只有这一个锁
    private static final Object LOCK = new Object();


    public static void main(String[] args) {
        Stream.of("T1","T2").forEach(name->
            new Thread(name){
                @Override
                public void run() {
//                    DifferenceOfWaitAndSleep.m1();
                    DifferenceOfWaitAndSleep.m2();
                }
            }.start()
        );

    }

    public static void m1(){
        synchronized (LOCK){
            try {
                System.out.println("enter "+Thread.currentThread().getName()+" thread");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2() {
        synchronized (LOCK) {
            try {
                System.out.println("enter " + Thread.currentThread().getName() + " thread");
                //这里的获取LOCK后，sleep的不会释放锁，T2直到5秒后才执行进入
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
