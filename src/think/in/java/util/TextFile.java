package think.in.java.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.function.Function;

public class TextFile extends ArrayList<String> {

    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    new File(fileName).getAbsoluteFile()
            ));
            try {
                String s ;
                while ((s = in.readLine()) != null){
                    sb.append(s);
                    sb.append("\n");
                }
            }finally {
                in.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    public static void write(String fileName, String text ){
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try{
                out.write(text);
            }finally {
                out.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    public TextFile(String fileName, String splitter){
        super(Arrays.asList(read(fileName).split(splitter)));
        if(get(0).equals("")) remove(0);
    }

    public TextFile(String fileName){
        this(fileName, "\n");
    }

    public void write(String fileName){
        try{
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                for(String item : this){
                    out.write(item);
                }
            }finally {
                out.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        File file = Directory.walk(".", "TextFile.java").files.get(0);
        String fileName = file.getAbsolutePath();
        String fileStr = read(fileName);
        write("test.txt", fileStr);
        TreeSet<String> words = new TreeSet<>(
                new TextFile(fileName, "\\W+")//所有的非字母数字作为分割符，简单的切割出单词
        );
//        System.out.println(words.headSet("a"));

        HashMap<Character, Integer> map = new HashMap<>();
        for (String word : words){
            ByteArrayInputStream in = new ByteArrayInputStream(word.getBytes());
            while (in.available() != 0){
                char c = (char) in.read();
                map.computeIfAbsent(c, k->1);
                map.computeIfPresent(c, (k, v)-> v+1);
            }
        }
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}
