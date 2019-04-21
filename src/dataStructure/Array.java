package dataStructure;

public  class  Array<E> {
	private E[] data;
	private int size;
	/**
	 * 
	 * @param capactiy 元素个数
	 */
	
	@SuppressWarnings("unchecked")
	public Array(int capactiy) {
		data=(E[])new Object[capactiy];
		size=0;
	}
	/**
	 * 默认构造函数为10个元素
	 */
	public Array() {
		this(10);
	}
	public Array(E[] arr) {
		data=arr;
	}
	/**
	 * 返回容量
	 */
	public int getSize() {
		return size;
	}
	
	public int getCapacity() {
		return data.length;
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public void add(int index,E e) {
		if(size==data.length)
		{
			resize(2*data.length);
		}
		if (index<0||index>size) {
			throw new IllegalArgumentException("add failed ,add index<0||index>size");
		}
		
		for(int i=size-1;i>=index;i--)
		{
			data[i+1]=data[i];
		}
		data[index]=e;
		size++;
	}
	
	private void resize(int newCapacity) {
		@SuppressWarnings("unchecked")
		E[] newData=(E[])new Object[newCapacity];
		for(int i=0;i<size;i++)
		{
			newData[i]=data[i];
		}
		data=newData;
		//newData=null;
		
	}
	public void addLast(E e) {
		add(size, e);		
	}
	
	public void addFirst(E e) {
		add(0, e);	
	}
	
	public E get(int index) {
		if(index<0||index>data.length)
		{
			throw new IllegalArgumentException("add failed ,add index<0||index>size");
		}
		
		return data[index];
	}
	
	public void set(int index,E e) {
		if(index<0||index>data.length)
		{
			throw new IllegalArgumentException("add failed ,add index<0||index>size");
		}
		data[index]=e;
	}
	
	public boolean contains(E e) {
		for(int i=0;i<size;i++)
			if(data[i].equals(e))
			{
				return true;
			}
		return false;
	}
	
	public int find(E e) {
		for(int i=0;i<size;i++)
			if(data[i].equals(e))
			{
				return i;
			}
		return -1;
		
	}
	
	public E remove(int index) {
		if(index<0||index>data.length)
		{
			throw new IllegalArgumentException("add failed ,add index<0||index>size");
		}
		E ret=data[index];
		for(int i=index+1;i<size;i++)
		{
			data[i-1]=data[i];
		}
		size--;
		data[size]=null;//将引用释放，加快回收站回收垃圾
		
		if(size==data.length/2&& data.length / 2 != 0)
		resize(data.length/2);
		return ret;
	}
	
	public E removeFirst() {
		return remove(0);		
	}
	
	public E removeLast()
	{
		return remove(size-1);
	}
	
	public void removeElement(E e) {
		int index=find(e);
		if (index!=-1) {
			remove(index);
		}		
	}
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append(String.format("Array: Size=%d, Capacity=%d\n", size,data.length));
		res.append("[");
		for(int i=0;i<size;i++)
		{
			res.append(data[i]);
			if(i!=size-1)
				res.append(",");
		}
		res.append("]");
		return res.toString();

	}
	
	public E getFirst()
	{
		return get(0);
	}
	
	public E getLast()
	{
		return get(size-1);
	}
	
	public void swap(int i, int j){

        if(i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal.");

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
	
}
