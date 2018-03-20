import com.company.inheritance.Animal;
import com.company.inheritance.Cat;
import com.company.inheritance.Dog;

public class Main {

    public static void main(String[] args) {
        Animal a1 = new Animal(),
                a2 = new Animal();
        Dog d1 = new Dog(),
                d2 = new Dog("Arnold");
        Cat c1 = new Cat(),
                c2 = new Cat("Kitku");

        animalDialog(d2,c2);
       // Animal a2 = new Animal("X"); -- > nie zadziała gdyż dostęp do tego konstruktora jest chroniony (tylko klasy pochodne mają do niego dostęp)
    }
    private static void animalDialog(Animal a1, Animal a2){
        a1.speak();
        a2.speak();
        System.out.println("---------");
    }
}
