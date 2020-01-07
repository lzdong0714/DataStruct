package think.in.java.chapter10;

interface Incrementable{
    void inrement();
}


class Callee1 implements Incrementable{

    private int i = 0;

    @Override
    public void inrement() {
        i ++;
        System.out.println(i);
    }
}

class MyIncrement{
    public void increment(){
        System.out.println("Other operation");
    }

    static void f(MyIncrement increment){
        increment.increment();
    }
}

class Callee2 extends MyIncrement{
    private int i = 0;

    public void increment(){
        super.increment();
        i ++;
        System.out.println(i);
    }

    private class Closure implements Incrementable{

        @Override
        public void inrement() {
            Callee2.this.increment();
        }
    }

    Incrementable getCallBackReference(){
        return new Closure();
    }
}

class Caller{
    private Incrementable callBacReference;
    Caller(Incrementable inc){ callBacReference = inc;}
    void go(){
        callBacReference.inrement();
    }
}

public class CallBacks {

    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallBackReference());
        caller1.go();
        caller2.go();
        caller2.go();
    }
}
