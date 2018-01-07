package com.company.domain;

public class Person {
    private String name;
    private Integer age;
    private String city;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", city=" + city +

                '}';
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
