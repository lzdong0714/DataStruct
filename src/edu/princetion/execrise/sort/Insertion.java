package edu.princetion.execrise.sort;

public class Insertion extends Example {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for(int i = 0; i < N; i++){
            for(int j = i; j > 0; j--){
                if( !less(a[j], a[j-1]) )
                    exch(a, j, j-1);
            }
        }
    }


    public static void main(String[] args) {
        String[] a = {"A", "S", "B","W"};
        Insertion insertion = new Insertion();
        insertion.sort(a);
        assert Insertion.isSorted(a);
        Insertion.show(a);
    }
}
