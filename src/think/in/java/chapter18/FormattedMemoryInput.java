package think.in.java.chapter18;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

@Slf4j
public class FormattedMemoryInput {

    public static void main(String[] args) throws IOException {
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(
                    BufferedInputFile.read("FormattedMemoryInput.java").getBytes()
            ));
            while (true){
                System.out.println((char)in.readByte());
            }

        }catch (EOFException e){
            log.info("{}", e);
        }
    }
}
