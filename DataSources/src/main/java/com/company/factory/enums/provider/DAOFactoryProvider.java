package com.company.factory.enums.provider;

import com.company.factory.Factory;
import com.company.factory.enums.factory.FactoryEnum;
import com.company.factory.implementation.CSVFactory;
import com.company.factory.implementation.DBFactory;
import com.company.factory.implementation.WSFactory;
import com.company.factory.implementation.XMLFactory;

import java.util.HashMap;
import java.util.Map;


public enum DAOFactoryProvider {
    INSTANCE;

    private Factory factory;

    private static final Map<FactoryEnum, Factory> factoryEnumToFactoryMap;

    static {
        factoryEnumToFactoryMap = new HashMap<FactoryEnum, Factory>();
        factoryEnumToFactoryMap.put(FactoryEnum.XML, new XMLFactory());
        factoryEnumToFactoryMap.put(FactoryEnum.CSV, new CSVFactory());
        factoryEnumToFactoryMap.put(FactoryEnum.DBF, new DBFactory());
        factoryEnumToFactoryMap.put(FactoryEnum.WSF, new WSFactory());
    }

    public void setSourceOfData(FactoryEnum factoryEnum) {
        factory = factoryEnumToFactoryMap.get(factoryEnum);
    }

    public Factory getService() {
        return factory;
    }
}
