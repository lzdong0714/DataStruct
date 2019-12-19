package think.in.java.chapter18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class MemoryInput {

    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader(
                BufferedInputFile.read("MemoryInput.java")
        );
        int c;
        while ((c = reader.read()) != -1){
            System.out.println(c);
        }
    }
}
