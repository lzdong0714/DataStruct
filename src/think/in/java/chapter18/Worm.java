package think.in.java.chapter18;

import lombok.extern.slf4j.Slf4j;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Random;

@Slf4j
class Data implements Serializable {
    private int n;
    public Data(int n){this.n = n; }
    public String toString(){
        return Integer.toString(n);
    }


}

public class Worm implements Serializable{
    private  Logger logger = LoggerFactory.getLogger(getClass());
    private static Random random = new Random(666);

    private Data[] d = {
      new Data(random.nextInt(10)),
      new Data(random.nextInt(10)),
      new Data(random.nextInt(10))
    };
    private Worm next;
    private char c;

    public Worm(){
        System.out.println("Default Construct ;");
    }


    public Worm(int i, char x){
        System.out.println("Worm construct :" + i);
        c = x;
        if(--i > 0){

            next = new Worm(i, (char)(x + 1));
        }
    }

    public String toString(){
        StringBuilder builder = new StringBuilder(":");
        builder.append(c);
        builder.append("(");
        for(Data data : d){
            builder.append(data);
        }
        builder.append(")");
        if(next != null){
            builder.append(next);
        }
        return builder.toString();

    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Worm worm = new Worm(6, 'a');
        System.out.println("w = " + worm);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"));
        out.writeObject("Worm storage \n");
        out.writeObject(worm);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
        String s = (String) in.readObject();
        Worm w2  = (Worm) in.readObject();
        System.out.println(s + " : "+ w2);

        ByteArrayOutputStream byteOut =
                new ByteArrayOutputStream();

        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject("Worm, storage \n");
        objOut.writeObject(w2);
        objOut.flush();

        ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(
                byteOut.toByteArray() ) );

        s=(String) objIn.readObject();
        Worm w3 = (Worm) objIn.readObject();
        System.out.println(s + ":" + w3);

    }


}