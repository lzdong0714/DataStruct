package dataStructure.BRTress;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 鍒ゆ柇璇ヤ簩鍙夋爲鏄惁鏄竴妫典簩鍒嗘悳绱㈡爲
    public boolean isBST(){

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i = 1 ; i < keys.size() ; i ++)
            if(keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys){

        if(node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 鍒ゆ柇璇ヤ簩鍙夋爲鏄惁鏄竴妫靛钩琛′簩鍙夋爲
    public boolean isBalanced(){
        return isBalanced(root);
    }

    // 鍒ゆ柇浠ode涓烘牴鐨勪簩鍙夋爲鏄惁鏄竴妫靛钩琛′簩鍙夋爲锛岄�掑綊绠楁硶
    private boolean isBalanced(Node node){

        if(node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 鑾峰緱鑺傜偣node鐨勯珮搴�
    private int getHeight(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    // 鑾峰緱鑺傜偣node鐨勫钩琛″洜瀛�
    private int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 瀵硅妭鐐箉杩涜鍚戝彸鏃嬭浆鎿嶄綔锛岃繑鍥炴棆杞悗鏂扮殑鏍硅妭鐐箈
    //        y                              x
    //       / \                           /   \
    //      x   T4     鍚戝彸鏃嬭浆 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 鍚戝彸鏃嬭浆杩囩▼
        x.right = y;
        y.left = T3;

        // 鏇存柊height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 瀵硅妭鐐箉杩涜鍚戝乏鏃嬭浆鎿嶄綔锛岃繑鍥炴棆杞悗鏂扮殑鏍硅妭鐐箈
    //    y                             x
    //  /  \                          /   \
    // T1   x      鍚戝乏鏃嬭浆 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 鍚戝乏鏃嬭浆杩囩▼
        x.left = y;
        y.right = T2;

        // 鏇存柊height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 鍚戜簩鍒嗘悳绱㈡爲涓坊鍔犳柊鐨勫厓绱�(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 鍚戜互node涓烘牴鐨勪簩鍒嗘悳绱㈡爲涓彃鍏ュ厓绱�(key, value)锛岄�掑綊绠楁硶
    // 杩斿洖鎻掑叆鏂拌妭鐐瑰悗浜屽垎鎼滅储鏍戠殑鏍�
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // 鏇存柊height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 璁＄畻骞宠　鍥犲瓙
        int balanceFactor = getBalanceFactor(node);

        // 骞宠　缁存姢
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

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

        Node retNode;
        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            // return node;
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            // return node;
            retNode = node;
        }
        else{   // key.compareTo(node.key) == 0

            // 寰呭垹闄よ妭鐐瑰乏瀛愭爲涓虹┖鐨勬儏鍐�
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                // return rightNode;
                retNode = rightNode;
            }

            // 寰呭垹闄よ妭鐐瑰彸瀛愭爲涓虹┖鐨勬儏鍐�
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                // return leftNode;
                retNode = leftNode;
            }

            // 寰呭垹闄よ妭鐐瑰乏鍙冲瓙鏍戝潎涓嶄负绌虹殑鎯呭喌
            else{
                // 鎵惧埌姣斿緟鍒犻櫎鑺傜偣澶х殑鏈�灏忚妭鐐�, 鍗冲緟鍒犻櫎鑺傜偣鍙冲瓙鏍戠殑鏈�灏忚妭鐐�
                // 鐢ㄨ繖涓妭鐐归《鏇垮緟鍒犻櫎鑺傜偣鐨勪綅缃�
                Node successor = minimum(node.right);
                //successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                // return successor;
                retNode = successor;
            }
        }

        if(retNode == null)
            return null;

        // 鏇存柊height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 璁＄畻骞宠　鍥犲瓙
        int balanceFactor = getBalanceFactor(retNode);

        // 骞宠　缁存姢
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());

            for(String word: words){
                map.remove(word);
                if(!map.isBST() || !map.isBalanced())
                    throw new RuntimeException();
            }
        }

        System.out.println();
    }
}
