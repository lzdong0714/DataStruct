package thread.Chapter4;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {

//        example_1();
//        example_2();
        example_3();


    }

    /**
     * 打断
     * @throws InterruptedException
     */
    public static void example_1() throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true){
//                System.out.println(">>"+ Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    System.out.println("当前线程被打断");
                }
            }


        });

        t1.start();
        //main线程sleep，确保t1线程启动
        Thread.sleep(100);
        System.out.println(t1.isInterrupted());
        t1.interrupt();
        //仅仅是打断一次while,收到一次打断信号而非中断，没有退出，因为while(true)死循环
        System.out.println(t1.isInterrupted());
        t1.interrupt();
        System.out.println(t1.isInterrupted());
    }


    public static void example_2(){
        Thread t = new Thread(){
            @Override
            public void run() {
                while (true){

                }
            }
        };

        t.start();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                t.interrupt();
                System.out.println("t had tried interrupting t1");
            }
        };

        t2.start();

        try {
//            /这里体现出join ,wait ,sleep 的区别，JDK文档中
//            说明这 3 个方法调用时均可以被打断，但是join操作的是
//            main线程，所以要对main线程打断，才能有InterruptException
            t.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void example_3(){
        Thread t1 = new Thread(()->{
            while (true){

            }
        });

        t1.start();
        Thread main = Thread.currentThread();
        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            main.interrupt();
            System.out.println("t2 is done the interrupt action");
        });

        t2.start();

        //捕捉中断异常
        try {
            t1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
