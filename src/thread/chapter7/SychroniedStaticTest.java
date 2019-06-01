package thread.chapter7;

public class SychroniedStaticTest {
    public static void main(String[] args) {
        new Thread("T1"){
            @Override
            public void run() {
                SychronizedStatic.m1();
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run() {
                SychronizedStatic.m2();
            }
        }.start();

        //这里的T3由于是非同步态代码块所以是不会去抢Sychronized Static,class锁的
        //会迅速执行
        new Thread("T3"){
            @Override
            public void run() {
                SychronizedStatic.m3();
            }
        }.start();

        System.out.println("main thread is over");
    }

}
