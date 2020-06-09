package my.algs.git;

import org.apache.logging.log4j.core.util.Assert;

import java.util.Arrays;

/**
 * @Author: Liu Zhendong
 * @Description 素数
 * @createTime 2020年06月02日 15:49:00
 */
public class Primes {

    public static int countPrimes(int n){
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for(int i = 2; i * i < n; i++){
            if(isPrim[i]){
                for (int j = i*i; j < n; j = j+i){ // 关键这里填充了整个区间，是如何证明的？？？？
                    isPrim[j] = false;
                }
            }

        }

        int count = 0;
        for(int i = 2; i < n; i++){
            if(isPrim[i])
                count ++;
        }
         return count;
    }


    public static boolean isPrime(int n){

        if(n < 2){
            throw new IllegalArgumentException("输入参数不可用小于2");
        }

        for(int index = 2 ; index * index < n; index++){
            if(n % index == 0)
                return false;
        }


        return true;
    }


    public static void main(String[] args) {

        System.out.println("count: " + Primes.countPrimes(10));
        System.out.println("is prime: " + Primes.isPrime(333333));

    }


}
