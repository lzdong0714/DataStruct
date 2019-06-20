package thread.base.chapter9;

//单个生产与消费下的版本
public class ProduceConsumerVersion {

    private int i = 1;

    final private Object LOCK = new Object();

    private volatile boolean isProduce = false;


    private void produce(){
        synchronized (LOCK){
            if (isProduce){
                try {
                    //线程进入wait状态
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                //唤醒线程
                LOCK.notify();
                System.out.println("P -> "+(i++));
                isProduce = true;

            }
        }
    }

    private void consume(){
        synchronized (LOCK){
            if(!isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                LOCK.notify();
                System.out.println("C -> " + i);
                isProduce = false;
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion pc = new ProduceConsumerVersion();

        new Thread("p"){
            @Override
            public void run() {
                while (true){
                    pc.produce();
                }
            }
        }.start();

        new Thread("c"){
            @Override
            public void run() {
                while (true){
                    pc.consume();
                }
            }
        }.start();
    }
}
