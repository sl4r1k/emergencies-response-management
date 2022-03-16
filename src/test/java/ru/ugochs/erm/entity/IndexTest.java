package ru.ugochs.erm.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class IndexTest {
    @Test
    void shouldThrowIllegalArgumentException() {
        Index firstIndex = new Index();
        Index secondIndex = new Index();
        secondIndex.setParent(firstIndex);
        Index thirdIndex = new Index();
        thirdIndex.setParent(secondIndex);
        Class<IllegalArgumentException> expectedException = IllegalArgumentException.class;
        Executable executable = () -> new Index().setParent(thirdIndex);
        Assertions.assertThrows(expectedException, executable);
    }
}
