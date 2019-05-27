package thread.chapter5;

public class ThreadCloseGraceful {


    private static class Worker extends Thread{
        private volatile  boolean start = true;
        public Worker(String name){
            super(name);
        }
        @Override
        public void run() {
            while (start){

            }
        }

        public void shutdown(){
            System.out.println("close work");
            this.start = false;
        }
    }

    private static class Worker_2 extends Thread{
        private volatile boolean start=true;
        public Worker_2(String name){
            super(name);
        }
        @Override
        public void run() {
            while (start){
                if(Thread.interrupted()){
                    System.out.println("close work2 ");
                    break;
                }
            }
                //go other process
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker("work");
        worker.start();

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        worker.shutdown();

        Worker_2 worker_2 = new Worker_2("work_2");
        worker_2.start();
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        worker_2.interrupt();

        System.out.println("main finished");
    }
}
