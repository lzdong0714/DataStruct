package think.in.java.a.onjava8.streams;


import java.io.IOException;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @Author: Liu Zhendong
 * @Description 流操作一般用法
 * @createTime 2020年01月15日 10:04:00
 */
public class StreamOperation {
/**
 *  we're only allowed to perform a single operation that consumes a Stream
 *  只允许通过一个流做一个操作，不同于scala/spark中有cache操作，Strean对象的改变后无法在操作
 */
    public static void main(String[] args) throws IOException {
        String file = System.getProperty("user.dir") + "/src/resource/pride-and-prejudice.txt";
        Stream<String> stream = FileToWordsRegexp.stream(file);
        Supplier<Stream<String>> supplier = ()->stream;
        StreamOperation.peekStream(supplier.get());
        System.out.println("================");
        StreamOperation.sortStream(supplier.get());
        System.out.println("================");
        StreamOperation.streamOfFlatMap(supplier.get());

    }


    public static void peekStream(Stream<String> stream){
        stream.limit(5)
                .map(w -> w + " ")
                .peek(System.out::print)
                .map(String::toLowerCase)
                .peek(System.out::print)
                .map(String::toUpperCase)
                .forEach(System.out::print);

    }

    public static void sortStream(Stream<String> stream){
        stream.limit(5)
                .sorted(Comparator.reverseOrder())
                .map(w -> w + " ")
                .forEach(System.out::println);

    }

    /**
     * map(item - > Object) 循环元素item操作映射为Object
     * flatMap(item  -> Stream/Collection) 对循环元素item 操作映射为 Stream集合类型
     * 演示flatMap，当Stream的元素是一个
     */
    public static void streamOfFlatMap(Stream<String> stream){
        stream.limit(5)
                .flatMap(w -> Stream.of(w + " ", "prefix"+w, w+"suffix"))
                .forEach(System.out::println);
    }

}
