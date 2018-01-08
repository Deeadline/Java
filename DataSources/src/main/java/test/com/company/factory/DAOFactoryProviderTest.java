package test.com.company.factory;

import com.company.factory.enums.FactoryEnum;
import com.company.factory.implementation.CSVFactory;
import com.company.factory.implementation.DBFactory;
import com.company.factory.implementation.WSFactory;
import com.company.factory.implementation.XMLFactory;
import com.company.factory.provider.DAOFactoryProvider;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DAOFactoryProviderTest {
    @Test
    public void setSourceOfDataCSV() {
        DAOFactoryProvider.INSTANCE.setSourceOfData(FactoryEnum.CSV);
        assertTrue(DAOFactoryProvider.INSTANCE.getService() instanceof CSVFactory);
    }

    @Test
    public void setSourceOfDataDB() {
        DAOFactoryProvider.INSTANCE.setSourceOfData(FactoryEnum.DBF);
        assertTrue(DAOFactoryProvider.INSTANCE.getService() instanceof DBFactory);
    }

    @Test
    public void setSourceOfDataXML() {
        DAOFactoryProvider.INSTANCE.setSourceOfData(FactoryEnum.XML);
        assertTrue(DAOFactoryProvider.INSTANCE.getService() instanceof XMLFactory);
    }

    @Test
    public void setSourceOfDataWS() {
        DAOFactoryProvider.INSTANCE.setSourceOfData(FactoryEnum.WSF);
        assertTrue(DAOFactoryProvider.INSTANCE.getService() instanceof WSFactory);
    }
}
