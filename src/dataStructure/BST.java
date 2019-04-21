package dataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST <E extends Comparable<E>>{
	
	private class Node{
		public E e;
		public Node left,right;
		
		public Node(E e)
		{
			this.e=e;
			left=null;
			right=null;
		}
	}
	
	private Node root;
	private int size;
	
	public BST()
	{
		root=null;
		size=0;
	}
	
	public void add(E e) {
//		if(root==null)
//		{
//			root=new Node(e);
//		    size++;
//		}
//		else {
//			add(root,e);
//		}
		root=add(root, e);
	}
	
	/**
	 *返回插入节点后的新二叉树的根
	 **/
	private Node add(Node node,E e) {
		//递归的结束条件
//		if(e.equals(node.e))
//		{
//			return ;
//		}else if (e.compareTo(node.e)<0&&node.left==null) {
//			node.left=new Node(e);
//			size++;
//			return;
//		}else if (e.compareTo(node.e)>0&&node.right==null) {
//			node.right=new Node(e);
//			size++;
//			return;
//		}
//		
		if(node==null)
		{
			size++;
			return new Node(e);
		}
		
		//递归调用分支
		if(e.compareTo(node.e)<0)
		{
			node.left=add(node.left, e);
		}else 
		{
			node.right=add(node.right, e);
		}
		return node;
	}
	
	/**
	 * 看二分收索树中是否包含元素e
	 * */
	public boolean contains(E e){
        return contains(root, e);
    }
	
	private boolean contains(Node node,E e)
	{
		if(root==null)
			return false;
		if (e.equals(root.e)) {
			return true;
		} else if (e.compareTo(root.e)<0) {
			return contains(root.left,e);
		}else {
			return contains(root.right,e);
		}
	}

	/**
	 * 二分搜索先序遍历
	 * @return
	 */
	public void preOrder() {
		preOrder(root);
	}
	
	private void preOrder(Node root) {
		if(root==null)
			return ;		
		System.out.println(root.e);		
		preOrder(root.left);
		preOrder(root.right);
	}
	
	public void preOrderNR() {
		Stack<Node> stack=new Stack<>();
		stack.push(root);
		while(!stack.isEmpty())
		{
			Node cur=stack.pop();
			System.out.println(cur.e);
			if (cur.right!=null) {
				stack.push(cur.right);
			}
			if(cur.left!=null)
				stack.push(cur.left);
		}
	}
		
	public void inOrder() {
		inOrder(root);
	}
	
	private void inOrder(Node root) {
		if(root==null)
			return ;
		inOrder(root.left);
		System.out.println(root.e);
		inOrder(root.right);
	}
	
	
	public void postOrder() {
		postOrder(root);
	}
	
	private void postOrder(Node root)
	{
		if(root!=null)
		{
			System.out.println(root.e);
			postOrder(root.right);
			postOrder(root.left);
		}
	}
	
	/**
	 * 二分搜索的层序遍历
	 * 用队列
	 */
	public void levelOrder() {
		Queue<Node> queue=new LinkedList<>();
		
		queue.add(root);
		while(!queue.isEmpty())
		{
			Node cur=queue.remove();
			System.out.println(cur.e);
			if(cur.left!=null)
				queue.add(cur.left);
			if(cur.right!=null)
				queue.add(cur.right);
		}
	}
	
	public int getSize()
	{
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}

	 @Override
	public String toString(){
	        StringBuilder res = new StringBuilder();
	        generateBSTString(root, 0, res);
	        return res.toString();
	    }

	    // 生成以node为根节点，深度为depth的描述二叉树的字符串
	    private void generateBSTString(Node node, int depth, StringBuilder res){

	        if(node == null){
	            res.append(generateDepthString(depth) + "null\n");
	            return;
	        }

	        res.append(generateDepthString(depth) + node.e + "\n");
	        generateBSTString(node.left, depth + 1, res);
	        generateBSTString(node.right, depth + 1, res);
	    }

	    private String generateDepthString(int depth){
	        StringBuilder res = new StringBuilder();
	        for(int i = 0 ; i < depth ; i ++)
	            res.append("--");
	        return res.toString();
	    }

	public E minimum() {
		if(size==0)
			throw new IllegalArgumentException("the tree is empty");	
		return minimum(root).e;
	}
	
	private Node minimum(Node root) {
		if (root.left==null) 
			return root;
		else {
			return minimum(root.left);
		}		
	}
	
	public E maxmum() {
		if(size==0)
			throw new IllegalArgumentException("the tree is empty");	
		return maxmum(root).e;
	}
	
	private Node maxmum(Node root)
	{
		if(root.right==null)
			return root;
		else {
			return maxmum(root.right);
		}
	}

	public E removeMin() {
		E ret=minimum();	
		root=removeMin(root);	
		return ret;
	}
	
	/**
	 * 
	 * @param root 删除以root为根的二分树收索树中的最小节点
	 * @return 返回删除节点后新的二分搜搜树的根
	 */
	private Node removeMin(Node root) {
		if (root.left==null) {
			Node node=root.right;
			root.right=null;
			size--;
			return node;
		}else
		{
			root.left=removeMin(root.left);
			return root;
		}	
	}

	public E removeMax() {
		
		E ret=maxmum();
		root=removeMax(root);
		
		return ret;
	}
	/**
	 * 
	 * @param root
	 * @return
	 */
	private Node removeMax(Node root) {
		if(root.right==null)
		{
			Node node=root.left;
			root.left=null;
			size--;
			return node;
		}else {
			root.right=removeMax(root.right);
			return root;
		}
	}
	
	public void remove(E e) {
		root=remove(root,e);
		
	}
	
	/**
	 * 
	 * @param root
	 * @param e
	 * @return
	 */
	private Node remove(Node root,E e)
	{  
		if(root==null)
			return null;
		
		if(e.compareTo(root.e)<0)
			root.left=remove(root.left, e);
		else if(e.compareTo(root.e)>0)
			root.right=remove(root.right, e);
		
		//其他时候则是找到并相等
		else {
			//左边为空，右边不为空
			if(root.left==null)
			{
				Node node=root.right;
				root.right=null;
				size--;
				return node;
			}else if (root.right==null) {
				Node node=root.left;
				root.left=null;
				size--;
				return node;
			}else {
				Node successor=minimum(root.right);
				successor.right=removeMin(root.right);
				successor.left=root.left;
				root.left=root.right=null;
				return successor;
			}
			
			
		}
		
		return null;
	}
}
