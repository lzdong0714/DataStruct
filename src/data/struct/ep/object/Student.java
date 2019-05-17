package data.struct.ep.object;

public class Student {
    int grade;
    int cls;
    String firstName;
    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int B= 32;
        int has = 0;
        has = has*B + grade;
        has = has*B + cls;
        has = has*B+ firstName.toLowerCase().hashCode();
        has = has*B + lastName.toLowerCase().hashCode();
        return has;
    }

    //简单的hash指相等不会对map set 集合的键值有影响，必须覆盖equal函数
//    @Override
//    public boolean equals(Object obj) {
//        if(obj == this)
//            return true;
//
//        if (obj == null)
//            return false;
//
//        if(getClass() != obj.getClass())
//            return false;
//
//        Student another = (Student) obj;
//        return this.grade==another.grade&&
//                this.cls == another.cls&&
//                this.lastName.equals(((Student) obj).lastName)&&
//                this.firstName.equals(((Student) obj).firstName);
//    }
}