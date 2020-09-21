package net.bio;

import think.in.java.chapter21.CacheThreadPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BioServerThreadPool {
    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8090);
        System.out.println("===== server with threadPool start ======");
        while ( !server.isClosed() ){
            Socket socket = server.accept();
            System.out.println("====get new connection ===");
            threadPool.execute(()->{
                try {
                    String msg;
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    while ((msg = reader.readLine()) != null){
                        if (msg.length() == 0){break;}
                        System.out.println(socket.toString() + msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

    }
}
