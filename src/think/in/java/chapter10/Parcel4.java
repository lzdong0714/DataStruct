package think.in.java.chapter10;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import think.in.java.chapter10.innerface.Contents;
import think.in.java.chapter10.innerface.Destination;

public class Parcel4 {
    private class PDestination implements Destination{

        private String label;
        private PDestination(String whereTo){
            label = whereTo;
        }
        @Override
        public String readLine() {
            return label;
        }
    }


    private class PContent implements Contents{
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    public Destination destination(){
        return new PDestination("YCY");
    }

    public Contents contents(){
        return new PContent();
    }

    public static void main(String[] args) {
        Parcel4 p4 = new Parcel4();
        Contents contents = p4.contents();
        int value = contents.value();
        System.out.println(value);
        Destination destination = p4.destination();
        System.out.println(destination.readLine());
    }
}
