package thread.chapter1;

public class TryConcurrency {
    public static void main(String[]   args){
        new Thread("read thread"){
            @Override
            public void run(){
                readFromDataBase();
            }
        }.start();

        new Thread("write thread"){
            @Override
            public void run() {
                writeDataToFile();

            }
        }.start();
    }
    private static void readFromDataBase(){
        try{
            System.out.println("begin read");
            Thread.sleep(1000 *10L);
            System.out.println("Read data done start handle it");
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("handle data successfully");
    }

    private static void writeDataToFile(){
        try{
            System.out.println("begin write");
            Thread.sleep(1000 *10L);
            System.out.println("write data done start handle it");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("handle write successfully");

    }
}
