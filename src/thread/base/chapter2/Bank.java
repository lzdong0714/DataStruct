package thread.base.chapter2;

public class Bank {
    public static void main(String[] args){

        TicketWindow ticketWindow_1 = new TicketWindow("No.1");
        TicketWindow ticketWindow_2 = new TicketWindow("No.2");
        TicketWindow ticketWindow_3 = new TicketWindow("No.3");
        ticketWindow_1.start();
        ticketWindow_2.start();
        ticketWindow_3.start();
    }
}
