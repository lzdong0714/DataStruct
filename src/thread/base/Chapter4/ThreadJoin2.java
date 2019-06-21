package thread.base.Chapter4;

public class ThreadJoin2 {
    //对多个线程的结束信号，也可以
    // t1{ t2.join }那先执行t1，后执行t2
    public static void main(String[] args) throws InterruptedException {
        long startTimeStamp = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunable("x1",1*1000L));
        Thread t2 = new Thread(new CaptureRunable("x2",2*1000L));
        Thread t3 = new Thread(new CaptureRunable("x3",3*1000L));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTimeStamp = System.currentTimeMillis();
        System.out.println("process start at " + startTimeStamp +
                "end at "+endTimeStamp);
    }
}

class CaptureRunable implements Runnable{
    private String mechineName;

    private long spendTime = 1*1000L;

    public CaptureRunable(String mechineName){
        this.mechineName = mechineName;
    }

    public CaptureRunable(String mechineName,long spendTime){
        this.mechineName = mechineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        // pretend to capture data
        try {
            Thread.sleep(spendTime);
            System.out.println(mechineName+"use time "+spendTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public String getResult(){
        return  mechineName +"finished";
    }
}