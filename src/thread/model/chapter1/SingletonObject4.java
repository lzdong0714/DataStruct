package thread.model.chapter1;

public class SingletonObject4 {

    private static SingletonObject4 instance;

    private SingletonObject4(){

    }

    //double check 最多有两个线程抢到锁的效率优化
    //但是有空指针异常的问题，在new SingletonObject4()的
    //构造函数中可能由于jvm优化的排序，造成初始化没有构造完全
    //所以instance的信息不完全，会有空指针
    public static SingletonObject4 getInstance(){
        if(null == instance){
            synchronized (SingletonObject4.class){
                if(null == instance){
                    return new SingletonObject4();
                }
            }
        }

        return SingletonObject4.instance;
    }
}
