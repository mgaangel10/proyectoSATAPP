package tests;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

public class ArrayTesting {
    @Test
    @ParametizerTest
    @CsvSource({
            "1, true",
            "2, true",
            "3, true",
            "1, false",
            "6, false",
            "7, false"
    })
    @DisplayName("Prueba parametrizada siElArrayContiene")
     void testSiElArrayContieneParametrizado(int elementoLista, boolean resultadoEsperado) {

        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);

        MiClase
        boolean resultado = miClaseArray.siElArrayContiene(lista, elementoLista);


        assertEquals(resultadoEsperado, resultado);
    }

}
