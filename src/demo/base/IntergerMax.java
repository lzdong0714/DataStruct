package demo.base;

import java.math.BigInteger;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年05月12日 15:01:00
 */
public class IntergerMax {

    private static Integer integerMax = Integer.MAX_VALUE;

    private static Long longMax = Long.MAX_VALUE;

    private static BigInteger bigIntegerMax = BigInteger.valueOf(longMax);


    public static void main(String[] args) {


        System.out.println("Integer  ： " + integerMax);
        System.out.println("Long  ： " + longMax);
        System.out.println("BigInteger  ： " + bigIntegerMax);
    }

}