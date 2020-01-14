package think.in.java.a.onjava8.streams;

import java.util.stream.Stream;

/**
 * @Author: Liu Zhendong
 * @Description 用 iterate构造生成，需要一个
 * @createTime 2020年01月14日 15:49:00
 */
public class GenerIteratorFib {
    int x = 1;

    Stream<Integer> numbers(){
        return Stream.iterate(0, i ->{
            int result = x + i;
            x = i;
            return result; // <==> (result = i)
        });
    }

    public static void main(String[] args) {
        new GenerIteratorFib().numbers()
                .skip(3)
                .limit(5)
                .forEach(System.out::println);
    }
}
