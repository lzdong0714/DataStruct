package thread.chapter5;

public class ThreadService {

    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run() {
                //runner 是 task的子线程
                Thread runner = new Thread(task);

                //task 作为守护线程，如果task结束，runner也会被结束
                runner.setDaemon(true);
                runner.start();
                try {
                    //先执行runner，完成后再执行task
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
//                     e.printStackTrace();
                }
            }
        };

        //顺序是先创建task线程，然后创建runner子线程，
        //然后由于runner.join task被runner block住了)
        executeThread.start();

    }

    //限制runner执行时间的长度
    public void shutdown(long mills){
        long currentTime = System.currentTimeMillis();
        while (!finished){
            if((System.currentTimeMillis() - currentTime)>mills ){
                System.out.println("任务超时，结束任务");
                //父线程被打断，
                executeThread.interrupt();
                break;
            }

            try{
                executeThread.sleep(1);
            }catch (InterruptedException e){
                System.out.println("执行线程被打断");
                break;
            }

            finished = false;
        }

    }
}
