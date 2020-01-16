package think.in.java.chapter21;

import concurrency.annoations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
class Pair{
    private int x, y;
    public Pair(int x, int y){this.x = x; this.y = y;}

    public Pair(){this(0, 0);}

    public int getX(){return x;}

    public int getY() { return y; }

    public void incrementX(){x ++;}
    public void incrementY(){y ++;}
    public String toString (){
        return "X :" + x + ", Y :" + y;
    }

    public class PairValueNotEqualException extends RuntimeException{
        public PairValueNotEqualException(){
            super("Pair values not equal" + Pair.this);
        }
    }

    public void checkState(){
        if(x != y){
            throw new PairValueNotEqualException();
        }
    }
}

abstract class PairManager{
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();

    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());

    public synchronized  Pair getPair(){
        return new Pair(p.getX(), p.getY());
    }

    protected void store(Pair p){
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        }catch (InterruptedException e){
            //ignore
        }
    }

    // 留给继承类去操作已经初始化了的属性 Pair p
    public abstract void increment();

}


class PairManger_1 extends PairManager{
    @Override//synchronized 不作为关键字区分方法，同理还有个 throw exception
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}


class PairManger_2 extends PairManager{

    @Override
    public void increment() {
        Pair temp;
        synchronized (this){
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}

class PairManipulator implements Runnable{

    private PairManager pm;
    public PairManipulator(PairManager pm){
        this.pm = pm;
    }


    @Override
    public void run() {
        while (true){
            pm.increment();
        }
    }

    public String toString(){
        return "Pair" + pm.getPair() + " CheckCount " + pm.checkCounter.get();
    }
}

class PairChecker implements Runnable{
    private PairManager pm ;
    public PairChecker(PairManager pm){this.pm = pm;}

    @Override
    public void run() {
        while (true){
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }

}


public class CriticalSection {

    static void testApproaches(PairManager pman_1, PairManager pman_2){
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator
                pm_1 = new PairManipulator(pman_1),
                pm_2 = new PairManipulator(pman_2);

        PairChecker
                pc_1 = new PairChecker(pman_1),
                pc_2 = new PairChecker(pman_2);

        exec.execute(pm_1);
        exec.execute(pm_2);
        exec.execute(pc_1);
        exec.execute(pc_2);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException e){
            System.out.println("Sleep interrupted");
        }
        System.out.println("pm_1 :" + pm_1  + "\n" + "pm_2 :" + pm_2);
        System.exit(0);
    }


    public static void main(String[] args) {
        // PairManager 的继承称之为"模板方法"，在PairManger_1，PairManger_2
        //  都只是做了操作，没有生成新的对象，所有操作的都是PairManger中唯一的一个属性Pair p
        // 两个Manger相互都有increment操作，而Pair 中的incrementX，incremnetY都是非线程安全的
        // 所有在交叉检验 checkState的时候，会抛出异常，
        // 但是没有定义线程中的Thread.UncaughtExceptionHandler
        // 所以不会捕捉异常
        testApproaches(new PairManger_1(), new PairManger_2());
    }


}
