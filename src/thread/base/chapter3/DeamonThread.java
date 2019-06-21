package thread.base.chapter3;

public class DeamonThread {
    public static void main(String[] args) {
        Thread t  = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" run");
                    Thread.sleep(1000*10L);
                    System.out.println(Thread.currentThread().getName()+" done");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

//        //------这样就是mian线程先结束，然后t线程自己运行
        t.start();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        //------end------

        //设定了main 为t的守护线程，因main是t的父线程,main死了掉，t死掉
//        t.setDaemon(true);
//        t.start();
//        System.out.println(Thread.currentThread().getName());
        //-----end------
        /**
         *  A<---------长链(会有心跳设置)------------->B
         *  当AB见的连接关掉了，那么就不用再去做心跳监测了，
         *  心跳监测维护，与业务无关
         *   ->daemonThread(health check)
         */
    }
}
