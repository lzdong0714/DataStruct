package thread.chapter9;

public class ProduceConsumerVersion {

    private int i = 1;

    final private Object LOCK = new Object();


    private void produce(){
        synchronized (LOCK){
            System.out.println("P -> "+(i++));
        }
    }

    private void consume(){
        synchronized (LOCK){
            System.out.println("C -> "+i);
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
