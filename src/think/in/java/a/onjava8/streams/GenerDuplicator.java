package think.in.java.a.onjava8.streams;

import java.util.stream.Stream;

/**
 * @Author: Liu Zhendong
 * @Description generator 构造流方法 lambda表达式构造
 * @createTime 2020年01月14日 15:34:00
 */
public class GenerDuplicator {

    /**
     * (parameters) -> expression
     * 或
     * (parameters) ->{ statements;
     * @param args
     */
    public static void main(String[] args) {
        Stream.generate(()->"duplicate")
                .limit(3)
                .forEach(System.out::println);
    }

}
