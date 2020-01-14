package think.in.java.a.onjava8.streams;

import java.util.stream.Stream;

/**
 * @Author: Liu Zhendong
 * @Description 用类的静态生成器
 * @createTime 2020年01月14日 15:38:00
 */
public class GenerStaticGenerator {
    public static void main(String[] args) {
        Stream.generate(Bubble::builder)
                .limit(5)
                .forEach(System.out::println);
    }

}

class Bubble{
    final int i;

    public Bubble(int n){ i = n;}

    @Override
    public String toString(){
        return "Bubble(" + i + ")";
    }

    private static int count = 0;

    public static Bubble builder(){
        return new Bubble(count ++ );
    }
}
