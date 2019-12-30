package think.in.java.chapter12;

class BaseballException extends Exception{}
class Foul extends BaseballException{}
class Strike extends Foul{}

abstract class Inning{
    public Inning()throws BaseballException{};
    public void event() throws BaseballException{
        // Doesn't throw actual throw anything
    }
    public void eventDo() throws BaseballException{
        // Doesn't throw actual throw anything
        System.out.println("do sth");
    }

    public abstract void atBat()throws Strike, Foul;
    public void walk(){}
}

class StormException extends Exception{}
class RainedOut extends StormException{}
class PopFoul extends Foul{}
interface Storm{
    void event() throws RainedOut;
    void rainHard() throws RainedOut;
    void eventDo() throws RainedOut;
}

public class StormInning {
    public StormInning()throws BaseballException,RainedOut{};
    public StormInning(String s)throws BaseballException,RainedOut{};

    public void event()  {}
    public void eventDo() {

    }

}
