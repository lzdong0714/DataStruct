package think.in.java.chapter18.bigfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferTo {

    private static final String bigFilePathIn = "G:\\BaiduNetdiskDownload\\" +
            "GOT.S07E07.The.Dragon.and.the.Wolf.720p.AMZN.WEB-DL.DDP5.1.H.264-GoT.rar";

    private static final String bigFilePathOut = "G:\\BaiduNetdiskDownload\\" +
            "GOT.S07E07.The.Dragon.and.the.Wolf.720p.AMZN.WEB-DL.DDP5.1.H.264-GoT.copy.rar";
    public static void main(String[] args) throws IOException {
        FileChannel in = new FileInputStream(bigFilePathIn).getChannel(),
                out = new FileOutputStream(bigFilePathOut).getChannel();

        in.transferTo(0, in.size(), out);// 没有OOM?
        out.transferFrom(in, 0, in.size());
    }
}
