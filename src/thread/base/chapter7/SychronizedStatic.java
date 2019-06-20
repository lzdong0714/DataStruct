package thread.base.chapter7;

public class SychronizedStatic {

    //静态类共用一个class锁

    /**
     ×如果是静态代码块，那么会初始化的时候
     *调用一次这个静态代块,同过对象锁，会优先于方法锁的执行
     */
    static {
        synchronized (SychronizedStatic.class){
            try {
                System.out.println("static "+ Thread.currentThread().getName());
                Thread.sleep(2_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1(){
        System.out.println(" m1 "+Thread.currentThread().getName());

        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2(){
        System.out.println(" m2 "+Thread.currentThread().getName());

        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3(){

        System.out.println(" m3 "+Thread.currentThread().getName());
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
