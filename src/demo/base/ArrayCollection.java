package demo.base;

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

    public static void main(String[] args) {
        ArrayCollection collection = new ArrayCollection();
        collection.test();
    }
}
