package dataStructure;

public class SegmentTree<E> {
	private E[] data;
	Merger< E> merger;
	private E[] tree;
	@SuppressWarnings("unchecked")
	public SegmentTree(E[] arr,Merger<E> merger) {
		this.merger=merger;
		data=(E[])new Object[arr.length];
		for(int i=0;i<arr.length;i++)
		data[i]=arr[i];
		
		tree=(E[])new Object[arr.length*4];
		
		buildSegmentTree(0,0,arr.length-1);
	}
	
	private void buildSegmentTree(int treeIndex,int l,int r) {
		if (l==r) {
			tree[treeIndex]=data[l];
			return;
		}
		
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		
		int m=l+(r-l)/2;
		
		buildSegmentTree(leftTreeIndex, l, m);
		buildSegmentTree(rightTreeIndex, m+1, r);
		
		tree[treeIndex]=merger.merger(
				tree[leftTreeIndex], tree[rightTreeIndex]);
		
	}

	public int getSize()
	{
		return data.length;
		
	}
	
	public boolean isEmpty() {
		return data==null;
	}
	
	public E get(int index)
	{
		if (index<0||index>data.length) {
			throw new IllegalArgumentException("Index is illegal");
		}
		
		return data[index];
	}
	
	private int leftChild(int index)
	{
		return index*2+1;
	}
	
	private int rightChild(int index)
	{
		return index*2+2;
	}

	public E query(int queryL,int queryR) {
		if (queryL<0||queryR>data.length) {
			throw new IllegalArgumentException("Index is illegal");
		}
		return query(0, 0, data.length-1,queryL,queryR) ;
	}
	
	private E query(int treeIndex,int l,int r,int queryL,int queryR) {
//		if(l==querL&&r==queryR)
//			return tree[treeIndex];
//		int m=l+(r-l)/2;
//		int leftTreeIndex=leftChild(treeIndex);
//		int rightTreeIndex=rightChild(treeIndex);
//		
//		if(querL>m+1) {
//			return query(rightTreeIndex, m+1, r, querL, queryR);
//		}else if(queryR<=m)
//		{
//			return query(leftTreeIndex, l, m, querL, queryR);
//		}
//			
//		return	merger.merger(
//					query(leftTreeIndex, l, m, querL, m),
//					query(rightTreeIndex, m+1, r, m+1, queryR));
//		
		if(l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if(queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merger(leftResult, rightResult);		
	}
	
	@Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
