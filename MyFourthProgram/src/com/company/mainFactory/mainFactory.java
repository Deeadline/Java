package com.company.mainFactory;

import com.company.factory.DAOFactoryInterfaceProvider;
import com.company.factory.factory.factoryImplements.Instance;

public class mainFactory {
    public static void main(String[] args) {
        DAOFactoryInterfaceProvider A = new DAOFactoryInterfaceProvider();
        A.setSource(Instance.XML);
          System.out.println(A.getAge() + " " + A.getName() + " " + A.getCity());
        A.setSource(Instance.CSV);
          System.out.println(A.getAge() + " " + A.getName() + " " + A.getCity());
        A.setAge(25);
        A.setSource(Instance.DB);
        System.out.println(A.getAge() + " " + A.getName() + " " + A.getCity());
    }
}
