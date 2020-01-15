package think.in.java.chapter21;


class Sleeper extends Thread{
    private int duration;
    public Sleeper(String name, int sleepTime){
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run(){
        try {
            sleep(duration);
        }catch (InterruptedException e){
            System.out.println(getName() + "was interrupted"
                    + "isInterrupted():" + isInterrupted() );
        }
        System.out.println(getName() + " has awakened");
    }

}

class Join extends Thread{
    private Sleeper sleeper;
    public Join(String name, Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run(){
        try {
            sleeper.join(); // sleeper执行完毕了才能
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }

        System.out.println(getName() + "join completed");
    }
}
public class Joining {

    public static void main(String[] args) {
        Sleeper sleeper = new Sleeper("Sleeper", 1500);
        Sleeper grunmpy = new Sleeper("Grumnpy", 1500);
        Join join = new Join("Dopey", sleeper),
             doc = new Join("grunmpy", grunmpy);

        grunmpy.interrupt();

    }
}
