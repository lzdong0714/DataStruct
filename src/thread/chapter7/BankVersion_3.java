package thread.chapter7;

public class BankVersion_3 {

    private final static int MAX = 500;

    private int index = 1;

    public static void main(String[] args) {
        SynchronizeMethod ticketWindow = new SynchronizeMethod();

        Thread windowThread_1 = new Thread(ticketWindow,"window NO 1");
        Thread windowThread_2 = new Thread(ticketWindow,"window NO 2");
        Thread windowThread_3 = new Thread(ticketWindow,"window NO 3");

        windowThread_1.start();
        windowThread_2.start();
        windowThread_3.start();
    }
}
