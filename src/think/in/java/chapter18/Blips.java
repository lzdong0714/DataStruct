package think.in.java.chapter18;

import java.io.*;

// 对复杂类的序列化 继承Externalizable，必须调用内部应用的对象递归的序列化
public class Blips {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Blip1 blip1 = new Blip1();
        Blip2 blip2 = new Blip2();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        System.out.println("write obj to file");
        out.writeObject(blip1);
        out.writeObject(blip2);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
        System.out.println("create obj from file");
        Blip1 read_blip1 = (Blip1) in.readObject();
        System.out.println("=============================");
        Blip2 read_blip2 = (Blip2) in.readObject();

    }
}
    class Blip1 implements Externalizable{

        public Blip1(){
            System.out.println("Blipl construct");
        }

        // 序列化与反序列化时调用
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            System.out.println("Blipl writeExternal");
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            System.out.println("Blipl readExternal");
        }

    }

    class Blip2 implements Externalizable{
        public Blip2(){
            System.out.println("Bilp2 construct");
        }


        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            System.out.println("Blip2 writeExternal");

        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            System.out.println("Blip2 readExternal");

        }
    }


