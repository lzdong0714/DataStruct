package net.nio;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NIOServerMulti {

    /**  处理业务操作的线程*/
    private static ExecutorService workPool = Executors.newCachedThreadPool();

    /**
     * 封装 selector.select()等事件轮询的代码
     */
    abstract class ReactorThread extends Thread{
        Selector selector;
        LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue();

        public abstract void handler(SelectableChannel channel)throws Exception;

        private ReactorThread() throws IOException{
            selector = Selector.open();
        }

        volatile  boolean running = false;


        @Override
        public void run() {
            while (running){
                try {
                    Runnable task;
                    while ((task = taskQueue.poll()) != null){
                        task.run();
                    }
                    selector.select(1000);

                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selected.iterator();
                    while (iter.hasNext()){
                        SelectionKey key = iter.next();
                        iter.remove();
                        int readyOps = key.readyOps();
                        while ((readyOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT )) != 0 || readyOps == 0){
                            try {
                                SelectableChannel channel = (SelectableChannel) key.attachment();
                                channel.configureBlocking(false);
                                handler(channel);
                                if(!channel.isOpen()){
                                    key.cancel();// 如果关闭了，就取消这个key的订阅
                                }
                            }catch (Exception ex){
                                key.cancel();
                            }
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        protected void doStart() {
            running = true;
            this.start();
        }

        protected SelectionKey register(ServerSocketChannel socketChannel) throws ClosedChannelException {
            SelectionKey selectionKey = socketChannel.register(selector, 0, serverSocketChannel);

            return selectionKey;
        }

        protected SelectionKey register(SocketChannel socketChannel) throws ClosedChannelException {
            SelectionKey selectionKey = socketChannel.register(selector, 0, socketChannel);
            return selectionKey;
        }
    }

    private ServerSocketChannel serverSocketChannel;

    // 1 创建多个线程 处理accept
    private ReactorThread[] mainReactorThreads = new ReactorThread[1];

    // 2 创建多个线程 处理io （I/O线程）
    private ReactorThread[] subReactorThreads = new ReactorThread[8];


    /**
     *  初始化线程组
     */
    private void newGroup() throws IOException{
        // 创建IO线程，负责处理客户端链接以后的socketChannel的IO读写
        for (int i = 0; i < subReactorThreads.length; i++){

            subReactorThreads[i] = new ReactorThread() {
                @Override
                public void handler(SelectableChannel channel) throws Exception {
                    SocketChannel ch = (SocketChannel) channel;
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    while (ch.isOpen() && ch.read(requestBuffer) != -1){
                        if (requestBuffer.position() > 0) break;
                    }

                    if(requestBuffer.position() == 0) return;
                    requestBuffer.flip();
                    byte[] context = new byte[requestBuffer .limit()];
                    requestBuffer.get(context);
                    log.info(Thread.currentThread().getName() + "收到数据：" + new String(context) + "来自" + ch.getRemoteAddress() );
                    workPool.submit(()->{
                        //TODO 业务,数据库,接口
                    });

                    String response = "HTTP/1.1 200 OK\r\n" +
                            "Content-Length: 11 \r\n\r\n" +
                            "Hello World";
                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    while (buffer.hasRemaining()) {
                        ch.write(buffer);
                    }
                }
            };


        }


        // 创建mainReactor线程，只负责处理serverSocketChannel
        for(int i = 0; i < mainReactorThreads.length; i++){
            mainReactorThreads[i] = new ReactorThread() {
                AtomicInteger incr = new AtomicInteger(0);
                @Override
                public void handler(SelectableChannel channel) throws Exception {
                    // 只做请求分发，不做具体的数据读取
                    ServerSocketChannel ch = (ServerSocketChannel) channel;
                    SocketChannel socketChannel = ch.accept();
                    socketChannel.configureBlocking(false);

                    // 收到连接建立之后分发给IO线程读数据
                    int index = incr.getAndIncrement() % subReactorThreads.length;
                    ReactorThread workEventLoop = subReactorThreads[index];
                    workEventLoop.doStart();
                    SelectionKey selectionKey = workEventLoop.register(socketChannel);
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    System.out.println(Thread.currentThread().getName() + "receive new link from:" + socketChannel.getRemoteAddress());
                }
            };



        }
    }

    /**
     * 初始化channel ,并且
     */


    public static void main(String[] args) throws IOException {
        NIOServerMulti serverMulti = new NIOServerMulti();
        serverMulti.newGroup();
        serverMulti.initAndRegister();
        serverMulti.bind();
    }


    private void bind() throws IOException {
        serverSocketChannel.bind(new InetSocketAddress(8080));
        log.info("start up finished");
    }

    private void initAndRegister() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        int index = new Random().nextInt(mainReactorThreads.length);
        mainReactorThreads[index].doStart();
        SelectionKey selectionKey =  mainReactorThreads[index].register(serverSocketChannel);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
    }
}
