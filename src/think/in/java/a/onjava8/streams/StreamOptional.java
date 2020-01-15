package think.in.java.a.onjava8.streams;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月15日 11:25:00
 */
public class StreamOptional {

    public static void main(String[] args) throws IOException {
        String file = System.getProperty("user.dir") + "/src/resource/pride-and-prejudice.txt";
        Stream<String> stream = FileToWordsRegexp.stream(file);
        Supplier<Stream<String>> supplier = ()->stream;
        Optional<Stream<String>> optional = Optional.ofNullable(stream);

        boolean b = optional.isPresent();
        if(b){
            //  to do sth
        }else {
            // NPE
        }
        List<String> ret = new LinkedList<>();
        optional.ifPresent(item ->{
            addList(ret,item);
            System.out.println(item);
        });
        System.out.println("get " + ret.size() + " words to list");

    }


    private static void addList(List<String> list, Stream<String> stream){
        List<String> collect = stream.collect(Collectors.toList());
        list.addAll(collect);
    }

}
