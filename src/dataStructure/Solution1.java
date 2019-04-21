package dataStructure;

public class Solution1 {
	class ListNode{
		public int val;
		public ListNode next;
		
		public ListNode(int[] arr) {
			if (arr==null||arr.length==0) {
				throw new IllegalArgumentException("arr is empty");
			}
			this.val=arr[0];
			ListNode cur=this;
			for (int i=1;i<arr.length;i++)
			{
				cur.next=new ListNode(arr[i]);
				cur=cur.next;
			}
		}
		
		public ListNode(int val) {
			this.val=val;
			next=null;
		}
		
		@Override
		public String toString() {
			StringBuilder res=new StringBuilder();
			ListNode node=this;
			res.append("head: ");
			while(node!=null)
			{
				res.append(node.val+"->");
				node=node.next;
			}
			res.append("NULL");
			return res.toString();
		}
	}
	
	
	ListNode removeElement(ListNode listNode,int val)
	{
		ListNode dummyHead=new ListNode(-1);
		
		dummyHead.next=listNode;
		ListNode prev=dummyHead;
		while(prev.next!=null)
		{
			if (prev.next.val==val) {
				ListNode cur=prev.next;
				prev.next=cur.next;
				cur.next=null;
				
			} else {
				prev=prev.next;

			}
		}
		
		return listNode;
	}
	
	ListNode removeElement2(ListNode listNode,int val)
	{
		//递归的方式删除特定元素
		if (listNode.next==null) {
			return listNode;
		}
		ListNode res=removeElement2(listNode.next, val);
		if(listNode.val==val)
		{
			return res;
		}
		else
		{
			listNode.next=res;
			return listNode;
		}
	}
	
	public void show() {
		int[] arr= {1,2,3,4,5,6,7,8,9};
		ListNode node=new ListNode(arr);
		System.out.println(node);
		node=removeElement2(node, 6);
		System.out.println(node);
	}
	
	
	
	
	public static void main(String args[]) {
		Solution1 solution1=new Solution1();
		solution1.show();
		
		
		
	}

}
