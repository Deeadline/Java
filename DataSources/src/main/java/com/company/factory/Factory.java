package com.company.factory;


public interface Factory {
    String getName();
    String getCity();
    Integer getAge();
    void setName(String name);
    void setAge(Integer age);
    void setCity(String city);
}
