/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Modelo.Bit;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jeiso
 */
public class SopaBinariaNGTest {

    protected SopaBinaria sopaTest;

    public SopaBinariaNGTest() {
    }

    public void escenario() throws Exception {
        sopaTest = new SopaBinaria("src/Datos/binarios.xls");

    }

    public void escenarioError() throws Exception {
        sopaTest = new SopaBinaria("src/Datos/binarios_error.xls");
    }

    @Test
    public void pruebaCargarMatriz() throws Exception {
        escenario();

        String matriz = "1	1	1	1	0	0	\n"
                + "1	1	1	1	1	0	\n"
                + "0	0	0	0	1	0	\n"
                + "0	0	1	1	0	0	\n"
                + "1	0	1	1	1	1	\n"
                + "1	1	0	1	1	1	\n"
                + "1	1	1	1	0	1	\n"
                + "1	1	1	1	1	1	\n"
                + "0	0	0	0	1	0	\n";

        String toStringResultado = sopaTest.toString();

        assertEquals(toStringResultado, matriz, "No carga correctamente el archivo");
        try {
            escenarioError();

        } catch (Exception e) {
            String error = "Hay valores en la matriz diferentes a 0 o 1: " + "  " + 2;
            assertEquals(e.getMessage(), error, "No deberia cargar con elementos diferentes a 1 o 0");
        }

    }

    /**
     * Test of getMatrizOrdenada method, of class SopaBinaria.
     */
    @Test
    public void testGetMatrizOrdenada() throws Exception {
        escenario();
        String ordenada = "1	0	1	1	1	0	\n"
                + "1	1	1	1	1	0	\n"
                + "0	1	0	0	0	0	\n"
                + "1	0	0	0	1	0	\n"
                + "1	1	1	0	1	1	\n"
                + "0	1	1	1	1	1	\n"
                + "1	0	1	1	1	1	\n"
                + "1	1	1	1	1	1	\n"
                + "0	1	0	0	0	0	\n";

        assertEquals(this.sopaTest.getMatrizOrdenada().toString(), ordenada,
                "No retorna la matriz de paridad correctamente");

    }

    /**
     * Test of calculadorParidad method, of class SopaBinaria.
     */
    @Test
    public void testCalculadorParidad() throws Exception {
        escenario();
        int paridad[][] = {{0, 2}, {1, 1}, {2, 3}, {3, 1}, {4, 3}, {5, 1}};
        int paridadCalculada[][] = this.sopaTest.calculadorParidad();
        System.out.println("testCalculadorParidad()");
        assertEquals(paridadCalculada, paridad, "Mal calculo de paridades");
    }

}
