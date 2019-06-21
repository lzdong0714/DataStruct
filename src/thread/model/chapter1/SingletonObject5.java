package thread.model.chapter1;

public class SingletonObject5 {
    //volatile 不能保证原子性，但是保证了可见性，happens before
    //保证instance内容中读的部分必须保证先有写过
    private static volatile SingletonObject5 instance;

    private SingletonObject5(){
        //to build constructor
    }

    public static SingletonObject5 getInstance(){
        if(instance == null){
            synchronized (SingletonObject5.class){
                return new SingletonObject5();
            }
        }
        return SingletonObject5.instance;
    }

}
