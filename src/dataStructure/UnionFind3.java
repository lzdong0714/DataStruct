package dataStructure;


public class UnionFind3 implements UF {
	private int []parent; // parent[i]��ʾ��һ��Ԫ����ָ��ĸ��ڵ�
	private int []sz; // sz[i]��ʾ��iΪ���ļ�����Ԫ�ظ���
	
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
		// Ԫ�ظ���
		return parent.length;
	}
	
	
	// ���ҹ���, ����Ԫ��p����Ӧ�ļ��ϱ��
    // O(h)���Ӷ�, hΪ���ĸ߶�
	private int find(int p)
	{
		if(p<0||p>=parent.length)
			throw new IllegalArgumentException("p is out of bound");
		
		// ����ȥ��ѯ�Լ��ĸ��׽ڵ�, ֱ��������ڵ�
        // ���ڵ���ص�: parent[p] == p
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
