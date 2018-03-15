import com.company.inheritance.Student;
import com.company.inheritance.Person;

public class Main {

    public static void main(String[] args) {
        Person ktos = new Person("Ktos", "Nowy", 19);
        ktos.setAge(5);
        System.out.println(ktos.getAge());
        Student minionAndrzej = new Student("Andrzej", "Minion", 20,"123456");
        /*ktos.PrintPerson();*/
        minionAndrzej.PrintPerson();
        minionAndrzej.setAge(5);
        System.out.println(minionAndrzej.getAge());
    }
}
