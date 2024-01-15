package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTest {


    @ParameterizedTest
    @CsvSource({
            "1, true",
            "2, true",
            "3, true",
            "1, true",
            "6, false",
            "7, false"
    })
    @DisplayName("Prueba parametrizada siElArrayContiene")
    public void testSiElArrayContieneParametrizado(int elementoLista, boolean resultadoEsperado) {

        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        MiClaseArray miClaseArray = new MiClaseArray();

        boolean resultado = miClaseArray.elementoContieneArray(lista,elementoLista);


        assertEquals(resultadoEsperado, resultado);
    }



}
