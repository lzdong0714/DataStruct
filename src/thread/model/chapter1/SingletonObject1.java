package thread.model.chapter1;

public class SingletonObject1 {

    /**
     * can't lazy load 就是不能在使用的时候才够在，而是在一开始就构造在静态静态方法中
     * 所以会占用内存？？？
     */
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1(){}

    private static SingletonObject1 getInstance(){
        return instance;
    }
}
