package think.in.java.chapter12;


import org.omg.Messaging.SYNC_WITH_TRANSPORT;

class MyException extends Exception{
    public MyException(){}

    public MyException(String msg){
        super(msg);
    }
}

public class FullContructors {
    public static void f() throws MyException{
        System.out.println("Throwing MyException from f()");
        throw new MyException();
    }

    public static void g() throws MyException{
        System.out.println("Throwing MyException from g()");
        throw new MyException("originated in g()");
    }


    public static void main(String[] args) {
        try {
            f();
        }catch (MyException e){
            e.printStackTrace(System.err);
        }

        try {
            g();
        }catch (MyException e){
            e.printStackTrace(System.out);
        }
    }
}
