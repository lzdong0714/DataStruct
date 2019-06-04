package thread.chapter10;

import java.util.Collection;

public interface Lock {

    public  class TimeOutException extends Exception{
        public TimeOutException(String msg){
            super(msg);
        }
    }


    void lock() throws InterruptedException;

    void lock(long mills)throws InterruptedException,TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
