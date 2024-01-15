package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrianguloTest {



    @ParameterizedTest
    @CsvSource({
            "1,1,1,true",
            "0,0,0,false"
    })
    void testTriangulo(int a,int b,int c,boolean resuladoEsperado){
        Triangulo triangulo = new Triangulo();

       boolean res= triangulo.esTriangulo(a, b, c);
        assertEquals(resuladoEsperado,res);

    }
}
