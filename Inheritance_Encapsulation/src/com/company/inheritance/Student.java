package com.company.inheritance;

public class Student extends Person {
    private String index;
    public Student(String firstName, String lastName, Integer age,String index) {
        super(firstName, lastName, age);
        this.index = index;
    }

    @Override
    public void PrintPerson() {
        super.PrintPerson();
        System.out.println(index);
    }
    @Override
    public void setAge(Integer age){
        super.setAge(age*10);
    }
}
