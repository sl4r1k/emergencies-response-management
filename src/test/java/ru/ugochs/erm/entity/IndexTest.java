package ru.ugochs.erm.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

class IndexTest {
    @Test
    void shouldThrowIllegalArgumentException() {
        Index firstIndex = new Index(Mockito.anyString());
        Index secondIndex = new Index(firstIndex, Mockito.anyString());
        Index thirdIndex = new Index(secondIndex, Mockito.anyString());
        Class<IllegalArgumentException> expectedException = IllegalArgumentException.class;
        Executable executable = () -> new Index(thirdIndex, Mockito.anyString());
        Assertions.assertThrows(expectedException, executable);
    }
}
