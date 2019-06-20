package thread.model.chapter1;

public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2(){}

    public static SingletonObject2 getInstance(){
        //这里多线程执行的时候就有问题了
        if( null == instance){
            return new SingletonObject2();
        }
        return SingletonObject2.instance;
    }
}
