package thread.chapter7;

public class SynchronizeTest {
    /**
     * 用win+R 输入jconsole 可以连接java面板查询线程
     * 用win+R 输入cmd ，输入jps可以查看启动进程
     * 输入jstack 查看线程堆
     *
     */

    private static final Object LOCK = new Object();

    public static void main(String[] args) {

        Runnable runnable=()->{
        synchronized (LOCK){
              try {
                  Thread.sleep(200_000);
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
            }
        };


        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
    }
}
