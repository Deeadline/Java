package myFirstCalculator;

public class CalculatorClass {
    public CalculatorClass(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.result = 0;
    }
    public CalculatorClass(){
        this.result = 0;
    }
    public void addTwoNumbers(int firstNumber, int secondNumber){
        result = firstNumber + secondNumber;
    }
    public void setFirstNumber(int firstNumber){
        this.firstNumber = firstNumber;
    }
    public void setSecondNumber(int secondNumber){
        this.secondNumber = secondNumber;
    }
    public int getResult(){
        return this.result;
    }
    public void addTwoNumbers(){
        result = firstNumber + secondNumber;
    }
    private int firstNumber;
    private int secondNumber;
    private int result;
}
