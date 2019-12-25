package think.in.java.chapter18.bigfile;

import edu.princeton.cs.algs4.FordFulkerson;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MapperedIO {
    private static int numOfInts = 4000000;
    private static int numOfBufffInts = 200000;

    private abstract static class Tester{
        private String name;
        public Tester(String name){this.name = name;}

        public void runTest(){
            System.out.println(name + " :");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("% .2f \n", duration/1.0e9);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }

        public abstract void test() throws IOException;
    }

    static String fileIn = "data.txt";
    private static Tester[] tests = {
            new Tester("Stream Writer") {
                @Override
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
                            new FileOutputStream(new File(fileIn))
                        ));

                    for(int i = 0; i < numOfInts; i++){
                        dos.writeInt(i);
                    }
                    dos.close();
                }
            },

            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(fileIn,"rw")
                            .getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
                            .asIntBuffer();

                    for(int i = 0; i < numOfInts; i++){
                        ib.put(i);
                    }
                    fc.close();
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                    DataInputStream dis = new DataInputStream(new BufferedInputStream(
                            new FileInputStream(fileIn)
                    ));

                    for(int i = 0; i<numOfInts; i++){
                       dis.readInt();
                    }
                    dis.close();
                }
            },

            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new FileInputStream(new File(fileIn)).getChannel();

                    MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
                    IntBuffer ib = byteBuffer.asIntBuffer();
                    while (ib.hasRemaining()){
                        ib.get();
                    }
                    fc.close();
                }
            },

            new Tester("Mapper Read and Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(
                            new File(fileIn),"rw"
                    ).getChannel();

                    MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
                    IntBuffer ib = byteBuffer.asIntBuffer();
                    ib.put(0);
                    for(int i = 1; i<numOfBufffInts; i++){
                        ib.put(ib.get(i - 1));
                    }
                    fc.close();
                }

            }

    };

    public static void main(String[] args) {
        for(Tester tester : tests){
            tester.runTest();
        }
    }
}
