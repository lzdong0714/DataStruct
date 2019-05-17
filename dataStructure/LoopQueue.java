package dataStructure;

public class LoopQueue<E> implements Queue<E> {
	private E[] data;
	private int front;
	private int tail;
	private int size;//练习 ：去掉size重写循环队列方法
	
	
	@SuppressWarnings("unchecked")
	public LoopQueue(int capacity) {
		//首尾判定需要多一个元素空间
		data= (E[])new Object[capacity+1];
		front=tail=0;
		size=0;
	}
	
	public LoopQueue() {
		this(10);
	}
	
	public int getCapacity() {
		return data.length-1;
	}

	@Override
	public void enqueue(E e) {
		if((tail+1)%data.length==front)
		{
			resize(2*getCapacity());
		}
		
			data[tail]=e;
			tail=(tail+1)%data.length;
			size++;
		
	}

	private void resize(int newCapacity) {
		@SuppressWarnings("unchecked")
		E[] newData= (E[])new Object[newCapacity+1];
		for(int i=0;i<size;i++)
		{
			newData[i]=data[(i+front)%data.length];
		}
		data=newData;
		front=0;
		tail=size;		
	}

	@Override
	public E dequeue() {
		if(isEmpty())
		{
			throw new IllegalArgumentException("No Element is in Queue");
			
		}
			E ret=data[front];
			data[front]=null;			
			front=(front+1)%data.length;
			size--;			
		if(size==getCapacity()/4&&getCapacity()/2!=0)
			resize(getCapacity()/2);
		
		
		return ret;
	}

	@Override
	public E getFront() {
		if(isEmpty())
		{
			throw new IllegalArgumentException("No Element is in Queue");
			
		}
		return data[front];
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return front==tail;
	}
	
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append(String.format("Queue: Size=%d, Capacity=%d\n", size,getCapacity()));
		res.append("front [");
		for(int i=front;i!=tail;i=(i+1)%data.length )
		{
			res.append(data[i]);
			if((i+1)%data.length!=tail)
				res.append(",");
		}
		res.append("] tail");  
		return res.toString();

	}

}
