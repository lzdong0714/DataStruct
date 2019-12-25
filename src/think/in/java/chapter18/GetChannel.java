package think.in.java.chapter18;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {

    private static final int BSIZE = 1024;

    private static final String bigFilePath = "G:\\BaiduNetdiskDownload\\" +
            "GOT.S07E07.The.Dragon.and.the.Wolf.720p.AMZN.WEB-DL.DDP5.1.H.264-GoT.rar";

    public static void main(String[] args) throws IOException {
        String file = "data.txt";
        FileChannel fileChannel = new FileOutputStream(file).getChannel();

        fileChannel.write(ByteBuffer.wrap("Some text".getBytes()));
        fileChannel.close();

        fileChannel = new RandomAccessFile(file,"rw").getChannel();
        fileChannel.position(fileChannel.size());
        fileChannel.write(ByteBuffer.wrap("Some more".getBytes()));
        fileChannel.close();

        fileChannel = new FileInputStream(file).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(buffer);
        buffer.flip();// 缓冲器
        while (buffer.hasRemaining()){
            System.out.println((char)buffer.get());
        }


    }
}
