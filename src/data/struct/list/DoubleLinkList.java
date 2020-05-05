package data.struct.list;


import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * 双向链表
 */

class Node<K, V>{
    public K k;
    public V v;
    public Node next;
    public Node prev;

    public Node(K k, V v){
        this.k = k;
        this.v = v;
        next = null;
        prev = null;
    }

    @Override
    public String toString(){
        return k.toString() + " : " + v.toString();
    }
}

@Slf4j
public class DoubleLinkList<K, V> {



    private int capacity = 0xffff;
    private Node<K, V> head ;
    private Node<K, V> tail ;
    private int size ;

    public DoubleLinkList(){
        head = null;
        tail = head;
        size = 0;
    }

    public DoubleLinkList(int capacity){
        this.capacity = capacity;
        head = null;
        tail = head;
        size = 0;
    }

    /**
     * 从头部添加
     */
    private Node addHead(Node node){
        if(this.head == null){
            this.head = node;
            this.tail = node;
            this.head.next = null;
            this.head.prev = null;

        }else {
            node.next = head;
            head.prev = node;
            head = node;
            head.prev = null;
        }
        size ++;
        return node;
    }

    /**
     * 从尾部添加节点
     */

    private Node addTail(Node node){
        if(this.tail == null){
            this.tail = node;
            this.head = node;
            this.tail.next = null;
            this.tail.prev = null;
        }else {
            tail.next = node;
            node.prev = tail;
            tail = node;
            tail.next = null;
        }
        size ++;
        return node;
    }
    /**
     * 删除任意节点
     */
    public void deleteTail(){
        if(size == 0)
            return ;
        if(size>1){
            tail.prev.next = null;
            tail = tail.prev;
        }else if(size == 1) {
            tail = null;
            head = null;
        }
        size--;
    }

    public void deleteHead(){
        if(size == 0)
            return ;
        if(size == 1){
            head = null;
            tail = null;
        }else {
            head.next.prev = null;
            head = head.next;
        }
        size--;
    }

    public Node remove(Node node){

        //空值默认删除尾部节点
        if(node == null)
            node = tail;

        if(node == tail)
            deleteTail();

        if(node == head)
            deleteHead();
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node;

    }

    // 删除头部节点
    public void pop(){
         deleteHead();
    }

    // 添加
    public void append(Node node){
        addHead(node);
    }

    public void appendHead(Node node){
        addHead(node);
    }


    public void printInLog(){
        Node p = head;
        StringBuilder sb = new StringBuilder();
        while (p != null){
            sb.append(p.toString());
            p = p.next;
            if(p != null)
                sb.append("=>");
        }
        log.info(sb.toString());
    }

    public static void main(String[] args) {
        int capacity = 8;
        DoubleLinkList<Integer, Integer> list =
                new DoubleLinkList<>(capacity);
        Node<Integer,Integer> node[] = new Node[capacity];
        IntStream.range(0,8)
                .forEach(index->{
                    node[index] = new Node<>(index,index);
                    list.append(node[index]);
                });

        list.printInLog();
        System.out.println("==========");
        list.deleteHead();
        list.printInLog();
        System.out.println("==========");
        list.deleteTail();
        list.printInLog();

    }
}
