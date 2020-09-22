package demo.base;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年08月07日 11:03:00
 */
public class CalHash {

    public static void main(String[] args) {

        String str = "红钢城街社区卫生服务中心";
        cal(str);
        str = str + str;
        cal(str);
        cal(str.substring(1, 3));
    }

    private static void cal(String string){
        int code = string.hashCode();
        float ratio = code * 1.00f/Integer.MAX_VALUE;
        System.out.println(code + "|" + ratio);
        System.out.println("---------");
    }
}
