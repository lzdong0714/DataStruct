package think.in.java.chapter19;

import edu.princeton.cs.algs4.In;
import lombok.extern.slf4j.Slf4j;
import think.in.java.util.TextFile;

import java.util.EnumMap;
import java.util.Iterator;

import static think.in.java.chapter19.Input.*;

/**
 *
 * 用枚举构造状态机
 */

enum Category{
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOOTHPASTE, CHIPS, SOAP, SODA),
    QUIT_TRANSACTION(ABORT_TRASCATION),
    SHUT_DOWN(STOP);

    private Input[] values;

    // 不定长参数，实际输入时数组
    Category(Input... types){
        values = types;
    }

    private static EnumMap<Input, Category> categories =
            new EnumMap<Input, Category>(Input.class);

    // 静态代码块，初始化categories,对不同的输入，确定不同的策略
    static {
        for(Category c : Category.class.getEnumConstants()){
            for(Input type : c.values){
                categories.put(type, c);
            }

        }
    }

    // 枚举作为参数，输输出枚举，枚举套娃
    public static Category categorize(Input input){
        return categories.get(input);
    }




}

@Slf4j
public class VendingMechine {

    // 公共的状态，用静态资源来表示
    private static State state = State.RESTING;

    private static int amount = 0;

    private static Input selection = null;

    enum StateDuration{ TRANSIENT } //瞬态事件
    enum State{
        RESTING{
            void next(Input input){
                switch (Category.categorize(input)){
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if(amount <= input.amount())
                            log.info("insufficient money for " + selection);
                        else state = DISPENSING;
                    }
            }
        },

        DISPENSING{

        };
        private boolean isTransient = false;
        State(StateDuration trans){ isTransient = false;}
        State(){}
        void next(Input input){
            throw new RuntimeException("Only call next(Input input) for non-transient states");
        }

        void next(){
            throw new RuntimeException("Only call next() for StateDuration.TRANSIENT states");
        }

        void output(){ log.info("{}", amount);}


    }




    public static void main(String[] args) {

        Iterator<String> iterator =
                new TextFile("").iterator();
    }
}
