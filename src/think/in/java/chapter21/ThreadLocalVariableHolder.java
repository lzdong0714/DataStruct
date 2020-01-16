package think.in.java.chapter21;


import concurrency.annoations.NotThreadSafe;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
@NotThreadSafe


class Accessor implements Runnable{
    private final int id;
    public Accessor(int id ){this.id = id;}

    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()){
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    public String toString(){
        return  "#" + id + ": " + ThreadLocalVariableHolder.get();
    }
}



public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>();
    // TODO ThreadLocal的初始化
//    {
//      private Random random = new Random(47);
//      protected synchronized Integer initalValue(){
//          return random.nextInt(10000);
//      }
//    };

    public static void increment(){
        boolean b = Optional.ofNullable(value.get()).isPresent();
        if(b){
            value.set(0);
        }
        value.set(value.get() + 1);
    }


    public static int get(){
        return value.get();
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
//        IntStream.rangeClosed(0, 5).forEach( i -> {
//            exec.execute(new Accessor(i));
//        });
        for(int i = 0; i < 5; i++){
            exec.execute(new Accessor(i));
        }


        TimeUnit.SECONDS.sleep(3);
        exec.shutdown();
    }
}
