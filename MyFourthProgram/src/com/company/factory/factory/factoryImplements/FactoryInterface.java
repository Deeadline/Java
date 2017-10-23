package com.company.factory.factory.factoryImplements;

import com.company.domain.Person;

public interface FactoryInterface {
    public Person person = new Person();

    public String getName();

    public void setName(String name);

    public int getAge();

    public void setAge(int age);

    public String getCity();

    public void setCity(String city);
}
