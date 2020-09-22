package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false); // 非阻塞模式
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功！");
        while (true){

            SocketChannel socketChannel = serverSocketChannel.accept();

            if(socketChannel != null){
                System.out.println("get new link " + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                try {
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.isOpen() && socketChannel.read(requestBuffer)!= -1){

                        if(requestBuffer.position() > 0) break;

                    }

                    if(requestBuffer.position() == 0) continue;
                    requestBuffer.flip();// 切换模式
                    byte[] content = new byte[requestBuffer.limit()];
                    requestBuffer.get(content);
                    System.out.println(new String(content));
                    System.out.println("receive data from :" + socketChannel.getRemoteAddress());

                    // HTTP response
                    String response = "HTTP/1.1 200 OK \r\n" +
                            "Content-Length: 11\r\n\r\n" +
                            "Hello World";

                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    while (buffer.hasRemaining()){
                        socketChannel.write(buffer);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}
