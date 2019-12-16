package think.in.java.chapter10;

import think.in.java.chapter10.innerface.Service;
import think.in.java.chapter10.innerface.ServiceFactory;

public class Implementation1 implements Service {
    private Implementation1(){}

    @Override
    public void method1() {
        System.out.println("Implementation1 method1");
    }

    @Override
    public void method2() {
        System.out.println("Implementation1 method2");

    }

    public static ServiceFactory factory(){
        return new ServiceFactory() {
            @Override
            public Service getService() {
                return new Implementation1();
            }
        };
    }
}
