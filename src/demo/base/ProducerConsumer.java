package demo.base;

/**
 * 操作系统教材的 生产者与消费者例子
 * 说明notify 和 wait 的线程间管理
 */
public class ProducerConsumer {
    static final int N = 10;
    static producer p = new producer();
    static consumer c = new consumer();
    static our_monitor mon = new our_monitor();

    static class producer extends Thread{

        @Override
        public void run(){
            int item;
            while (true){
                item = produce_item();
                mon.insert(item);
            }
        }

        private int produce_item(){
            System.out.println("produce a  item : "+ N/2);
            return 2;
        }


    }

    static class consumer extends Thread{

        @Override
        public void run(){
            int item;
            while (true){
                item = mon.remove();
                consume_item(item);
            }
        }

        private void consume_item(int item){
            System.out.println("consume a item：" + item + "  index:  " + N/item);
        }

    }

    static class our_monitor{// 一个管理进程
        private int buffer[] = new int[N];
        private int count = 0, lo = 0, hi = 0;

        public synchronized void insert(int val){
            if(count == N)
                go_to_sleep();
            buffer[hi] = val;
            hi = (hi + 1) % N;
            count = count + 1;
            if(count == 1)
                notify();
        }

        public synchronized int remove(){

            int val;
            if(count == 0)
                go_to_sleep();
            val = buffer[lo];

            lo = (lo + 1) % N;
            count = count - 1;
            if(count == N-1)
                notify();
            return val;
        }

        private void go_to_sleep(){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
    }


    public static void main(String[] args) {
        p.start();
        c.start();
    }

}
