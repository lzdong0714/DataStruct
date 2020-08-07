package demo.base.inter;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年07月16日 13:17:00
 */
public class TryFinial {

    private static int test_1(){

        int b = 0;
        try {
            b = b + 1;
            return b;
        }catch (Exception e){

        }finally {
            System.out.println("in finial block " + b);
            b = b + 1;
            System.out.println("after operation in finial block " + b);
        }
        return b;
    }
    public static void main(String[] args) {
            System.out.println(test_1());
    }
}
