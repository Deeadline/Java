package com.company.domain;

public class Person {
    private int age = 15;
    private String name = "Mariusz";
    private String city = "Warszawa";

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {  this.city = city; }
}
