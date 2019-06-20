package thread.base.chapter1;



public  class TemplateMethod {

    public final void print(String msg){
        System.out.println("##################");
        wrapPrint(msg);
        System.out.println("##################");
    }

    //提供的模板方法，同Thread中的run()方法一样
    protected void wrapPrint(String msg){};

    public static void main(String[] args){
        TemplateMethod t1 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String msg) {
                System.out.println(msg);
            }
        };

        t1.print("this is message");
    }

}

//public abstract class TemplateMethod {
//
//    public final void print(String msg){
//        System.out.println("##################");
//
//        wrapPrint(msg);
//        System.out.println("##################");
//    }
//
//    protected abstract void wrapPrint(String msg);
//
//}
