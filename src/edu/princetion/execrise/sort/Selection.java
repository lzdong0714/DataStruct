package edu.princetion.execrise.sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class Selection extends Example {


    public static void main(String[] args) {
        String[] a = {"A", "S", "B","W"};
        Selection selection = new Selection();
        selection.sort(a);
        assert Selection.isSorted(a);
        Selection.show(a);
    }

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for(int i = 0 ; i < N; i++ ){
            int min =i;
            for(int j = i; j < N; j++){
                boolean is_less = less(a[j], a[min]);
                if(is_less){
                    min = j;
                    exch(a, i, min);
                }
            }
        }
    }
}
