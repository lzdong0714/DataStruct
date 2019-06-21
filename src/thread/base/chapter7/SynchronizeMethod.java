package thread.base.chapter7;

public class SynchronizeMethod implements Runnable {

    //只读的共享数据
    private final static int MAX = 500;

    //共享数据,(index++)是读写操作，所以有线程问题
    private int index = 1;

    //同步方法
    //用的锁对象是this，所以BankVerison_3
    //只有一线程执行完1 到 500 的累加
//    public synchronized void run(){
//        while (true){
//            if(index > MAX)
//                break;
//
//            try {
//                Thread.sleep(5);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//
//            System.out.println(Thread.currentThread().getName()+
//                    " current number is : "+(index++));
//        }
//    }

    // 对上面方法的改造,将同步范围限定到方法区中？？？
    @Override
    public void run() {
        while (true){
            if (ticket()){
                break;
            }
        }
    }


    public synchronized boolean ticket(){
        if(index > MAX)
            return true;

        try {
            Thread.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+
                    " current number is : "+(index++));
        return false;
    }
}
