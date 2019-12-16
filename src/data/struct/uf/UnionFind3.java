package data.struct.uf;


public class UnionFind3 implements UF {
	private int []parent; // parent[i]表示第一个元素所指向的父节点
	private int []sz; // sz[i]表示以i为根的集合中元素个数

	public UnionFind3(int size) {
		parent=new int[size];
		sz=new int [size];

		for(int i=0;i<size;i++)
		{
			parent[i]=i;
			sz[i]=1;
		}

	}

	@Override
	public int getSize() {
		// 元素个数
		return parent.length;
	}


	// 查找过程, 查找元素p所对应的集合编号
	// O(h)复杂度, h为树的高度
	private int find(int p)
	{
		if(p<0||p>=parent.length)
			throw new IllegalArgumentException("p is out of bound");

		// 不断去查询自己的父亲节点, 直到到达根节点
		// 根节点的特点: parent[p] == p
		while(p!=parent[p])
			p=parent[p];

		return p;
	}


	@Override
	public boolean isConnected(int p, int q) {

		return find(p)==find(q);
	}

	@Override
	public void unionElements(int p, int q) {
		int pRoot=find(p);
		int qRoot=find(q);

		if (pRoot==qRoot) {
			return;
		}

		if (sz[pRoot]<sz[qRoot]) {
			parent[pRoot]=qRoot;
			sz[qRoot]+=sz[pRoot];
		} else {
			parent[qRoot]=pRoot;
			sz[pRoot]+=sz[qRoot];

		}
	}

}

