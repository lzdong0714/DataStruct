package think.in.java.chapter19;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public class Enums {

    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values){
        return values[rand.nextInt(values.length)];
    }


    public static void main(String[] args) {

        IntStream.range(0, 16)
                .forEach(item-> {
                    Colour random = random(Colour.class);
                    String str = random + " ";
                    System.out.println(str);
                });
        int ord = 1;
        Colour value = Colour.values()[ord];
        switch (value){
            case Black: log.info("{}", Colour.Black);
            default: log.info("wrong guess");
        }
    }
}

