package demo.base;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年11月28日 16:13:00
 */
public class StringBufferUsage {

    public static void main(String[] args) {
        demo_1();
    }

    private static void demo_1(){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10 ; i ++){
                buffer.append("name").append(",");
        }
        buffer.deleteCharAt(buffer.length()-1);
        System.out.println(buffer.toString());
    }
}
