package thread.model.chapter4;

import java.util.Arrays;

public class LifeCycleClient {

    public static void main(String[] args) {

        ThreadLifeCycleObserver observer = new ThreadLifeCycleObserver();
        observer.concurrentQuery(Arrays.asList("1","2"));
    }
}
