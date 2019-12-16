package data.struct.uf;

public class UnionFind5 implements UF{
	private int[] rank;
	private int [] parent;

	// rank[i]表示以i为根的集合所表示的树的层数
	// 在后续的代码中, 我们并不会维护rank的语意, 也就是rank的值在路径压缩的过程中, 有可能不在是树的层数值
	// 这也是我们的rank不叫height或者depth的原因, 他只是作为比较的一个标准
	public UnionFind5(int size) {
		rank=new int [size];
		parent=new int [size];
		for(int i=0;i<size;i++)
		{
			rank[i]=1;
			parent[i]=i;
		}
	}


	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return parent.length;
	}

	public int find(int p) {
		if(p<0||p>=parent.length)
		{
			throw new IllegalArgumentException("p is out if boud");
		}

		while(p!=parent[p])
			p=parent[p];

//		parent[p] = parent[parent[p]];//路径压缩的方式，在查找中执行
//        p = parent[p];
		return p;
	}

	@Override
	public boolean isConnected(int p, int q) {
		int pRoot=find(p);
		int qRoot=find(q);
		return pRoot==qRoot;
	}

	@Override
	public void unionElements(int p, int q) {
		int pRoot=find(p);
		int qRoot=find(q);
		if (pRoot==qRoot) {
			return ;
		}
		if (rank[pRoot]<rank[qRoot]){
			parent[pRoot]=qRoot;
		}else if (rank[pRoot]>rank[qRoot]) {
			parent[qRoot]=pRoot;
		}else {
			parent[pRoot]=qRoot;
			rank[qRoot]+=1;
		}
	}

}
