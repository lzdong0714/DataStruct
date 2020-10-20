package net.nio;

import java.nio.ByteBuffer;

public class BufferDemo {

    public static void main(String[] args) {
        int size = 4;
        ByteBuffer buffer = ByteBuffer.allocate(size);
        System.out.println(String.format("初始化容量：%s, position 位置:%s, limit限制 %s", buffer.capacity(), buffer.position(),
                buffer.limit()));

        // in written model
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        System.out.println(String.format("写入3字节后: 容量%s, position 位置:%s, limit限制 %s", buffer.capacity(), buffer.position(),
                buffer.limit()));

        System.out.println("###### 开始读取数据 #####");
        buffer.flip(); // 转化为读模式
        byte a = buffer.get();
        System.out.println(a);
        byte b = buffer.get();
        System.out.println(b);
        System.out.println(String.format("读取2字节后: 容量%s, position 位置:%s, limit限制 %s", buffer.capacity(), buffer.position(),
                buffer.limit()));

        buffer.compact();// 清除已阅读的数据， clear(); 清除整个缓冲区
        buffer.put((byte)4);
        buffer.put((byte)5);
        buffer.put((byte)6);

        // 申请堆外内存，避免进入java heap
        // file/socket  --->> OS memory --->>> java heap
        // 可以避免多的拷贝操作， 以及避免GC时，heap 额外保存
        // 以及存在一个 Cleaner 对象，在GC时会自动回收，同GC时的触发Deallocator回收
        ByteBuffer direcByteBuff = ByteBuffer.allocate(size);
    }


}
