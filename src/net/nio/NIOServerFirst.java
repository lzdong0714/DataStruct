package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class NIOServerFirst {

    private static ArrayList<SocketChannel> channels = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false); // 非阻塞模式
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            System.out.println("启动成功！");
            while (true){

            SocketChannel socketChannel = serverSocketChannel.accept();

            if(socketChannel != null) {
                System.out.println("get new link " + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
            }else {
                Iterator<SocketChannel> iterator = channels.iterator();
                // 用集合轮询，或者map注册，找对应的响应机制，因SocketChannel是异步的
                while (iterator.hasNext()) {
                    SocketChannel ch = iterator.next();
                    try {
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);

                        if(ch.read(requestBuffer) == 0){
                            //
                            continue;
                        }
                        while (ch.isOpen() && ch.read(requestBuffer) != -1) {
                            if (requestBuffer.position() > 0) break;
                        }

                        if (requestBuffer.position() == 0) continue;
                        requestBuffer.flip();// 切换模式
                        byte[] content = new byte[requestBuffer.limit()];
                        requestBuffer.get(content);
                        System.out.println(new String(content));
                        System.out.println("receive data from :" + ch.getRemoteAddress());

                        // HTTP response
                        String response = "HTTP/1.1 200 OK \r\n" +
                                "Content-Length: 11\r\n\r\n" +
                                "Hello World";

                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                        while (buffer.hasRemaining()) {
                            ch.write(buffer);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
