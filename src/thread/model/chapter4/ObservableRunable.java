package thread.model.chapter4;

import java.util.LinkedList;
import java.util.List;

public abstract class ObservableRunable implements Runnable {

    final protected List<LifeCycleListener> listernRegister = new LinkedList<>();

    public void regist(final LifeCycleListener lifeCycleListener){
        listernRegister.add(lifeCycleListener);
    }

    public void unregist(LifeCycleListener lifeCycleListener){
        listernRegister.remove(lifeCycleListener);
    }

    public ObservableRunable(LifeCycleListener lifeCycleListener){
        regist(lifeCycleListener);
    }

    protected void notifyChange(RunableEvent event){
        for(LifeCycleListener listener : listernRegister){
            // TODO 这里其实是做一个数据交互，用event 状态做示例，
            // event中包含thread，做什么用呢？ 用于注册当前的RunableThread
            listener.onEvent(event);
        }
    }

    public enum RunableState{
        RUNNING, ERROR, DONE;
    }

    public static class RunableEvent{
        private RunableState state;

        private final Thread thread;

        private final Throwable cause;


        public RunableEvent(RunableState state, Thread thread, Throwable cause){
            this.state = state;
            this.thread = thread;
            this.cause = cause;
            System.out.println(thread.getName());
        }

        public Thread getThread() {
            return thread;
        }

        public RunableState getState() {
            return state;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}
