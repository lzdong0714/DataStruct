package think.in.java.a.onjava8.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月14日 17:28:00
 */
public class FileToWordsRegexp {

    private String all;

    public FileToWordsRegexp(String filePath) throws Exception{
        all = Files.lines(Paths.get(filePath))
                .skip(1)
                .collect(Collectors.joining(" "));
    }

    // 全部读入到String all中后在处理
    public Stream<String> stream(){
        return Pattern.compile("[ .,?]+").splitAsStream(all);
    }


    // 对每一行进行处理
    public static Stream<String> stream(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .skip(1)
                .flatMap(line -> Pattern.compile("\\w+").splitAsStream(line));

    }


}
