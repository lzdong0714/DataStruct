package thread.base.chapter9;

import java.util.*;

public class CaptureService {
    private final static LinkedList<Control> CONTROLS = new LinkedList();
    private final static int MAX_WORKER = 5;

    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<>();

        Arrays.asList("M1","M2","M3","M4","M4","M5",
                "M6","M7","M8","M9","M10")
            .stream()
            .map(CaptureService::createThread)
            .forEach(thread -> {
                thread.start();
                worker.add(thread);
            });

        worker.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("All of capture woek is finished")
                .ifPresent(System.out::println);
    }


    public static Thread createThread(String name){
        //public Thread(Runnable target, String name) {
        //        this(null, target, name, 0);


        return new Thread(()->{
            Optional.of("The worker ["+Thread.currentThread().getName()+
                    "] begin capture data" )
            .ifPresent(System.out::println);
            synchronized (CONTROLS){
                while (CONTROLS.size()>MAX_WORKER){
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.add(new Control());
            }

            Optional.of("The worker ["+Thread.currentThread().getName()+"] is working")
                    .ifPresent(System.out::println);

            try {
                //模拟工作耗时
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (CONTROLS){
                Optional.of("The worker ["+Thread.currentThread().getName()+"] finished working ")
                        .ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        },name);
    }

    private static class Control{

    }
}
