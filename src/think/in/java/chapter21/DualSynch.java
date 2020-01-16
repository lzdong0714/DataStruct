package think.in.java.chapter21;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.stream.IntStream;

public class DualSynch {
    private final Object syncObject = new Object();


    // 默认的锁对象是 this 对象
    public synchronized void f(){
        IntStream.rangeClosed(1, 50).forEach(i ->{
            System.out.println("print");
            Thread.yield();
        });

    }

    public void g(){
        // syncObject 如果换成this那么就会变成同步的f()和g()
        synchronized (syncObject){
            IntStream.rangeClosed(1, 50)
                .forEach(i ->{
                    System.out.println("g()");
                    Thread.yield();
                });
        }
    }


    public static void main(String[] args) {
        DualSynch synch = new DualSynch();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synch.f();
            }
        }).start();
        synch.g();

    }

}
