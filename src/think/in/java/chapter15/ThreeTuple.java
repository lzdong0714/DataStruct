package think.in.java.chapter15;

public class ThreeTuple<A, B, C> extends TwoTuple<A ,B> {
    public final C three;

    ThreeTuple(A a , B b, C c){
        super(a, b);
        three = c;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + "," + three +")";
    }
}
