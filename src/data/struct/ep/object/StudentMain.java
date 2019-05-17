package data.struct.ep.object;

import java.util.HashMap;
import java.util.HashSet;

public class StudentMain {
    public static void main(String[] args){
        Student student = new Student(3,2,"liu","zd");
        System.out.println("student hashcode"+student.hashCode());
        Student student1 = new Student(3,2,"liu","zd");

        //
        System.out.println("studetn1 hashcode" + student1.hashCode());

        System.out.println(student.equals(student1));
        HashMap<Student,Integer> hashMap = new HashMap<Student, Integer>();
        hashMap.put(student,100);
        hashMap.put(student1,99);
        System.out.println("map size is :"+ hashMap.size());

        HashSet<Student> set = new HashSet<Student>();
        set.add(student1);
        set.add(student);
        System.out.println("set size is :"+set.size());
    }
}
//3533354
//1854778591