package net.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class BioClient {

    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8090);
        OutputStream out = socket.getOutputStream();

        Scanner scanner = new Scanner(System.in);
        System.out.println("please input :");
        String msg = scanner.nextLine();
        out.write(msg.getBytes(charset)); // 阻塞的，输入后完成程序
        scanner.close();
        socket.close();
    }
}
