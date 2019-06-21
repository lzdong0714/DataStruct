package thread.model.chapter1;

public class SingletonObject6 {

    private SingletonObject6(){}

    public static SingletonObject6 getInstance(){
        return InstanceHolder.instance;
    }

    private static class InstanceHolder{
        private final static SingletonObject6 instance= new SingletonObject6();
    }
}
