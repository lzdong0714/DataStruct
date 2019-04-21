package data.struct;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>,V> {

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height;

        public Node(K key,V value){
            this.key = key;
            this.value = value ;
            left = null;
            right = null;
            height = 1;
        }
    }

    private  Node root;

    private  int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return  size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //判断二叉树是不是二分搜索树
    public boolean isBST(){

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root ,keys);
        for (int i = 1; i<keys.size(); i++){
            if(keys.get(i-1).compareTo(keys.get(i))>0)
                return false;
        }
        return true;
    }

    private void  inOrder(Node node,ArrayList<K> keys){
        if(node == null)
            return;

        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    //判断是否是一个平衡二叉树
    public boolean isBalanced(Node node){
        if(node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);

        if(Math.abs(balanceFactor)>1)
            return false;
        return isBalanced(node.left)&&isBalanced(node.right);
    }

    private int getHeight(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    private int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left)-getHeight(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2

    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(x.right))+1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4

    private Node leftRoatate(Node y){
        Node x = y.right;
        Node T2 = x.right;

        x.left = y;
        y.right  = T2;

        //更新高度
        y.height = Math.max(getHeight(y.right),getHeight(y.left))+1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;

        return x;
    }

    //添加元素

    public void add(K key,V value){
        root = add(root,key,value);
    }

    private Node add(Node node,K key,V value){

        if(node == null){
            size ++;
            return new Node(key,value);
        }

        if (key.compareTo(node.key)<0)
            node.left = add(node.left,key,value);

        else if (key.compareTo(node.key)>0)
            node.right = add(node.right,key,value);

        else node.value = value;

        //更新height 先跟新底层，然后上一层，底层维护好了平衡，再计算上一层的的高度
        //计算
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right))+1;


        int balanceFactor = getBalanceFactor(node);

        //维护平衡,先左后右，右边的最后底层可能空缺一个，但但是左边一直有节点
        //LL 做字数
        if(balanceFactor > 1 && getBalanceFactor(node.left)>=0)
            return rightRotate(node);
        //RR
        if(balanceFactor< -1 && getBalanceFactor(node.right)>=0)
            return leftRoatate(node);

        //LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) <0){
            node.left = leftRoatate(node.left);
            return rightRotate(node);
        }

        //RL
//        if()

        return node;
    }


}
