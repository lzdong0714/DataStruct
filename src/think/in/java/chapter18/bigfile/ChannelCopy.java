package think.in.java.chapter18.bigfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChannelCopy {
    private static final String bigFilePathIn = "G:\\BaiduNetdiskDownload\\" +
            "GOT.S07E07.The.Dragon.and.the.Wolf.720p.AMZN.WEB-DL.DDP5.1.H.264-GoT.rar";

    private static final String bigFilePathOut = "G:\\BaiduNetdiskDownload\\" +
            "GOT.S07E07.The.Dragon.and.the.Wolf.720p.AMZN.WEB-DL.DDP5.1.H.264-GoT.copy.rar";

    private static final int BSIZE = 2048;

    public static void main(String[] args) throws IOException {
        String fileIn = "data.txt";
        String fileOut = "data_1.txt";
        String startTime = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
        System.out.println("start time:"+startTime);
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        FileChannel in = new FileInputStream(bigFilePathIn).getChannel(),
        out = new FileOutputStream(bigFilePathOut).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);

        while (in.read(buffer) != -1){
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
        String endTime = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
        System.out.println("end time:"+endTime);
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
