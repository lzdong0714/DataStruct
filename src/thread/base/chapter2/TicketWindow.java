package thread.base.chapter2;

public class TicketWindow extends Thread{

    private final String name;

    // 3 个窗口使用同一个资源,但是读写还是不同步，
    // 而且生命周期在整个JVM运行中，独立于Class外
    private static final int MAX = 50;

    private static int index = 1;

    public TicketWindow(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("window "+ name +
                    " current number is : "+(index++));
        }
    }
}
