package com.company.factory.enums.factory;

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
}
