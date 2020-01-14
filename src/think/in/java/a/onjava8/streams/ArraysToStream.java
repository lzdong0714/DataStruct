package think.in.java.a.onjava8.streams;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @Author: Liu Zhendong
 * @Description 将数组转化为stream对象
 * @createTime 2020年01月14日 16:12:00
 */
@Slf4j
public class ArraysToStream {
    Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {

        Arrays.stream(new double[]{3.3, 2.3, 4.4})
                .forEach(n -> n = n+1);

        // 范围限定遵循左闭右开即 [3, 6)
        Arrays.stream(new int[]{1,2,3,4,5,6,7}, 3, 6)
                .forEach(n -> System.out.format("%d ", n));
    }


    private static void demo_1(){
        Arrays.stream(new Operations[]{
                () -> Operations.show("aaa"),
                () -> Operations.show("bbb"),
                () -> Operations.show("ccc"),
                () -> Operations.show("ddd")
        })
        .forEach(Operations::execute);
    }


}

interface Operations{
    void execute();

    static void runOps(Operations... ops){
        for(Operations op : ops){
            op.execute();
        }
    }

     static void show(String msg){
         System.out.println(msg);
     };
}
