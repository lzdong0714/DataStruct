package think.in.java.chapter10;

import think.in.java.chapter10.innerface.Service;
import think.in.java.chapter10.innerface.ServiceFactory;

public class Implementation2 implements Service {
    private Implementation2(){}
    private int i = 0;

    @Override
    public void method1() {
        i+=1;
        System.out.println("Implementation2 method1" + i);
    }

    @Override
    public void method2() {
        System.out.println("Implementation2 method1");

    }


    public static ServiceFactory factory(){
        return new ServiceFactory() {
            @Override
            public Service getService() {
                return new Implementation2();
            }
        };
    }
}
