package thread.chapter3;

public class DaemonThread2 {
    public static void main(String[] args) {
//        Thread t = new Thread(()->{});
        Thread t = new Thread(()->{
            Thread innerThread = new Thread(()->{
               try {
                   Thread.sleep(100_000);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
            });
            //设定了innerThread在t线程中，t完蛋，innerThread完蛋
            //如果不设定，那么就是个运行个的
            innerThread.setDaemon(true);

            innerThread.start();
            try {
                Thread.sleep(1_000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        t.start();
    }
}
