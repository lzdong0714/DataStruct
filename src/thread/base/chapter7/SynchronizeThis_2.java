package thread.base.chapter7;

public class SynchronizeThis_2 {

    public static void main(String[] args) {
        ThisLock_2 thisLock_2 = new ThisLock_2();
        new Thread("T1"){
            @Override
            public void run() {
                thisLock_2.m1();
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run() {
                thisLock_2.m2();
            }
        }.start();
    }
}

class ThisLock_2{

    private final Object LOCK = new Object();

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2(){
        synchronized (LOCK){
            System.out.println(Thread.currentThread().getName());

            try {
                Thread.sleep(10_1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
