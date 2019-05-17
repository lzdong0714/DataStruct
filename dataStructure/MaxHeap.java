package dataStructure;

public class MaxHeap<E extends Comparable<E>> {

	private Array<E> data;
	
	public MaxHeap() {
		data=new Array<>();
	}
	
	public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for(int i = parent(arr.length - 1) ; i >= 0 ; i --)
            siftDown(i);
    }
	
	public int getSize() {
		return data.getSize();
	}
	
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	 // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }
    
    public void add(E e) {
    	data.addLast(e);
    	siftUp(data.getSize()-1);
		
	}
    
    //添加元素
    private void siftUp(int k)
    {
    	while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0 ){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }
    
    public E findMax() {
    	if(data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
	}
    
    public E extractMax(){

        E ret = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }
    //取出元素
    private void siftDown(int k)
    {
    	while(leftChild(k)<getSize())
    	{
    		int j=leftChild(k);
    		if(j+1<getSize()&&data.get(j).compareTo(data.get(j+1))<0)
    			j++;
    		if (data.get(k).compareTo(data.get(j))>0) {
				break;
			}
    		
    		data.swap(k, j);
    		k=j;
    	}
    	
    }
    
}
