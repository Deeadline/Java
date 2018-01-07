package test.java;

import com.company.factory.enums.FactoryEnum;
import com.company.factory.implementation.CSVFactory;
import com.company.factory.implementation.DBFactory;
import com.company.factory.implementation.WSFactory;
import com.company.factory.implementation.XMLFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import com.company.factory.DAOFactoryProvider;
import org.mockito.Mock;

import static com.company.factory.enums.FactoryEnum.getFactory;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DAOFactoryProviderTest {
    @InjectMocks
    private DAOFactoryProvider daoFactoryProvider = new DAOFactoryProvider();
    @Mock
    private CSVFactory csvFactory;
    @Mock
    private DBFactory dbFactory;
    @Mock
    private WSFactory wsFactory;
    @Mock
    private XMLFactory xmlFactory;

    @Before
    public void setUp() {
        initMocks(this);
        when(csvFactory.getName()).thenReturn("csvFactoryData");
        when(wsFactory.getName()).thenReturn("wsFactoryData");
        when(dbFactory.getName()).thenReturn("dbFactoryData");
        when(xmlFactory.getName()).thenReturn("xmlFactoryData");
    }

    @Test
    public void setInstanceTestCSV() {
        daoFactoryProvider.setInstance(getFactory(FactoryEnum.CSV));
        assertEquals("csvFactoryData", csvFactory.getName());
        System.out.println(csvFactory.getName());
    }

    @Test
    public void setInstanceTestDB() {
        daoFactoryProvider.setInstance(getFactory(FactoryEnum.DBF));
        assertEquals("dbFactoryData", dbFactory.getName());
        System.out.println(dbFactory.getName());
    }

    @Test
    public void setInstanceTestWS() {
        daoFactoryProvider.setInstance(getFactory(FactoryEnum.WSF));
        assertEquals("wsFactoryData", wsFactory.getName());
        System.out.println(wsFactory.getName());
    }

    @Test
    public void setInstanceTestXML() {
        daoFactoryProvider.setInstance(getFactory(FactoryEnum.XML));
        assertEquals("xmlFactoryData", xmlFactory.getName());
        System.out.println(xmlFactory.getName());
    }
}
