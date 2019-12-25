package think.in.java.chapter18.bigfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeMapperFiles {

    static int length = 0x8FFFFFF;

    private static final String bigFilePathIn = "G:\\BaiduNetdiskDownload\\" +
            "GOT.S07E07.The.Dragon.and.the.Wolf.720p.AMZN.WEB-DL.DDP5.1.H.264-GoT.rar";

    private static final String fileIn = "dat0.a.txt";

    public static void main(String[] args) throws IOException {
        MappedByteBuffer out = new RandomAccessFile(fileIn, "rw").getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, length);

        for(int i = 0; i < length; i++){
            out.put((byte)'x');
        }

        System.out.println("FINISH WRITING");
        for(int i = length/2; i < length/2 +6; i++){
            System.out.println((char)out.get(i));
        }
    }
}
