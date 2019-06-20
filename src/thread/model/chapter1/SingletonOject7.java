package thread.model.chapter1;

import java.util.stream.IntStream;

//effective java 推荐的单例写法
public class SingletonObject7 {

    //枚举类型：是私有的 finial的
    private SingletonObject7(){

    }


    private enum Singleton{
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton(){
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance(){
            return instance;
        }
    }

    public static SingletonObject7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).forEach(i->new Thread(String.valueOf(i)){
            @Override
            public void run(){
                System.out.println(SingletonObject7.getInstance());
            }
        }.start()
        );
    }
}
