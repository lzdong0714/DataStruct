package thread.base.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BooleanLock implements Lock {
    //The initValue is true indicated the lock have be get
    //The initValue is false indicated the lock have is free(ohter thread cant get this)
    //
    private boolean initValue;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock(){
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue){
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }

        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if(mills<=0){
            lock();
        }

        long hasremain = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue){
            if(hasremain <= 0)
                throw new TimeOutException("Time is out");
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasremain =  endTime-System.currentTimeMillis();
        }

        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if(currentThread == Thread.currentThread()){
            this.initValue = false;
            System.out.println(Thread.currentThread().getName()+
                    " release the lock monitor");
            this.notifyAll();
        }else{
            System.out.println("wrong thread release monitor");
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        //不可以直接返回线程集合，以防止被人修改，
        // 实际因该是返回浅拷贝
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
