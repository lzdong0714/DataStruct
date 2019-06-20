package thread.base.chapter10;

public class ExitCapture {
    public static void main(String[] args) {
        //捕获runtimeException的钩子程序
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The application will be exited");
            notifyAndRelease();
        }));

        int i = 0;
        while (true){
            try {
                Thread.sleep(1_1000L);
            }catch (Throwable e){
                //ignore
            }
            i++;
            if(i>20) throw new RuntimeException("error");
        }

    }

    private static void notifyAndRelease(){
        System.out.println("notify to the admin");
        try {
            Thread.sleep(1_000);
        }catch (Throwable e){
            //ignore
        }

        System.out.println("will release resource (socket ,file ,connect.)");
        try {
            Thread.sleep(1_000);
        }catch (Throwable e){

        }
        System.out.println("Release and Notify Done");
    }
}
