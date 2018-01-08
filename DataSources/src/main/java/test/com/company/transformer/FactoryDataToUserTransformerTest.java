package test.com.company.transformer;

import com.company.domain.Person;
import com.company.factory.implementation.CSVFactory;
import com.company.factory.transformer.FactoryDataToUserTransformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class FactoryDataToUserTransformerTest {
    @InjectMocks
    private FactoryDataToUserTransformer factoryDataToUserTransformer = new FactoryDataToUserTransformer();

    @Mock
    private CSVFactory csvFactory;

    @Before
    public void setUp(){
        initMocks(this);
        when(csvFactory.getAge()).thenReturn(4);
        when(csvFactory.getName()).thenReturn("Name");
    }

    private void initMocks(FactoryDataToUserTransformerTest factoryDataToUserTransformerTest) {
    }

    @Test
    public void populateTest(){
        Person person = factoryDataToUserTransformer.populate(csvFactory);
        assertEquals(Integer.valueOf(4),person.getAge());
        assertEquals("Name",person.getName());
    }
}
