package thread.model.chapter5;

import java.util.concurrent.CyclicBarrier;

/**
 * 读写锁
 */
public class ReadWriteLock {


    private int readingReaders = 0;
    private int waitingReaders = 0;
    private int writingWriters = 0;
    private int waitingWriters = 0;


    public synchronized void readUnlock(){
        this.readingReaders --;
        this.notifyAll();
    }

    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders ++;
        try {
            while (writingWriters > 0){
                this.wait();
            }
            this.readingReaders ++;
        }finally {
            this.waitingReaders --;
        }
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters ++; // 线程先等待
        try {
            while(writingWriters > 0 || readingReaders >0){
                this.wait();
            }
            this.writingWriters ++;
        }finally {
            this.waitingWriters--; // 获取进入权后，减少。
        }
    }

    public synchronized void writeUnLock(){
        this.writingWriters --;
        this.notifyAll(); // 唤醒其他线程
    }
    public void nonono(){
        new CyclicBarrier(8);
    }
}
