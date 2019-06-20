package thread.base.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {

    public static void main(String[] args) {

        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1","T2","T3","T4")
        .forEach(name->{
            new Thread(()->{
                try {
                    booleanLock.lock(10);
                    Optional.of(Thread.currentThread().getName()+
                            " has the lock")
                    .ifPresent(System.out::println);
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (Lock.TimeOutException e){
                    Optional.of(Thread.currentThread().getName()+" time out")
                            .ifPresent(System.out::println);
                }finally {
                    booleanLock.unlock();
                }
            },name).start();
        });
    }


    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName()
        +" is working")
        .ifPresent(System.out::println);
        Thread.sleep(10_000);
    }
}
