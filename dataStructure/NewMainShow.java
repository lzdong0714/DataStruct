package dataStructure;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.Segment;

public class NewMainShow {
	
public static void main(String args[]) {
	NewMainShow mainShow=new NewMainShow();
	
	//mainShow.testLinekListMap();
	//mainShow.testBSTMap();
	//mainShow.testBSTMap2();
	//mainShow.testMaxheap();
	//mainShow.testSegemetTree();
	mainShow.testSegementTree2();
	
}
public void testLinekListMap() {
	System.out.println("Pride and Prejudice");

	ArrayList<String> words = new ArrayList<>();
	if(FileOperation.readFile("D:\\\\eclipse-workspace\\\\algorithm\\\\src\\\\pride-and-prejudice.txt",
			words)) {
	    System.out.println("Total words: " + words.size());

	    LinkedListMap<String, Integer> map = new LinkedListMap<>();
	    for (String word : words) {
	        if (map.contains(word))
	            map.set(word, map.get(word) + 1);
	        else
	            map.add(word, 1);
	    }

	    System.out.println("Total different words: " + map.getSize());
	    
	    System.out.println("Frequency of PRIDE: " + map.get("pride"));
	    System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
	}

	System.out.println();
}

public void testBSTMap() {
	 System.out.println("Pride and Prejudice");

     ArrayList<String> words = new ArrayList<>();
     if(FileOperation.readFile("D:\\eclipse-workspace\\algorithm\\src\\pride-and-prejudice.txt",
    		 words)) {
         System.out.println("Total words: " + words.size());

         BSTMap<String, Integer> map = new BSTMap<>();
         for (String word : words) {
             if (map.contains(word))
                 map.set(word, map.get(word) + 1);
             else
                 map.add(word, 1);
         }

         System.out.println("Total different words: " + map.getSize());
         System.out.println(map.toString());
         System.out.println("contains of PRIDE: " + map.contains("pride"));
 	    System.out.println("contains of PREJUDICE: " + map.contains("prejudice"));
         System.out.println("Frequency of PRIDE: " + map.get("pride"));
         System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
     }

     System.out.println();
}

public void testBSTMap2() {
	 System.out.println("Pride and Prejudice");

    ArrayList<String> words = new ArrayList<>();
    if(FileOperation.readFile("D:\\eclipse-workspace\\algorithm\\src\\pride-and-prejudice.txt",
   		 words)) {
        System.out.println("Total words: " + words.size());

        BSTMap2<String, Integer> map = new BSTMap2<>();
        for (String word : words) {
            if (map.contains(word))
                map.set(word, map.get(word) + 1);
            else
                map.add(word, 1);
        }

        System.out.println("Total different words: " + map.getSize());
        System.out.println(map.toString());
        System.out.println("contains of PRIDE: " + map.contains("pride"));
	    System.out.println("contains of PREJUDICE: " + map.contains("prejudice"));
        System.out.println("Frequency of PRIDE: " + map.get("pride"));
        System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
    }

    System.out.println();
}

public  void testMaxheap() {

    int n = 1000000;

    MaxHeap<Integer> maxHeap = new MaxHeap<>();
    Random random = new Random();
    for(int i = 0 ; i < n ; i ++)
        maxHeap.add(random.nextInt(Integer.MAX_VALUE));

    int[] arr = new int[n];
    for(int i = 0 ; i < n ; i ++)
        arr[i] = maxHeap.extractMax();

    for(int i = 1 ; i < n ; i ++)
        if(arr[i-1] < arr[i])
            throw new IllegalArgumentException("Error");

    System.out.println("Test MaxHeap completed.");
}

	public void testSegemetTree() {
		Integer[] nums= {-2,3,5,-5,-40,+26,5,7};
		
		SegmentTree<Integer> segmentTree=new SegmentTree<>(nums, new Merger<Integer>() {
			public Integer merger(Integer a, Integer b) { return a+b;}
		});
		
		System.out.println(segmentTree);
	}

	public void testSegementTree2() {
		Integer[] nums = {-2, 0, 3, -5, 2, -1};
	
	    SegmentTree<Integer> segTree = new SegmentTree<>(nums,
	            (a, b) -> a + b);
	    System.out.println(segTree);
	
	    System.out.println(segTree.query(0, 2));
	    System.out.println(segTree.query(2, 5));
	    System.out.println(segTree.query(0, 5));
	}
}
