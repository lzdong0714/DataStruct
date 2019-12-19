package think.in.java.chapter18;

import think.in.java.util.Directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {

    public static String read(String fileName) throws IOException{
        // 借用文件工具找到当前路径下匹配的文件，输出打印
        Directory.TreeInfo walk = Directory.walk(".", fileName);
        File file = walk.files.get(0);
        BufferedReader in = new BufferedReader(new FileReader(file));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine() )!= null){
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        System.out.println(read("BufferedInputFile.java"));
    }
}
