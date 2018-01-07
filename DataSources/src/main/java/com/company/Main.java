package com.company;

import com.company.factory.DAOFactoryProvider;
import com.company.factory.enums.FactoryEnum;
import com.company.factory.transformer.FactoryDataToUserTransformer;

import static com.company.factory.enums.FactoryEnum.getFactory;

public class Main {

    public static void main(String[] args) {
        DAOFactoryProvider daoFactoryProvider = new DAOFactoryProvider();
        daoFactoryProvider.setInstance(getFactory(FactoryEnum.CSV));
        System.out.println(daoFactoryProvider.getName());
        daoFactoryProvider.setInstance(getFactory(FactoryEnum.DBF));
        System.out.println(daoFactoryProvider.getName());
        FactoryDataToUserTransformer factoryDataToUserTransformer = new FactoryDataToUserTransformer();
        System.out.println(factoryDataToUserTransformer.populate(daoFactoryProvider).toString());
    }
}
