package thread.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SimpleThreadPool {

    private final int size;

    private final int queueSize;

    private final static int DEFAULT_SIZE = 10;

    private final static int  DEFAULT_TASK_SIZE = 2000;

    private static volatile int seq = 0;

    private final static ThreadGroup GROUP= new ThreadGroup("POOL_Group");

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static String THREAD_PREFIX ="SIMPLE_THREAD_POOL_";

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    private final DiscardPolicy discardPolicy;

    private volatile boolean destroy = false;

    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = ()->{throw new DiscardException("DisCard this task");};


    private static class DiscardException extends RuntimeException {
        public DiscardException(String msg){
            super(msg);
        }

    }

    public SimpleThreadPool(){
        this(DEFAULT_SIZE,DEFAULT_TASK_SIZE,DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int size,int queueSize,DiscardPolicy discardPolicy){
        this. discardPolicy= discardPolicy;
        this.queueSize = queueSize;
        this.size = size;
        init();
    }

    //创建了一定数量的线程数，但是没有限定任务的个数
    private void init(){
        for (int i = 0;i < size; i++){
            createWorkTask();
        }
    }

    public  void submit(Runnable runnable){
        if(destroy){
            throw new IllegalArgumentException("the thread pool had been destroyed ,submit is no allowed");
        }
        synchronized (TASK_QUEUE){
            if(queueSize<TASK_QUEUE.size()){
                discardPolicy.discard();
            }
            TASK_QUEUE.addFirst(runnable);

            TASK_QUEUE.notifyAll();
        }
    }

    private void createWorkTask(){
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        //添加的线程，并运行
        task.start();
        THREAD_QUEUE.add(task);
    }

    public void shutdown() throws InterruptedException{
        while (!TASK_QUEUE.isEmpty()){
            Thread.sleep(50);
        }

        int initVal = THREAD_QUEUE.size();
        while (initVal > 0){
            for(WorkerTask task : THREAD_QUEUE){
                if(task.getTaskState() == TaskState.BLOCKED){
                    task.interrupt();
                    task.close();
                    initVal--;
                }else {
                    Thread.sleep(10);
                }
            }
        }
        destroy = true;
        System.out.println("The thread pool disposed");
    }

    public boolean isDestroy(){
        return this.destroy;
    }

    private enum TaskState{
        FREE, RUNNING, BLOCKED, DEAD
    }

    public interface DiscardPolicy{
         void discard() throws DiscardException;
    }

    private static class WorkerTask extends Thread{

        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group,String name){
            super(group,name);
        }

        public TaskState getTaskState(){
            return this.taskState;
        }

        public void run(){
            OUTER:
            while (this.taskState != TaskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    while (TASK_QUEUE.isEmpty()){
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        }catch (InterruptedException e){
                            break OUTER;
                        }
                    }

                    runnable = TASK_QUEUE.removeFirst();

                }
                if(runnable!=null){
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close(){
            this.taskState = TaskState.DEAD;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //创建默认的线程
        SimpleThreadPool threadPool = new SimpleThreadPool();
//        SimpleThreadPool threadPool = new SimpleThreadPool(6,10,SimpleThreadPool.DEFAULT_DISCARD_POLICY);

        //加入大于线程个数的任务数
        IntStream.rangeClosed(0,400)
                .forEach(item->{
                    threadPool.submit(()->{
                      System.out.println("the current task "+item+" is working:"+
                              Thread.currentThread().getName());
                      try {
                          Thread.sleep(1000);
                      }catch (InterruptedException e){
                          e.printStackTrace();
                      }
                      System.out.println("the current task "+item+" is finished:");

                    });
                });

        Thread.sleep(10000);
        threadPool.shutdown();

    }

}
