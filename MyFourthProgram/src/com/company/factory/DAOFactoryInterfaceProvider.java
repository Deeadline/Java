package com.company.factory;

import com.company.factory.factory.factoryImplements.*;

import java.util.HashMap;
import java.util.Map;


public class DAOFactoryInterfaceProvider implements FactoryInterface {
   private FactoryInterface instance = null;
   Map<Instance,FactoryInterface> factoryMap = new HashMap<Instance, FactoryInterface>();
    {
        factoryMap.put(Instance.XML,new XMLFactory());
        factoryMap.put(Instance.CSV,new CSVFactory());
        factoryMap.put(Instance.DB,new DBFactory());
    }
    public DAOFactoryInterfaceProvider() {
    }

    public String getCity() {
        return instance.getCity();
    }

    public String getName() {
        return instance.getName();
    }

    public int getAge() {
        return instance.getAge();
    }

    public void setAge(int age) {
        this.instance.setAge(age);
    }

    public void setCity(String city) {
        this.instance.setCity(city);
    }

    public void setName(String name) {
        this.instance.setName(name);
    }

    public void setSource(Enum Instance) {
        instance = factoryMap.get(Instance);
    }
}
