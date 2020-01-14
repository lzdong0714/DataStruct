package think.in.java.a.onjava8.streams;

import java.util.Random;

/**
 * @Author: Liu Zhendong
 * @Description 随意数中创建流
 * @createTime 2020年01月14日 14:52:00
 */
public class Randoms {
    public static void main(String[] args) {
        new Random(47)
                .ints(5, 20)
                .distinct()
                .limit(7)
                .sorted()
                .forEach(System.out::println);
    }
}
