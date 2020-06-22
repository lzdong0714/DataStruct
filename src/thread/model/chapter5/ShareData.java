package thread.model.chapter5;

public class ShareData {

    private char[] buffer ;
    private ReadWriteLock lock = new ReadWriteLock();

    public ShareData(int size){
        buffer = new char[size];
        for(int i = 0; i < size; i ++){
            buffer[i] = '*';
        }
    }


    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return doRead();
        }finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            doWrite(c);
        }finally {
            lock.writeUnLock();
        }
    }

    private void doWrite(char c) {
        for(int i = 0; i< buffer.length; i++){
            buffer[i] = c;
            slowly(50);
        }

    }

    private char[] doRead() {
        char[] retChar = new char[buffer.length];
        for(int i = 0; i<buffer.length; i++){
            retChar[i] = buffer[i];
        }
        slowly(50);
        return retChar;
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
