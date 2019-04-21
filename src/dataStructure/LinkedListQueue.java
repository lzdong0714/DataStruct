package dataStructure;



public class LinkedListQueue<E> implements Queue<E>{

	private class Node	
	{
		public E e;
		public Node next;//当作指针就好了
		public Node(E e,Node next) {
			this.e=e;
			this.next=next;
		}
		
		public Node(E e) {
			this.e=e;
			this.next=null;
		}
		
		public Node() {
			this.e=null;
			this.next=null;
		}

		@Override
		public String toString() {
			return e.toString();
		}		
	}

	private Node head;
	private Node tail;
	private int size;
	
	

	public LinkedListQueue() {
		head=tail=null;
		size=0;
	}

	@Override
	public void enqueue(E e) {
		if(tail==null)
		{
			tail=new Node(e);
			head=tail;
		}else {
			tail.next=new Node(e, tail.next);
			tail=tail.next;
		}
		size++;
	}

	@Override
	public E dequeue() {
		if(isEmpty())
			throw new IllegalArgumentException("No Element is exist");
		Node ret=head;
		head=head.next;
		ret.next=null;
		if(head==null)
			tail=null;//
		size--;
		return ret.e;
	}

	@Override
	public E getFront() {
		if(isEmpty())
			throw new IllegalArgumentException("No Element is exist");
		return head.e;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Queue:front ");
		
		Node cur=head;
		while(cur!=null)
		{
			res.append(cur+"->");
			cur=cur.next;
		}
		res.append("NULL tail");
		
		return res.toString();
	}
	
	

}
