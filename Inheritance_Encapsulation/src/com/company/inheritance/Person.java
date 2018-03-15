package com.company.inheritance;

/*final*/ public class Person {
    private String firstName;
    private String lastName;
    private Integer age;
    private Trainer trainer = new Trainer();
    final private Float exampleNumber;

    public Person(String firstName, String lastName, Integer age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        exampleNumber = 0.5F;
    }

    public Float getExampleNumber() {
        return exampleNumber;
    }
/*    public void setExampleNumber(Float number){
        this.exampleNumber = number;
    }*/

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    protected void PrintPerson() {
        System.out.print(firstName + " " + lastName + " " + age + " " + trainer.showID() + " ");
    }
}
