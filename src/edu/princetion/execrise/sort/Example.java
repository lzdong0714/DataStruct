package edu.princetion.execrise.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public abstract class Example {


    public abstract void sort(Comparable[] a);

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }


    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            StdOut.print(a[i] + "");

            StdOut.print();
        }
    }


    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }


}
