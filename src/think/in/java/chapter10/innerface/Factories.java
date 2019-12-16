package think.in.java.chapter10.innerface;

import think.in.java.chapter10.Implementation1;
import think.in.java.chapter10.Implementation2;

public class Factories {

    public static void serviceCustomer(ServiceFactory factory){
        Service service = factory.getService();
        service.method1();
        service.method2();
    }


    public static void main(String[] args) {
        serviceCustomer(Implementation1.factory());
        serviceCustomer(Implementation2.factory());
        serviceCustomer(Implementation2.factory());
        boolean equals = Implementation1.factory().equals(Implementation1.factory());
        System.out.println("is factory produce same obj :" + equals);

    }
}
