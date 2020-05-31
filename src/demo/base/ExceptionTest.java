package demo.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年05月22日 10:48:00
 */

@Slf4j
public class ExceptionTest {



    public static void main(String[] args)throws Exception {
        ExceptionTest exceptionTest = new ExceptionTest();
        exceptionTest.test();
    }

    private void test(){

        try {
            System.out.println("throw exception");
            throwEX();
        }catch (Exception e){
            throw this.new MyException("lllll");
        }
        log.info("{}", "eeeee");
    }

    private void throwEX(){
        throw new IllegalArgumentException("illge");
    }

    class MyException extends RuntimeException{
        MyException(String msg){
            super(msg);
        }
    }
}
