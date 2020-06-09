package my.algs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年05月24日 09:39:00
 */
public class Algs {

    private List<Long> list = new LinkedList<>();


    class ListNode{
        int val;
        ListNode next;

    }
    void traverse(ListNode head){
        if(head.next != null){
            traverse(head.next);
        }else {
            // val -> f(val)
            return;
        }

    }

    class BinaryNode{
        int val;
        BinaryNode left, right;
    }

    /**
     * 当遍历返回有返回值时，便具有了“语义”
     * @param root
     */
    void traverse(BinaryNode root){
        // 前序遍历
        traverse(root.left);
        // 中序遍历
        traverse(root.right);
        // 后续遍历
    }

    class TreeNode{
        int val;
        TreeNode[] children;
    }

    void traverse(TreeNode root){
        for(TreeNode node : root.children){
            traverse(node);
        }
    }

    void test(){
        ReentrantLock lock = new ReentrantLock();
        Collections.synchronizedCollection(new LinkedList<>());

    }


}
