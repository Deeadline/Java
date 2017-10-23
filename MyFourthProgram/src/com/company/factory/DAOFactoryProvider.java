package com.company.factory;

import com.company.factory.factory.factoryImplements.Factory;

import java.util.HashMap;
import java.util.Map;

public class DAOFactoryProvider implements Factory {
    Factory instance = null;
    private DAOFactoryProvider(){}
    public String getCity(){
        return null;
    }

    public String getName() {
        return null;
    }

    public int getAge() {
        return 0;
    }

    public void setAge(int age) {

    }

    public void setCity(char city) {

    }

    public void setName(char name) {

    }
}
