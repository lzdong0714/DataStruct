package think.in.java.chapter15;

public class TwoTuple<A, B> {
    public final A first;

    public final B second;

    TwoTuple(A a, B b){
        this.first = a;
        this.second = b;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }
}
