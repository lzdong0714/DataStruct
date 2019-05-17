package thread.chapter2;

public class TicketWindowRunable implements Runnable {

    private final static int MAX = 50;

    private int index = 0;

    public void run() {
        while (index <= MAX){
            System.out.println(Thread.currentThread().getName() +
                    " current number is : "+(index++));
            try {
            Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
