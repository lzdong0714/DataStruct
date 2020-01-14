package think.in.java.a.onjava8.streams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Liu Zhendong
 * @Description 从集合中创建流
 * @createTime 2020年01月14日 15:03:00
 */
public class CollectionToStream {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>(
                Arrays.asList("It's a wonderful day for pie".split(" ")));

        set.stream().map(x -> x + "?")
                .forEach(System.out::println);
    }
}
