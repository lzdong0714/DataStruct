package dataStructure;



public class LinkedListMap<K,V> implements Map<K, V> {
	
	
	private class Node
	{
		public K key;
		public V value;
		public Node next;//当作指针就好了
		public Node(K key,V value,Node next) {
			this.key=key;
			this.value=value;
			this.next=next;
		}
		
		public Node(K  key) {
			this.key=key;
			this.value=null;
			this.next=null;
		}
		
		public Node() {
			this.key=null;
			this.value=null;
			this.next=null;
		}

		@Override
		public String toString() {
			return key.toString()+":"+value.toString();
		}
	}

	private Node dummyHead;
	private int size;
	
	public LinkedListMap() {
		dummyHead=new Node();
		size=0;
	}
	
	/**
	 * 随便写写看能不能显示出来
	 * @param key 键
	 * @param value 键值
	 *  
	 */
	@Override
	public void add(K key, V value) {
		Node node=getNode(key);
		if (node==null) {
			dummyHead.next=new Node(key,value,dummyHead.next);
			size++;
		}else {
			node.value=value;
		}
		
	}

	/**与教学视频略有不同，注意查错
	 * @param key
	 * 
	 */
	@Override
	public V remove(K key) {
		Node prev=dummyHead;
		while(prev.next!=null)
		{
			if (prev.next.key.equals(key)) {
				Node deletNode=prev.next;
				prev.next=deletNode.next;
				deletNode.next=null;
				size--;
				return deletNode.value;
			}
			else {
				prev=prev.next;
			}
		}
		
		return null;
	}

	/**
	 * 自己写的暂时
	 */
	@Override
	public boolean contains(K key) {
		
		return getNode(key)!=null;
	}

/**
 * @param V key
 */
	@Override
	public V get(K key) {
		Node node=getNode(key);
		return node==null?null:node.value;
	}

	/**
	 * @param key
	 * @param newValue
	 */
	@Override
	public void set(K key, V newValue) {
		Node node=getNode(key);
		if (node==null) {
			throw new IllegalArgumentException("such key is not exist");
		}else {
			node.value=newValue;
		}
		
	}

	@Override
	public int getSize() {
		
		return size;
	}

	@Override
	public boolean isEmpty() {
		
		return size==0;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	private Node getNode(K key) {
		Node cur=dummyHead.next;
		while(cur!=null)
		{
			if(cur.key.equals(key))
				return cur;
			cur=cur.next;
		}
		return null;
	}
}
