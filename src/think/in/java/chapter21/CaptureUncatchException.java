package think.in.java.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ExceptionThread extends Thread {
    public void run(){
        Thread t = Thread.currentThread();
        System.out.println("run() by" + t);
        System.out.println(
                "eh = " + t.getUncaughtExceptionHandler()
        );
       throw new RuntimeException();
    }
}

class HandlerThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new MyUncaugthExceptionHandle());

        return t;
    }
}

class MyUncaugthExceptionHandle implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

public class CaptureUncatchException {

    public static void main(String[] args) {
       ExecutorService service =  Executors.newCachedThreadPool(new HandlerThreadFactory());
       service.execute(new ExceptionThread());

    }
}
