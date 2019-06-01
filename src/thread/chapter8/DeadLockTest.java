package thread.chapter8;

public class DeadLockTest {

    public static void main(String[] args) {
        //两个对象相会持有，加载，运行同步代码块出现的死锁状态
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(){
            @Override
            public void run() {
                while (true){
                  deadLock.m1();
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
              while (true){
                  otherService.s2();
              }
            }
        }.start();
    }
}
