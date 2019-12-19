package think.in.java.chapter18;

import think.in.java.util.Directory;

import java.io.*;

public class TestEOF {
    public static void main(String[] args) throws IOException {
        Directory.TreeInfo treeInfo = Directory.walk(".", "TestEOF.java");
        File file = treeInfo.files.get(0);
        DataInputStream in = new DataInputStream(new BufferedInputStream(
                new FileInputStream(file))
        );

        while (in.available() != 0){
            System.out.println((char)in.readByte());
        }
    }
}
