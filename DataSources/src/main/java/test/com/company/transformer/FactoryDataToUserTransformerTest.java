package test.com.company.transformer;

import com.company.domain.Person;
import com.company.factory.implementation.CSVFactory;
import com.company.factory.implementation.DBFactory;
import com.company.factory.implementation.WSFactory;
import com.company.factory.implementation.XMLFactory;
import com.company.factory.transformer.FactoryDataToUserTransformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FactoryDataToUserTransformerTest {
    @InjectMocks
    private FactoryDataToUserTransformer factoryDataToUserTransformer = new FactoryDataToUserTransformer();

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
        when(csvFactory.getAge()).thenReturn(1);
        when(csvFactory.getName()).thenReturn("CSV Name");
        when(csvFactory.getCity()).thenReturn("CSV City");

        when(dbFactory.getAge()).thenReturn(2);
        when(dbFactory.getCity()).thenReturn("DB City");
        when(dbFactory.getName()).thenReturn("DB Name");

        when(wsFactory.getAge()).thenReturn(3);
        when(wsFactory.getName()).thenReturn("WS Name");
        when(wsFactory.getCity()).thenReturn("WS City");

        when(xmlFactory.getAge()).thenReturn(4);
        when(xmlFactory.getName()).thenReturn("XML Name");
        when(xmlFactory.getCity()).thenReturn("XML City");
    }

    @Test
    public void populateCSVTest() {
        Person person = factoryDataToUserTransformer.populate(csvFactory);
        assertEquals(Integer.valueOf(1), person.getAge());
        assertEquals("CSV Name", person.getName());
        assertEquals("CSV City", person.getCity());
    }

    @Test
    public void populateDBTest() {
        Person person = factoryDataToUserTransformer.populate(dbFactory);
        assertEquals(Integer.valueOf(2), person.getAge());
        assertEquals("DB Name", person.getName());
        assertEquals("DB City", person.getCity());
    }

    @Test
    public void populateWSTest() {
        Person person = factoryDataToUserTransformer.populate(wsFactory);
        assertEquals(Integer.valueOf(3), person.getAge());
        assertEquals("WS Name", person.getName());
        assertEquals("WS City", person.getCity());
    }

    @Test
    public void populateXMLTest() {
        Person person = factoryDataToUserTransformer.populate(xmlFactory);
        assertEquals(Integer.valueOf(4), person.getAge());
        assertEquals("XML Name", person.getName());
        assertEquals("XML City", person.getCity());
    }
}
