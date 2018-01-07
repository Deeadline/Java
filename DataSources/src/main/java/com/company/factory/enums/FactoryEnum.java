package com.company.factory.enums;

import com.company.factory.Factory;
import com.company.factory.implementation.CSVFactory;
import com.company.factory.implementation.DBFactory;
import com.company.factory.implementation.WSFactory;
import com.company.factory.implementation.XMLFactory;

public enum FactoryEnum {
    XML,
    DBF,
    WSF,
    CSV;

    public static Factory getFactory(FactoryEnum factory) {
        switch (factory) {

            case XML:
                return new XMLFactory();
            case DBF:
                return new DBFactory();
            case WSF:
                return new WSFactory();
            case CSV:
                return new CSVFactory();
        }
        return null;
    }
}
