package think.in.java.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {
    private volatile int i = 0;// volatile 只能保证 i 的可见性，不能保证原子性，所有这里添加wuxiao


    // 必须在getValue() 和 increment()方法中都添加 synchronized方法才能保证 i 永远是偶数
    public  int getValue(){return  i; }

    public synchronized void increment(){ i++; i++;}


    @Override
    public void run() {
        while (true){
            increment();
        }
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        service.execute(at);
        while (true){
            int value = at.getValue();
            if(value % 2 != 0){
                System.out.println("value is " + value);
                System.exit(0);
            }
        }
    }
}
