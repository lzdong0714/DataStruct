package think.in.java.chapter12;

// 在throw Exception 到函数外之前还是会先执行 finally

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

class AlwaysException extends Exception{}

public class FinallyAlways {

    public static void main(String[] args) {
        System.out.println("step into main");
        try {
            System.out.println("into first block");
            try {
                System.out.println("into second block");
                throw new AlwaysException();
            }
//            catch (AlwaysException e){
//                System.out.println("catch second block");
//            }
            finally {
                System.out.println("second block finally");
            }
        }catch (Exception e){
                System.out.println("catch first block");

        }finally {
            System.out.println("first block finally");
        }
    }

}
