package think.in.java.a.onjava8.onjava;
import static java.util.concurrent.TimeUnit.*;
/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月14日 13:41:00
 */
public class Timer {
    private long start = System.nanoTime();

    public long duration(){
        return NANOSECONDS.toMillis(
                System.nanoTime() - start
        );
    }

    public static long duration(Runnable test){
        Timer timer = new Timer();
        test.run();
        return timer.duration();
    }
}
