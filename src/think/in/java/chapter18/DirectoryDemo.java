package think.in.java.chapter18;

import think.in.java.util.Directory;
import think.in.java.util.PPrint;

import java.util.Arrays;

public class DirectoryDemo {
    public static void main(String[] args) {
        String pformat = PPrint.pformat(Directory.walk(".").files);
        System.out.println(pformat);

        System.out.println("==================");

        String pformat1 = PPrint.pformat(
                Directory.walk(".", "T.*").files
        );
        System.out.println(pformat1);

        System.out.println("==================");


        String pformat2 = PPrint.pformat(
                Directory.walk(".", "D.*\\.java").files
        );
        System.out.println(pformat2);

    }
}
