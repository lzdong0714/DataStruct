package think.in.java.chapter10;

import think.in.java.chapter10.innerface.Contents;
import think.in.java.chapter10.innerface.Destination;

public class Parcel11 {

    private static class ParcelContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected static class ParcelDestination implements Destination{

        private String label;

        private ParcelDestination(String whereTo){
            this.label = whereTo;
        }

        @Override
        public String readLine() {
            return label;
        }
    }

    public static void f(){}
    static int x = 10;
    static class AnotherLevel{
        public static void f(){};
        static int x = 10;
    }


    public static Destination destination(String s){
        return new ParcelDestination(s);
    }

    public static Contents contents(){
        return new ParcelContents();
    }

    public static void main(String[] args) {
        Contents c = contents();
        Destination d = destination("world hello");
    }
}
