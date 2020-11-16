package net.nio;


import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

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
    }

    private ServerSocketChannel serverSocketChannel;

    // 1 创建多个线程 处理accept
    private ReactorThread[] mainReactorThreads = new ReactorThread[1];

    // 2 创建多个线程 处理io （I/O线程）
    private ReactorThread[] subReactorThreads = new ReactorThread[8];


    /**
     *  初始化线程组
     */
    private void newGroup() throws IOException{}

    /**
     * 初始化channel ,并且
     */


    public static void main(String[] args) throws IOException {
        NIOServerMulti serverMulti = new NIOServerMulti();
        serverMulti.newGroup();
        serverMulti.initAndRegister();
        serverMulti.bind();
    }

    private void bind() {

    }

    private void initAndRegister() {

    }
}
