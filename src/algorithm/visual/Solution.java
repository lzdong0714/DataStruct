package algorithm.visual;

public class Solution {
    public static boolean Find(int target, int [][] array) {

        int row=array.length;//行数
        int column=array[0].length;//列数

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
