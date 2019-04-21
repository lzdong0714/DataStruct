package dataStructure;

public class BSTMap<K extends Comparable<K>,V> implements Map<K, V> {

	/**
	 * 
	 * @author lzdong
	 *
	 */
	private class Node
	{
		public K key;
		public V value;
		public Node left,right;//当作指针就好了
		public Node(K key,V value) {
			this.key=key;
			this.value=value;
			this.left=null;
			this.right=null;
		}
		

		@Override
		public String toString() {
			return key.toString()+":"+value.toString();
		}
	}
	
	private Node root;
	private int size;
	public BSTMap() {
		root=null;
		size=0;
	}
	@Override
	public void add(K key, V value) {
		root=add(root, key, value);
	}
	
	/**
	 * 
	 * @param node
	 * @param key
	 * @param value
	 * @return
	 */
	private Node add(Node node,K key,V value) {
		if(node==null)
		{
			size++;
			return new Node(key, value);			
		}		
			if (key.compareTo(node.key)<0) {
				  node.left=add(node.left, key, value);
			} else if (key.compareTo(node.key)>0) {
				 node.right=add(node.right, key, value);
			}else {
				node.value=value;	
				}						
			return node;
	}

	/**
	 * 
	 * @param node 以此为根节点中获取 键对应的节点
	 * @param key 键
	 * @return
	 */
	private Node getNode(Node node,K key) {
		if(node==null)
			return null;
		
		if (key.equals(node.key)) {
			return node;
		}else if (key.compareTo(node.key)<0) {
			return getNode(node.left, key);
		}else   {
			return getNode(node.right, key);
		}
		
//		if(node == null)
//            return null;
//
//        if(key.equals(node.key))
//            return node;
//        else if(key.compareTo(node.key) < 0)
//            return getNode(node.left, key);
//        else // if(key.compareTo(node.key) > 0)
//            return getNode(node.right, key);
		
	}
	
	/**
	 * 
	 */
	@Override
	public V remove(K key) {
		Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
	}
	
	/**
	 * 
	 * @param node
	 * @return 返回最小值所在的节点
	 */
	private Node minimum(Node node) {
		if (node.left==null) {
			return node;
		}
		return minimum(node.left);
	}
	
	/**
	 * 删除以根节点为二分搜索树的最小节点
	 * 返回删除节点的二分树根节点
	 * @param node 
	 * @return
	 */
	private Node removeMin(Node node)
	{
		if (node.left==null) {
			Node rightNode=node.right;
			node.right=null;
			size--;
			return rightNode;
		}
		//返回根节点的要赋值 ，找寻节点的可以直接返回下一步
		node.left=removeMin(node.left);
		return node;		
	}
	
	/**
	 * 返回删除指定节点值的搜索树
	 * @param node
	 * @param key
	 * @return
	 */
	private Node remove(Node node, K key) {
		Node deletNode=getNode(node, key);
		if (deletNode==null) 
		return deletNode;
		if (node.key.compareTo(key)<0) {
			node.left=remove(node.left, key);
			return node;
		}else if (node.key.compareTo(key)>0) {
			node.right=remove(node.right, key);
			return node;
		}else {
			//node.key==key 分情况删除
			if(node.left==null)//左子树为空
			{
				Node nodeRight=node.right;
				node.right=null;
				size--;
				return nodeRight;
			}else if(node.right==null) {//右子树为空
				Node nodeLeft=node.left;
				node.left=null;
				size--;
				return nodeLeft;
			}else {//两边都不为空，删除节点的右枝最小值替换
				Node successor=minimum(node.right);
				successor.right=removeMin(node.right);
				successor.left=node.left;
				node.left=node.right=null;
				return successor;
			}
		}
	}
	
	/**
	 * 判定是包含key值
	 */
	@Override
	public boolean contains(K key) {
		return getNode(root, key)!= null;
	}

	/**
	 * 
	 */
	@Override
	public V get(K key) {
		Node node = getNode(root, key);
     return node == null ? null : node.value;	
		//return getNode(root, key).value;
	}

	/**
	 * 
	 */
	@Override
	public void set(K key, V newValue) {
//		if (getNode(root, key)!=null) {
//			add(root, key,newValue);
//		}else {
//			throw new IllegalArgumentException("such key is not existed .");
//		}	
		Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
	}

	/**
	 * 
	 */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * 
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

}
