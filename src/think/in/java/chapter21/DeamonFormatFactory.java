package think.in.java.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeamonFormatFactory implements Runnable {

    @Override
    public void run() {
        try {
            while (true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newCachedThreadPool(new DeamonThreadFactory());
        for(int i = 0 ; i < 10; i++){
            executors.execute(new DeamonFormatFactory());
        }
        System.out.println("All daemon thread is start");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
