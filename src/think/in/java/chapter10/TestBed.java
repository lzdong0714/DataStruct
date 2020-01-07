package think.in.java.chapter10;

public class TestBed {
    private void f(){
        System.out.println("method f()");
    }

    public static class Tester{
        public static void main(String[] args) {
            TestBed testBed = new TestBed();
            testBed.f();
        }
    }
}
