package think.in.java.chapter21;

public class LiftOff  implements Runnable{

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++; // 区分不同的LiftOff
    public LiftOff(){}
    public LiftOff(int countDown){this.countDown = countDown;}

    public String status(){
        return "# " + id + " ("  + (countDown > 0 ? countDown : "Lift Off") + ")";
    }
    @Override
    public void run() {
        while (countDown -- > 0){
            System.out.println(status());
            Thread.yield();
        }
    }
}
