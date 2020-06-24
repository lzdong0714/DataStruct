package demo.base;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年05月15日 15:24:00
 */
public class ArrayCollection {

    private LinkedList<String> str[] = new LinkedList[3];


    public void test(){

        LinkedList<String> list = str[1];
        list.push("a");
        System.out.println(str.length);
    }

    public void test_1(){
        long count = Arrays.asList("abc", "adc", "www", "NN", "20180N-201").stream()
                .filter(item -> {
                    return item.contains("N");
                }).count();
        System.out.println("get count " + count);

    }

    public static void main(String[] args) {
        ArrayCollection collection = new ArrayCollection();
//        collection.test();
        collection.test_1();
    }
}
