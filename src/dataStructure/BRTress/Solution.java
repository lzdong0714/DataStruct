package dataStructure.BRTress;

import java.util.ArrayList;

public class Solution {

    private class RBTree<K extends Comparable<K>, V> {

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

        //   node                     x
        //  /   \     宸︽棆杞�         /  \
        // T1   x   --------->   node   T3
        //     / \              /   \
        //    T2 T3            T1   T2
        private Node leftRotate(Node node){

            Node x = node.right;

            // 宸︽棆杞�
            node.right = x.left;
            x.left = node;

            x.color = node.color;
            node.color = RED;

            return x;
        }

        //     node                   x
        //    /   \     鍙虫棆杞�       /  \
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
                return new Node(key, value); // 榛樿鎻掑叆绾㈣壊鑺傜偣
            }

            if(key.compareTo(node.key) < 0)
                node.left = add(node.left, key, value);
            else if(key.compareTo(node.key) > 0)
                node.right = add(node.right, key, value);
            else // key.compareTo(node.key) == 0
                node.value = value;

            if (isRed(node.right) && !isRed(node.left))
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

        // 浠庣孩榛戞爲涓垹闄ら敭涓簁ey鐨勮妭鐐�
        public V remove(K key){
            throw new UnsupportedOperationException("No remove in RBTree!");
        }
    }

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        RBTree<String, Object> set = new RBTree<>();
        for(String word: words){
            StringBuilder res = new StringBuilder();
            for(int i = 0 ; i < word.length() ; i ++)
                res.append(codes[word.charAt(i) - 'a']);

            set.add(res.toString(), null);
        }

        return set.getSize();
    }
}
