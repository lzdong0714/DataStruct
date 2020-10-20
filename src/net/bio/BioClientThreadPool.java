package net.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class BioClientThreadPool {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(100);
    private static Random random = new Random(177777);
//    private static int i = 0;
    private static Charset charset = Charset.forName("UTF-8");
    public static void main(String[] args) throws IOException {

        threadPool.execute(()->{
//            int i = 0;
//            Socket socket = null;
//            try {
//                socket = new Socket("localhost", 8090);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            while (i < 10){
//                i++;
//                try {
//                    OutputStream out = socket.getOutputStream();
//                    if(out == null) continue;
//                    String msg = Thread.currentThread().getName() + "|"+ i + "|" + random.nextInt() + "\n";
//                    out.write(msg.getBytes(charset)); // 阻塞的，输入后完成程序
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            try {
                Socket socket = new Socket("localhost", 8090);

                OutputStream out = socket.getOutputStream();
//                Scanner scanner = new Scanner(System.in);
//                System.out.println("please input :");
//                String msg = scanner.nextLine();
                String msg = Thread.currentThread().getName() ;
                out.write(msg.getBytes(charset)); // 阻塞的，输入后完成程序
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }
}
