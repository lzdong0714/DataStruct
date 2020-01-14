package think.in.java.a.onjava8.streams;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Liu Zhendong
 * @Description generate 方法构造流
 * @createTime 2020年01月14日 15:19:00
 */
public class Generator implements Supplier<String> {

    Random random = new Random(47);

    char[] chars = "ASJNOAHNKMMXIOF".toCharArray();

    @Override
    public String get() {
        return "" + chars[random.nextInt(chars.length)];
    }

    public static void main(String[] args) {
        String word = Stream.generate(new Generator())
                    .limit(30)
                    .collect(Collectors.joining());
        System.out.println(word);
    }
}
