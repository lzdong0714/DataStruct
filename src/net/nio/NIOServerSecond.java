package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServerSecond {
    public static void main(String[] args) throws IOException {
        // 创建服务器channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        //用Selector ,代替注册粗略
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, serverSocketChannel);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        // 绑定接口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功");

        //
        while (true){
            // select 方法有阻塞效果，直到有事件通知才会有返回
           selector.select();
           Set<SelectionKey> selectionKeys = selector.selectedKeys();
           Iterator<SelectionKey> iterator = selectionKeys.iterator();

           while (iterator.hasNext()){
               // 被封装的查询结果
               SelectionKey key = iterator.next();
               iterator.remove();

               // 事件类别，关注 Read 和 Accept 两个事件
               if(key.isAcceptable()){
                   ServerSocketChannel server = (ServerSocketChannel) key.attachment();
                   SocketChannel clientSocketChannel = server.accept();
                   clientSocketChannel.configureBlocking(false);
                   clientSocketChannel.register(selector, SelectionKey.OP_READ, clientSocketChannel);
                   System.out.println("收到新链接 " + clientSocketChannel.getRemoteAddress());
               }

               if(key.isReadable()){
                   SocketChannel socketChannel = (SocketChannel) key.attachment();
                   try {
                       ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                       while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1){
                           if(requestBuffer.position() > 0) break;
                       }

                       if (requestBuffer.position() == 0) continue;
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
                       while (buffer.hasRemaining()) {
                           socketChannel.write(buffer);
                       }
                   }catch (IOException e){
                       e.printStackTrace();
                   }

               }
           }
        }


    }
}
