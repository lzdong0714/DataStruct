package think.in.java.chapter21;

// 基本的多线程演示时列
public class MoreBasicThread {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            Thread t = new Thread(new LiftOff());
            t.start();
        }

        System.out.println("============ main finish ===============");
    }
}
