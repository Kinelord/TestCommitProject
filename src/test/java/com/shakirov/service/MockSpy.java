package com.shakirov.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockSpy {

    @Mock
    private List<String> mockList;

    @Spy  // Применяется когда нужно заглушить только часть методов, а другую часть оставить рабочей
    private List<String> spyList = new ArrayList<>();

    @Test
    public void testMockList() {
        // По умолчанию вызов методов макета объекта ничего не даст
        mockList.add("test");

        Mockito.verify(mockList).add("test");
        assertEquals(0, mockList.size());
        assertNull(mockList.get(0));
    }

    @Test
    public void testSpyList() {
        // Шпионский объект вызовет реальный метод, если он не заглушка
        spyList.add("test");

        Mockito.verify(spyList).add("test");
//        assertEquals(1, spyList.size());
//        assertEquals("test", spyList.get(0));
    }

    @Test
    public void testMockWithStub() {
        // Попробуйте заглушить метод
        String expected = "Mock 100";
        when(mockList.get(100)).thenReturn(expected);

        assertEquals(expected, mockList.get(100));
    }

    @Test
    public void testSpyWithStub() {
        //Заглушка шпионского метода приведет к тому же результату, что и макет объекта
        String expected = "Spy 100";
        //Обратите внимание на использование doReturn перед when
        doReturn(expected).when(spyList).get(100);

        assertEquals(expected, spyList.get(100));
    }
}

