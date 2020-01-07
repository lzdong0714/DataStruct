package think.in.java.chapter10;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Singleton {
    private Singleton(){}

    private static class  Holder{
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance(){
        return Holder.instance;
    }

    public int add(int i){return ++i;}
    public int minus(int i){return --i;}
    private int a = 10;
    private int b = 20;
    public int add(){return ++a;}
    public int minus(){return --b;}
    private static String button ;
    public String getButton(){
        return button;
    }
    public void setButton(String setbutton){
        button = setbutton;
    }
    public void turnOn(){
        button = ButtonStatus.ON.state;
    }

    public void turnOff(){
        button = ButtonStatus.OFF.state;
    }
    public enum ButtonStatus{
        OFF("button_off"),
        ON("button_on");
        private String state;
        ButtonStatus(String state){
            this.state = state;
        }
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.add());
        System.out.println(instance.add(2));
        System.out.println(instance.minus(2));
        System.out.println(instance.minus());
        System.out.println(instance.add(10));
        addOut();
        System.out.println(instance.add());
    }

    private static void addOut(){
        Singleton instance = Singleton.getInstance();
        instance.add(10);
        instance.add();
    }

    private static void testShow_1(){
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.getButton());
        instance.turnOff();
        System.out.println(instance.getButton());
        instance.turnOn();
    }
}
