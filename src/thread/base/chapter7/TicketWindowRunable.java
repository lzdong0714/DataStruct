package thread.base.chapter7;

public class TicketWindowRunable implements Runnable {

    private final static int MAX = 500;

    private int index = 1;

    //这里线程不安全的，同一个值会多次读写，不安全
//    public void run() {
//        while (index <= MAX){
//            System.out.println(Thread.currentThread().getName() +
//                    " current number is : "+(index++));
//            try {
//            Thread.sleep(1);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//    }

    //这样是线程不安全的，因为多个线程读写index，
//    public void run(){
//        while (true){
//            if(index > MAX){
//                break;
//            }
//            try {
//                Thread.sleep(5);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//
//            System.out.println(Thread.currentThread().getName()+
//                    " current number is : "+(index++)   );
//
//        }
//    }


    //同步代码块
    private final Object MONITOR = new Object();

    @Override
    public void run() {
        while (true){
            synchronized (MONITOR){
                if (index>MAX)
                    break;

                try {
                    Thread.sleep(5);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+
                        " current number is : "+(index++));
            }
        }
    }


}
