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
		public Node left,right;//����ָ��ͺ���
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
	 * @param node �Դ�Ϊ���ڵ��л�ȡ ����Ӧ�Ľڵ�
	 * @param key ��
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
	 * @return ������Сֵ���ڵĽڵ�
	 */
	private Node minimum(Node node) {
		if (node.left==null) {
			return node;
		}
		return minimum(node.left);
	}
	
	/**
	 * ɾ���Ը��ڵ�Ϊ��������������С�ڵ�
	 * ����ɾ���ڵ�Ķ��������ڵ�
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
		//���ظ��ڵ��Ҫ��ֵ ����Ѱ�ڵ�Ŀ���ֱ�ӷ�����һ��
		node.left=removeMin(node.left);
		return node;		
	}
	
	/**
	 * ����ɾ��ָ���ڵ�ֵ��������
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
			//node.key==key �����ɾ��
			if(node.left==null)//������Ϊ��
			{
				Node nodeRight=node.right;
				node.right=null;
				size--;
				return nodeRight;
			}else if(node.right==null) {//������Ϊ��
				Node nodeLeft=node.left;
				node.left=null;
				size--;
				return nodeLeft;
			}else {//���߶���Ϊ�գ�ɾ���ڵ����֦��Сֵ�滻
				Node successor=minimum(node.right);
				successor.right=removeMin(node.right);
				successor.left=node.left;
				node.left=node.right=null;
				return successor;
			}
		}
	}
	
	/**
	 * �ж��ǰ���keyֵ
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
