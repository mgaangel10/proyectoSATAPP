package org.example;

public class Triangulo {

    public boolean esTriangulo(int a,int b,int c){
        int suma = a+b+c;
        if (suma>0){
            return true;
        }else {
            return false;
        }
    }

}
