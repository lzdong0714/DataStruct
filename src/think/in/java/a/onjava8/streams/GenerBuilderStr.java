package think.in.java.a.onjava8.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @Author: Liu Zhendong
 * @Description bulider的方式构造Stream
 * @createTime 2020年01月14日 16:05:00
 */
public class GenerBuilderStr {

    Stream.Builder<String> builder = Stream.builder();

    public GenerBuilderStr(String filePath) throws IOException {
        Files.lines(Paths.get(filePath))
                .skip(1)
                .forEach(line ->{
                    for(String w : line.split("[.?,]+")){
                        builder.add(w);
                    }
                });
    }

    Stream<String> stream(){
       return builder.build();
    }


    public static void main(String[] args) throws IOException {
        new GenerBuilderStr("pom.xml")
                .stream()
                .map(w -> w + " ")
                .forEach(System.out::println);
    }
}
