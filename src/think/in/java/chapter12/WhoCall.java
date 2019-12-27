package think.in.java.chapter12;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class WhoCall {
    static void f(){

        try{
            throw new Exception();
        }catch (Exception e){
            for (StackTraceElement element : e.getStackTrace()){
                System.out.println(element.getMethodName());
            }
        }
    }

    static void g(){ f(); }

    static void h(){ g();}

    public static void main(String[] args) {
        f();
        System.out.println("=================================");
        g();
        System.out.println("=================================");
        h();
    }
}
