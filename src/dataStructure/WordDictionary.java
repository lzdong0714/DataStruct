package dataStructure;

import java.util.TreeMap;



public class WordDictionary {
	private class Node
	{
		public boolean isWord;
		public TreeMap<Character, Node> next;
		
		public Node(boolean isWord) {
			this.isWord=isWord;
			// TODO Auto-generated constructor stub
		}
		
		public Node() {
		this.isWord=false;
			// TODO Auto-generated constructor stub
		}
	}
	
	private Node root;
	
	public  WordDictionary() {
		root=new Node();
	}
	
	
	public void add(String word)
	{
		Node cur=root;
		for(int i=0;i<word.length();i++)
		{
			char c=word.charAt(i);
			if (cur.next.get(c)==null) {
				cur.next.put(c, new Node());			
			}cur=cur.next.get(c);
		}
		if(!cur.isWord)
		{
			cur.isWord=true;
		}
	}
	
	public boolean search(String word) {
		return false;
		
	}
	
	private boolean match(Node node,String word,int index) {
		if(index==word.length())
			return node.isWord;
		
		char c=word.charAt(index);
		if (c!='.') {
			if(node.next.get(c)==null)
				return false;
			return match(node.next.get(c), word, index+1);
		}else {
			for(char nextchar : node.next.keySet())
				if(match(node.next.get(nextchar), word, index+1))
					return true;
			return false;
		}		
	}
}
