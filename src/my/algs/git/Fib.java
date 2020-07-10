package my.algs.git;

import java.util.List;

/**
 * @Author: Liu Zhendong
 * @Description 斐卜拉切数列求和
 * @createTime 2020年07月10日 14:34:00
 */
public class Fib {

    /**
     * 带一个“备忘录”计算数列已知数据 men
     */

    int men[];
    public Fib (int n){
        men = new int[n + 1];
    }
    public int fibCal(int n){
        if(men == null) men = new int[n+1];
        return helper(men, n);
    }

    private int helper(int[] men, int n ){
//        assert men.length > n : "memory length should not less than arg n";
        if(n < 0) return 0;
        if(n == 1 || n == 2) return 1;
        if(men[n] != 0) return men[n];
        men[n] = helper(men, n - 2) + helper(men, n - 1);
        return men[n];
    }

    /**
     * 其实回归原始，累加就行，只是这个数列的构造形式，适合用于讲解递归
     * @param args
     */
    public int fibCalFast(int n){
        if( n < 0 ) return 0;
        if( n == 1 || n == 2) return 1;
        int next;
        int tmp = 1;
        int ret = 2;
        for(int i = 3; i < n; i++){
            next = ret + tmp;
            tmp = ret;
            ret = next;
        }
        return ret;
    }
    public static void main(String[] args) {
        int num = 20;
        Fib fib = new Fib(num);
        int result = fib.fibCal(num);
        System.out.println("fib result is " + result);

        System.out.println("fast result fib is " + fib.fibCalFast(num));

    }

}
