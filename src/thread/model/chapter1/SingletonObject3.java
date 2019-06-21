package thread.model.chapter1;

public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3(){}

    /**
     * can lazy load
     * 解决懒加载，串行化，但是会降低效率
     * @return
     */
    public synchronized static SingletonObject3 getInstance(){
        if( null == instance){
            return new SingletonObject3();
        }
        return SingletonObject3.instance;
    }
}

