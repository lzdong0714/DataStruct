package algorithm;

public class Solution {
	public static boolean Find(int target, int [][] array) {
		
		int row=array.length;//����
		int column=array[0].length;//����
		
		if(row==column)
		{
			int max=array[row][column];
			int min=array[0][0];
			
		}
		else {}
		return target==row;
    }
	
	
	public static void main(String args[])
	{
		int target=3;
		int [][] arr=new int [3] [3];
		System.out.println(Find(target,arr)); 
		
		}

}
