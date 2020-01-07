package think.in.java.chapter10;

public interface ClassInterface {
    void howd();

    class Test implements ClassInterface{

        @Override
        public void howd() {
            System.out.println("holy shit");
        }

        public static void main(String[] args) {
            new Test().howd();
        }
    }
}
