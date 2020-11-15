package net.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
        while (!socketChannel.finishConnect()){
            // 没有链接上，那么一直等待
            Thread.yield();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入");
        //
        String msg = scanner.nextLine();
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        while (byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }

        System.out.println("收到回应");
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);

        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1){
            // 长链接情况下，需要手动判断数据有没有读取结束（这里简单的判断，超过0字节就认为请求结束了）
            if(requestBuffer.position() > 0) break;
        }

        requestBuffer.flip();
        byte[] content = new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        System.out.println(new String(content));
        scanner.close();
        socketChannel.close();
    }
}
