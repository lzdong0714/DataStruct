package think.in.java.chapter10;

import think.in.java.chapter10.innerface.Destination;

public class Parcel5 {
    public Destination destination(String s){
        class PDestination implements Destination{
            private String label;

            private PDestination(String whereTo){
                label = whereTo;
            }

            @Override
            public String readLine() {
                return label;
            }
        }

        return new PDestination(s);
    }


    public static void main(String[] args) {
        Parcel5 p5 = new Parcel5();
        Destination destination = p5.destination("Tasmania");
        String line = destination.readLine();
        System.out.println(line);
    }
}
