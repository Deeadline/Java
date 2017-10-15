package myFirstCalculator;

public class MainCalculator {
    public static void main(String[] args) {
        CalculatorClass myCalculator = new CalculatorClass();
        CalculatorClass myCalculator2 = new CalculatorClass(5,5);

        myCalculator.setFirstNumber(5);
        myCalculator.setSecondNumber(5);
        myCalculator.addTwoNumbers(10,10);
        System.out.println(myCalculator.getResult());

        myCalculator2.addTwoNumbers();
        System.out.println(myCalculator2.getResult());
    }
}
