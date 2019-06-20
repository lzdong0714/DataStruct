package thread.base.chapter2;

public class BankRunable {
    public static void main(String[] args){
        final TicketWindowRunable
                ticketWindow = new TicketWindowRunable();

        //逻辑与线程控制抽取出来
        Thread windowThread_1 = new Thread(ticketWindow,"window NO 1");
        Thread windowThread_2 = new Thread(ticketWindow,"window NO 2");
        Thread windowThread_3 = new Thread(ticketWindow,"window NO 3");

        windowThread_1.start();
        windowThread_2.start();
        windowThread_3.start();
    }
}
