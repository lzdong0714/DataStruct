package dataStructure;

public class UnionFind5 implements UF{
	private int[] rank;
	private int [] parent;
	
	// rank[i]��ʾ��iΪ���ļ�������ʾ�����Ĳ���
    // �ں����Ĵ�����, ���ǲ�����ά��rank������, Ҳ����rank��ֵ��·��ѹ���Ĺ�����, �п��ܲ��������Ĳ���ֵ
    // ��Ҳ�����ǵ�rank����height����depth��ԭ��, ��ֻ����Ϊ�Ƚϵ�һ����׼	
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
		
//		parent[p] = parent[parent[p]];//·��ѹ���ķ�ʽ���ڲ�����ִ��
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
