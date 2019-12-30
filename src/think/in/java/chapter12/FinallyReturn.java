package think.in.java.chapter12;

/**
 * finally 与 return 的关系
 */
public class FinallyReturn {
    public static void f(int i){
        try {
            switch(i) {
                case 1:
                    System.out.println(i);
                    return;
                case 2:
                    System.out.println(i);
                    return;
                case 3:
                    System.out.println(i);
                    return;
                default:
                    System.out.println("return");
                    return;
            }
        }finally {
            System.out.println("finally execute");
        }
    }

    public static void main(String[] args) {
        for(int i = 1 ; i < 5; i++){
            f(i);
        }
    }


}
