package com.company.factory.factory.factoryImplements;

public class XMLFactory implements FactoryInterface {
    public String getCity(){
        return person.getCity()+ " XML";
    }

    public String getName() {
        return person.getName()+ " XML";
    }

    public int getAge(){
        return person.getAge();
    }

    public void setAge(int age) {
        this.person.setAge(age);
    }

    public void setCity(String city) {
        this.person.setCity(city);
    }

    public void setName(String name) {
        this.person.setName(name);
    }
}
