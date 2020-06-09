package edu.princetion.execrise.sort;

public class Merge extends Example{
    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi){

        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k ++){
            aux[k] = a[k];
        }

        for(int k = lo; k <= hi; k++){
            if(i > mid)                 a[k] = aux[j++];
            else if(j > hi)             a[k] = aux[i++];
            else if(less(a[i], a[j]))   a[k] = aux[i++];
            else                        a[k] = aux[j++];
        }

    }

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0 , a.length-1);
    }

    /**
     * 递归 merge 在 sort之后，后序遍历？？？
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }


    public static void main(String[] args) {
        String[] a = {"A", "S", "B","W", "T"};
        Merge merge = new Merge();
        merge.sort(a);
        assert Merge.isSorted(a);
        Merge.show(a);
    }
}
