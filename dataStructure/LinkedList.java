package dataStructure;

import dataStructure.Solution1.ListNode;

public class LinkedList<E> {
	
	
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
	
	private Node dummyhead;
	private int size;
	
	public LinkedList()
	{
		dummyhead=new Node(null,null);
		size=0;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public void addFirst(E e) {
		add(0,e);
	}
	
	public void add(int index,E e)
	{
		if(index<0||index>getSize())
			throw new IllegalArgumentException(
					"total number of Elements is "+getSize());
		
		Node pre=dummyhead; 
		for (int i=0;i<index;i++) 
		{
			pre=pre.next;			
		}
//			Node node=new Node(e);
//			node.next=pre.next;
//			pre.next=node;
		pre.next=new Node(e, pre.next);
			size++;
	}
	
	public void add(E e) {
		add(size,e);
	}
	
	public E get(int index) {
		if(index<0||index>getSize())
			throw new IllegalArgumentException(
					"total number of Elements is "+getSize());
		Node current=dummyhead.next;
		for (int i=0;i<index;i++) 
		{
			current=current.next;			
		}
		return current.e;
	}
	
	public E getFirst() {
		return get(0);
		
	}
	
	public E getLast() {
		return get(size);
	}
	
	public void set(int index,E e) {
		if(index<0||index>getSize())
			throw new IllegalArgumentException(
					"total number of Elements is "+getSize());
		Node current=dummyhead.next;
		for (int i=0;i<index;i++) 
		{
			current=current.next;			
		}
		current.e=e;
	}
	
	public boolean contains(E e) {

		Node cur=dummyhead.next;
		while(cur!=null)
		{
			if(cur.equals(e))
			return true;
			cur=cur.next;
		}
		
		return false;
	}

	public E remove(int index) {
		if(index<0||index>=getSize())
			throw new IllegalArgumentException(
					"total number of Elements is "+getSize());
		Node prev=dummyhead;
		for(int i=0;i<index;i++)
		{
			prev=prev.next;
		}
		Node ret=prev.next;
		prev.next=ret.next;
		ret.next=null;
		size--;
		
		return ret.e;
	}

	public E removeFirst() {
		return remove(0);
	}
	
	public E removeLast() {
		return remove(size-1);
	}
	
	public void removeElement(E e) {
		LinkedList<E>.Node prev=dummyhead;
		while(prev.next!=null)
		{
			if (prev.next.e.equals(e)) {
				Node cur=prev.next;
				prev.next=cur.next;
				cur.next=null;
			} else {
				prev=prev.next;
			}
		}
		size--;
	}
	
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		
		Node cur=dummyhead.next;
		while(cur!=null)
		{
			res.append(cur+"->");
			cur=cur.next;
		}
		res.append("NULL");
		return res.toString();
	}
	
	
	
	
}
