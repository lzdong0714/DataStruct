package think.in.java.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class InnerException extends Exception{}

class OutterException extends RuntimeException{}
@Slf4j
public class DynamicFieldsPractise {
    Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        try {
            f();
        } catch (OutterException e) {
            e.printStackTrace();
        }
    }

    public static void f()throws OutterException{
        log.info("method f()");
        try {
            g();
        }catch (InnerException e){
            throw new OutterException();
        }catch (RuntimeException e){
            log.info("=====================");
//            e.printStackTrace();
            log.info("=====================");
        }

    }

    public static void g() throws InnerException{
        log.info("method g()");
        throw new RuntimeException();
    }

}
