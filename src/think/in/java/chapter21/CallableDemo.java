package think.in.java.chapter21;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.stream.IntStream;

class TaskWithResult implements Callable<String>{

    private int id ;

    public TaskWithResult(int id ){this.id = id;}

    @Override
    public String call() throws Exception {
        return "Result of TaskWithResult " + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        IntStream
            .rangeClosed(1, 10)
            .forEach(i ->{
                results.add(executors.submit(new TaskWithResult(i)));
            });

        for (Future<String> fs : results)
            try {
                System.out.println(fs.get());
            }catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                executors.shutdown();
            }
    }

}
