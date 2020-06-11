package demo.base;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Liu Zhendong
 * @Description 空对象异常测试
 * @createTime 2020年05月13日 10:01:00
 */
public class NPETest {

    public static void main(String[] args) {
        NPETest npeTest = new NPETest();
//        npeTest.test_1();
        npeTest.test_2();
    }

    public void test_1(){

        PersonClass personClass = new PersonClass();
        Person monitro = personClass.getMonitro(); // 没有NullPointerException
        List<Person> personList = personClass.getPersonList(); // 没有NullPointerException
        boolean present = Optional.ofNullable(monitro).isPresent();
        boolean b = monitro == null;
        System.out.println(present);
        System.out.println(b);
//        personList.get(0);              // NullPointerException
//        monitro.getName();             // NullPointerException
//        int age = monitro.getAge(); // NullPointerException

    }

    public void test_2(){
        List<String> stringList = null;

        System.out.println(stringList.isEmpty());

        stringList = new LinkedList<>();
        System.out.println(stringList.isEmpty());
    }

}


@Data
class Person{
    int age;
    String name;
}

@Data
class PersonClass{

    List<Person> personList;
    Person monitro;
    int number;
    String className;
}
