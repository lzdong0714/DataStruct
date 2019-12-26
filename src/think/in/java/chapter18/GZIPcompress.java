package think.in.java.chapter18;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPcompress {
    private static final String bigFilePathIn = "G:\\BaiduNetdiskDownload\\" +
            "GOT.S07E07.The.Dragon.and.the.Wolf.720p.AMZN.WEB-DL.DDP5.1.H.264-GoT.rar";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("data_1.txt"));
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(new FileInputStream(new File(bigFilePathIn)));

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new GZIPOutputStream(new FileOutputStream("data.gz")));

        System.out.println("writing file");
        int c;
        while ((c = bufferedReader.read()) != -1){
            bufferedOutputStream.write(c);
        }
        bufferedReader.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(new FileInputStream("data.gz"))
        ));

        System.out.println("reading file");
        String s;
         while ((s = reader.readLine()) != null){
            System.out.println(s);
        }



    }
}
