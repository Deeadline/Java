package com.company.factory.implementation;

import com.company.factory.Factory;

public class XMLFactory implements Factory {
    @Override
    public String getName() {
        return "XML";
    }

    @Override
    public Integer getAge() {
        return 4;
    }
    @Override
    public void setCity(String city) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setAge(Integer age) {

    }

    @Override
    public String getCity() {
        return "XML City";
    }
}
