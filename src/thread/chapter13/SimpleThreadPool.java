package thread.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SimpleThreadPool {

    private final int size;

    private final static int DEFAULT_SIZE = 10;

    private static volatile int seq = 0;

    private final static ThreadGroup GROUP= new ThreadGroup("POOL_Group");

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static String THREAD_PREFIX ="SIMPLE_THREAD_POOL_";

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();


    public SimpleThreadPool(){
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size){
        this.size = size;
        init();
    }

    private void init(){
        for (int i = 0;i < size; i++){
            createWorkTask();
        }
    }

    public  void submit(Runnable runnable){
        synchronized (TASK_QUEUE){
            TASK_QUEUE.addFirst(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private void createWorkTask(){
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    static enum TaskState{
        FREE, RUNNIG, BLOCKED, DEAD
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
                    taskState = TaskState.RUNNIG;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close(){
            this.taskState = TaskState.DEAD;
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool();

        IntStream.rangeClosed(0,40)
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
    }

}
