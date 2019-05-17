package thread.chapter3;

import java.util.Arrays;

public class CreateThread {
    public static void main(String[] args){
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread();
        //没有复写run()方法，那么执行空{}
        t1.start();
        t2.start();

//        System.out.println(t1.getName());
//        System.out.println(t2.getName());
//
//        System.out.println(t1.getThreadGroup()+" "+t1.getName());
//        System.out.println(Thread.currentThread().getThreadGroup()+" "+t1.getName());

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        Thread[] threads = new Thread[threadGroup.activeCount()];
        //将线程枚举到数组中，用activeCount()获取数组大小，创建线程组数
        // 将线程枚举到线程中？？？
        threadGroup.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);
        //结果Thread[main,5,main]
        //Thread[Monitor Ctrl-Break,5,main] ***辨明这里有一个多出来的监测线程
        //Thread[Thread-0,5,main]
        //Thread[Thread-1,5,]
    }
}
