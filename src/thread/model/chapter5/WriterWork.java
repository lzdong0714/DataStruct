package thread.model.chapter5;

public class WriterWork extends Thread {

    private ShareData data ;
    public WriterWork(ShareData data){
        this.data = data;
    }

    @Override
    public void run(){
        try {
            while (true){
                char c = 'c';
                data.write(c);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
