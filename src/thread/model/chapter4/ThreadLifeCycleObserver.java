package thread.model.chapter4;

import java.util.List;

public class ThreadLifeCycleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();


    public void concurrentQuery(List<String> ids){

        if(ids == null || ids.isEmpty()) return;

        ids.stream().forEach(id ->{
            new Thread(new ObservableRunable(this) {
                @Override
                public void run() {
                    try {
                        // 这里直接注册ObservableRunable（以及继承类），并且模拟Runable的执行
                        notifyChange(new RunableEvent(RunableState.RUNNING,Thread.currentThread(),null));
                        System.out.println("query for the id " + id);
                        //TODO 实际ObservableRunable 的工作内容 ,耗时1秒
                        Thread.sleep(1000);
                        notifyChange(new RunableEvent(RunableState.DONE, Thread.currentThread(), null));
                    }catch (Exception e){
                        notifyChange(new RunableEvent(RunableState.ERROR,Thread.currentThread(), e));
                    }
                }
            },id).start();
        });

    }

    @Override
    public void onEvent(ObservableRunable.RunableEvent event) {
        synchronized (LOCK){
            System.out.println("the runnable [" + event.getThread().getName() + "] state and data is change");
            if(event.getCause() != null){
                event.getCause().printStackTrace();
            }
        }
    }
}
