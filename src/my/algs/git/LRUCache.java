package my.algs.git;

import java.util.*;

/**
 * @Author: Liu Zhendong
 * @Description hashMap 和 双向链表的 LRU缓存模型
 * @createTime 2020年06月02日 11:28:00
 */
public class LRUCache {



    private Map<Integer, LinkNode> map;
    private int capactiy;
    private int size = 0 ;
    private DoubleList doubleList;
    public LRUCache(int capactiy){
        this.capactiy = capactiy;
        map = new HashMap<>(capactiy);
        doubleList = new DoubleList(capactiy);
    }
    public LRUCache(){
        this.capactiy = 8;
        map = new HashMap<>(capactiy);
        doubleList = new DoubleList(capactiy);
    }

    // 放入键值对，也就是当前项
    public void put(int key, int val){
        LinkNode item = new LinkNode(key, val);
        if(size < capactiy){
            map.put(key, item);
            doubleList.addFirst(item);
            size ++;
        }else {
            LinkNode last = doubleList.removeLast();
            map.remove(last.key);
            map.put(key,item);
            doubleList.addFirst(item);
        }
    }


    // 取出值
    public int get(int key){
        if( !map.containsKey(key) ){
            return -1;
        }

        LinkNode linkNode = this.map.get(key);

        return linkNode.getVal();
    }

    @Override
    public String toString(){

       return doubleList.toString();

    }


    private class LinkNode{
        int key;
        int val;
        LinkNode next;
        LinkNode prev;
        public LinkNode(int key, int val){
            this.key = key;
            this.val = val;
        }

        public LinkNode(){
            key = 0;
            val = 0;
        }

        public int getVal() {
            return val;
        }
    }

    class DoubleList{
        private int size ;
        private int limitSize;

        public DoubleList(int limitSize){
            this.limitSize = limitSize;
        }

        private LinkNode first;
        private LinkNode last;
        public int size(){
            return size;
        }


        public void addFirst(LinkNode node){
            if(first == null){
                first = node;
                last = node;
                size ++;
                return;
            }

            node.next = first;
            first.prev = node;
            first = node;
            size ++;

            if(size > limitSize){
                removeLast();
            }
        }

        public void remove(LinkNode node){
            if(size == 1){
                last = null;
                first = null;
                size --;
                return;
            }

            if(node == first){
                first.next.prev = null;
                first.next = null;
                size--;
                return;
            }

            if(node == last){
                last.prev.next = null;
                last.prev = null;
                size--;
                return;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public LinkNode removeLast(){
            LinkNode retLast = last;
            remove(last);
            return retLast;
        }

        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            LinkNode node = first;
            while (node != null ){
                if(node.next != null){
                    builder.append( "(" + node.key + "," + node.val + ")" );
                    builder.append(",");
                }else {
                    builder.append( "(" + node.key + "," + node.val + ")" );
                    builder.append("]");
                }
                node = node.next;
            }
            return builder.toString();
        }

    }


    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.toString());
        System.out.println(lruCache.get(3));
        lruCache.put(1,4);
        System.out.println(lruCache);



    }

}
