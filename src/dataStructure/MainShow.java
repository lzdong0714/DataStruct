package dataStructure;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class MainShow {
	public static void main(String[] args) {
////		int a[]=new int[10];
////		a[1]=1;
////		System.out.println(a.length);
//		
//		System.out.println("------------");
//		Array<Integer> array=new Array<>();
//		for(int i=0;i<10;i++)
//		{
//			array.addLast(i);			
//		}
//		System.out.println(array);
//		
////		array.addFirst(-1);
////		System.out.println(array);
////		
////		array.remove(0);
////		array.remove(1);
////		System.out.println(array);
//		System.out.println("--------------------------");	
//		ArrayStack<Integer> stack=new ArrayStack<>();
//		for(int i=0;i<10;i++)
//		{
//			stack.push(i);
//		}
//		System.out.println(stack);
////		
////		stack.push(11);
////		
////		System.out.println(stack.getSize());
////		System.out.println(stack.getCapacity());
//	System.out.println("--------------------------");	
//		Queue<Integer> queue=new ArrayQueue<>();
//		
//		for(int i=0;i<10;i++)
//		{
//			queue.enqueue(i); 
//		}
//		System.out.println(queue);
//		
////		System.out.println(queue.dequeue());
//		
//		
//		System.out.println("--------------------------");	
//		Queue<Integer> loopqueue=new LoopQueue<>();
//		
//		for(int i=0;i<10;i++)
//		{
//			loopqueue.enqueue(i);
//			System.out.println(loopqueue);
//			if (i%3==2) {
//				loopqueue.dequeue(); 
//				System.out.println(loopqueue);
//			}
//		}
//		System.out.println("----------------");
//		
//		LinkedList<Integer> linkedList=new LinkedList<>();
//		
//		for(int i=0;i<10;i++)
//			{
//			linkedList.addFirst(i);
//			}
//			System.out.println(linkedList);
//			
//			linkedList.set(6, 666);
//			System.out.println(linkedList);
//			
//			linkedList.remove(2);
//			System.out.println(linkedList);
//			linkedList.removeFirst();
//			System.out.println(linkedList);
//			linkedList.removeLast();
//			System.out.println(linkedList);
	
		
		System.out.println("--------------------------");	
//		Queue<Integer> linkqueue=new LinkedListQueue<>();
//		
//		for(int i=0;i<10;i++)
//		{
//			linkqueue.enqueue(i);
//			System.out.println(linkqueue);
//			if (i%3==2) {
//				linkqueue.dequeue(); 
//				System.out.println(linkqueue);
//			}
//		}
		
		BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);
        bst.levelOrder();
        //System.out.println();

        //System.out.println(bst);
		
        // test removeMax
     
        MainShow show=new MainShow();
        //show.MAXdeleteTest();
       
	}
	public void MAXdeleteTest() 
		{

        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;

        // test removeMin
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");


        // test removeMax
        for(int i = 0 ; i < 10 ; i ++)
            bst.add(random.nextInt(10));

        nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMax());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");
    }
}
