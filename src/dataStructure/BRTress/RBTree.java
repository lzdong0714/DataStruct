package dataStructure.BRTress;

import java.util.ArrayList;

public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 鍒ゆ柇鑺傜偣node鐨勯鑹�
    private boolean isRed(Node node){
        if(node == null)
            return BLACK;
        return node.color;
    }

    //   node                    x
    //  /   \     宸︽棆杞�                              /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node){

        Node x = node.right;

        // 宸︽棆杞�
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;//缁存姢榛戦珮

        return x;
    }

    //     node                  x
    //    /   \     鍙虫棆杞�                       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){

        Node x = node.left;

        // 鍙虫棆杞�
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 棰滆壊缈昏浆
    private void flipColors(Node node){

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    // 鍚戠孩榛戞爲涓坊鍔犳柊鐨勫厓绱�(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK; // 鏈�缁堟牴鑺傜偣涓洪粦鑹茶妭鐐�
    }

    // 鍚戜互node涓烘牴鐨勭孩榛戞爲涓彃鍏ュ厓绱�(key, value)锛岄�掑綊绠楁硶
    // 杩斿洖鎻掑叆鏂拌妭鐐瑰悗绾㈤粦鏍戠殑鏍�
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value); // 榛樿鎻掑叆绾㈣壊鑺傜偣锛屼篃鏄负浜嗙淮鎶ら粦楂�
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;
        // case4鎬庝箞鎼烇紵
        if (isRed(node.right) && !isRed(node.left))//杩欎笁涓猧f鐨勯『搴忎笉鑳芥崲銆�
            node = leftRotate(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    // 杩斿洖浠ode涓烘牴鑺傜偣鐨勪簩鍒嗘悳绱㈡爲涓紝key鎵�鍦ㄧ殑鑺傜偣
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 杩斿洖浠ode涓烘牴鐨勪簩鍒嗘悳绱㈡爲鐨勬渶灏忓�兼墍鍦ㄧ殑鑺傜偣
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 鍒犻櫎鎺変互node涓烘牴鐨勪簩鍒嗘悳绱㈡爲涓殑鏈�灏忚妭鐐�
    // 杩斿洖鍒犻櫎鑺傜偣鍚庢柊鐨勪簩鍒嗘悳绱㈡爲鐨勬牴
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 浠庝簩鍒嗘悳绱㈡爲涓垹闄ら敭涓簁ey鐨勮妭鐐�
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 寰呭垹闄よ妭鐐瑰乏瀛愭爲涓虹┖鐨勬儏鍐�
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 寰呭垹闄よ妭鐐瑰彸瀛愭爲涓虹┖鐨勬儏鍐�
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 寰呭垹闄よ妭鐐瑰乏鍙冲瓙鏍戝潎涓嶄负绌虹殑鎯呭喌

            // 鎵惧埌姣斿緟鍒犻櫎鑺傜偣澶х殑鏈�灏忚妭鐐�, 鍗冲緟鍒犻櫎鑺傜偣鍙冲瓙鏍戠殑鏈�灏忚妭鐐�
            // 鐢ㄨ繖涓妭鐐归《鏇垮緟鍒犻櫎鑺傜偣鐨勪綅缃�
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
