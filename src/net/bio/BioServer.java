package net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class BioServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8090);

        System.out.println("======server socket is success==========");

        while (!serverSocket.isClosed()){
            Socket request = serverSocket.accept(); // 阻塞，可以有多个连接，但是一个一个处理
            System.out.println("receive");
            try {
                // 实际对读取的数据做数据操作
                InputStream inputStream = request.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String msg;
                while ((msg = reader.readLine()) != null){ // 没有数据阻塞
                    if(msg.length() == 0){
                        break;
                    }
                    System.out.println(msg);
                }
                System.out.println("=====receive data from :" + request.toString());
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    request.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

}
