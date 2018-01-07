package com.company.factory;

public class DAOFactoryProvider implements Factory {
    Factory instance = null;

    public void setInstance(Factory factoryEnum) {
        instance = factoryEnum;
    }

    @Override
    public String getCity() {
        return instance.getCity();
    }

    @Override
    public void setCity(String city) {
        instance.setCity(city);

    }

    @Override
    public String getName() {
        return instance.getName();
    }

    @Override
    public Integer getAge() {
        return instance.getAge();
    }

    @Override
    public void setName(String name) {
        instance.setName(name);
    }

    @Override
    public void setAge(Integer age) {
        instance.setAge(age);
    }
}
