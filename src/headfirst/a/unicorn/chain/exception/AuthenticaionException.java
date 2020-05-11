package headfirst.a.unicorn.chain.exception;

//身份异常
public class AuthenticaionException extends Exception {
    public AuthenticaionException(){}
    public AuthenticaionException(String msg){
        super(msg);
    }
}
