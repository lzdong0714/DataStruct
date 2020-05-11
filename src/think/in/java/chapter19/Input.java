package think.in.java.chapter19;

public enum Input {
    NICKEL(5), DIME(10), QUARTER(25),
    DOLLAR(100), TOOOTHPASTE(200),
    CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRASCATION{
        public int amout(){
            throw new RuntimeException("ABORT.amount");
        }
    },

    STOP{
        public int amount(){
            throw new RuntimeException("SHUT_DOWN.amount");
        }
    };

    int value;
    Input(int value){
        this.value = value;
    }
    Input(){}

    int amount(){return  this.value;}
}
