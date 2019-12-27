package think.in.java.chapter18;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;

public class Blip3 implements Externalizable {

    private String s;
    private int i;
    public Blip3(){}
    public Blip3(String s, int i){
        this.s = s;
        this.i = i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3 writeExternal");
        // must do
        out.writeObject(s);
        out.writeObject(i);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3 readExternal");
        s = (String) in.readObject();
        i = (int)in.readObject();

    }


    public String toString(){
        return  s + ": " + i;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Blip3 bilp3 = new Blip3("bilp3", 9809);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("blip3.out"));
        out.writeObject(bilp3);
        out.close();
        System.out.println("============finish write object to file ====================");
        System.out.println("============start read object to file ====================");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("blip3.out"));
        Blip3 read3 = (Blip3)in.readObject();
        System.out.println(read3);
        in.close();
    }
}
