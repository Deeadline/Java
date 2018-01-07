package com.company.factory.transformer;

import com.company.domain.Person;
import com.company.factory.DAOFactoryProvider;

public class FactoryDataToUserTransformer {
    public Person populate(DAOFactoryProvider input) {
        Person output = new Person();
        output.setAge(input.getAge());
        output.setName(input.getName());
        output.setCity(input.getCity());
        return output;
    }
}
