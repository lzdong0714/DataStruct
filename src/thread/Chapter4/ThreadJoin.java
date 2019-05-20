package thread.Chapter4;

import java.util.stream.IntStream;

public class ThreadJoin {
    public static void main(String[] args) {
//        ThreadJoin.example_1();
//        ThreadJoin.example_2();
        ThreadJoin.example_3();
    }

    /**
     * t.join() 后，main()线程会等待t执行完毕
     */
    public static void example_1(){
        Thread t1 = new Thread(()->{
            IntStream.range(1,100).forEach(i->System.out.println(
                    Thread.currentThread().getName()+"->"+i
            ));
        });

        t1.start();
        try {
            //必须在start()之后；
            //会先执行t1,在执行main
            t1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        IntStream.range(1,100).forEach(i->System.out.println(
                Thread.currentThread().getName()+"->"+i));
    }

    /**
     * t1 t2 会交错执行，但是main还是最后，说明join的是main
     */
    public static void example_2(){
        Thread t1 = new Thread(()->{
            IntStream.range(1,100).forEach(i->System.out.println(
                    Thread.currentThread().getName()+"->"+i
            ));
        });

        Thread t2 = new Thread(()->{
            IntStream.range(1,100).forEach(i->System.out.println(
                    Thread.currentThread().getName()+"->"+i
            ));
        });
        t1.start();
        t2.start();
        try {
            //必须在start()之后；
            //会先执行t1,在执行main
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        IntStream.range(1,100).forEach(i->System.out.println(
                Thread.currentThread().getName()+"->"+i));
    }


    /**
     * t1.join(100), main线程等待t1线程100 ms，
     * 如果没结束就继续main线程
     *
     *  所以当前线程等待自己结束就是:
     *  while(true) <-------> Thread.currentThread().join();
     * 用处，main()线程死掉的是时候，要一直运行某线程，则用这个方法。
     *
     */
    public static void example_3(){
        Thread t1 = new Thread(()->{
            try{
                System.out.println("t1 is running");
                Thread.sleep(1000);
                System.out.println("t1 finish running");
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        });

        t1.start();
        try {
            //必须在start()之后；
            //会先执行t1,在执行main
            t1.join(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        IntStream.range(1,100).forEach(i->System.out.println(
                Thread.currentThread().getName()+"->"+i));
    }
}
