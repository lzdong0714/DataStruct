package think.in.java.chapter18.bigfile;

import concurrency.annoations.Recommand;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class Methods {

    private void method_1() throws IOException {
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream("big.txt"), 8192);
        int bytes = -1;
        do {
            byte[] tmpArray = new byte[8192];
            bytes = reader.read(tmpArray);
            if (bytes != -1) {
                //做事情
            }
        } while(bytes > 0);
        reader.close();
    }

    private void method_2() throws IOException {
        FileInputStream fileIn = new FileInputStream("big.txt");
        FileChannel fileChannel = fileIn.getChannel();
        MappedByteBuffer mappedBuf = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        boolean end = false;
        do {
            int limit = mappedBuf.limit();
            int position = mappedBuf.position();
            if (position >= limit) {
                end = true;
            }

            int maxSize = 2048;
            if (limit - position < maxSize) {
                maxSize = limit - position;
            }
            byte[] array = new byte[maxSize];
            mappedBuf.get(array);
            //拿array搞事情
            System.out.println(new String(array));
        } while (!end);
        mappedBuf.clear();
        fileChannel.close();
        fileIn.close();
    }


    private static void method_3() throws IOException{
        String bigFilePathIn = "G:\\BaiduNetdiskDownload\\" +
                "GOT.S07E07.The.Dragon.and.the.Wolf.720p.AMZN.WEB-DL.DDP5.1.H.264-GoT.rar";
        String bigFilePathOut = "G:\\BaiduNetdiskDownload\\" +
                "GOT.S07E07.The.Dragon.and.the.Wolf.720p.AMZN.WEB-DL.DDP5.1.H.264-GoT.copy.rar";
        long start = System.nanoTime();
        FileChannel fcIn = new RandomAccessFile(new File(bigFilePathIn), "rw").getChannel();
        MappedByteBuffer in = fcIn.map(FileChannel.MapMode.READ_ONLY, 0, fcIn.size());
        FileChannel fcOut = new RandomAccessFile(new File(bigFilePathIn), "rw").getChannel();
        MappedByteBuffer out = fcOut.map(FileChannel.MapMode.READ_WRITE, 0, fcOut.size());
        while(fcIn.read(out) != -1){
            out.flip();
            fcIn.write(out);
            out.clear();

        }
        double duration = System.nanoTime() - start;
        System.out.format("% .2f \n", duration/1.0e9);
    }


    public static void main(String[] args) throws IOException {
        method_3();
    }
}
