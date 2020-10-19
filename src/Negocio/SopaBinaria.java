/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Interface.IBinario;
import Modelo.Bit;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author madar
 */
public class SopaBinaria implements IBinario {

    private Bit mySopaBinaria[][];

    public Bit[][] getMySopaBinaria() {
        return mySopaBinaria;
    }

    public void setMySopaBinaria(Bit[][] mySopaBinaria) {
        this.mySopaBinaria = mySopaBinaria;
    }


    public SopaBinaria() {
    }

    public SopaBinaria(String rutaArchivoExcel) throws IOException, Exception {
        int contadorUnos = 0;
        HSSFWorkbook archivoExcel = new HSSFWorkbook(new FileInputStream(rutaArchivoExcel));
        //Obtiene la hoja 1
        HSSFSheet hoja = archivoExcel.getSheetAt(0);
        //Obtiene el número de la última fila con datos de la hoja.
        int canFilas = hoja.getLastRowNum() + 1;
        this.mySopaBinaria = new Bit[canFilas][];
        for (int i = 0; i < canFilas; i++) {
            HSSFRow filas = hoja.getRow(i);
            int cantCol = filas.getLastCellNum();
            this.mySopaBinaria[i] = new Bit[cantCol];

            for (int j = 0; j < cantCol; j++) {

                String valor = filas.getCell(j).getStringCellValue();

                Bit nuevo = new Bit();
                switch (valor) {
                    case "0":
                        nuevo.setValor(false);
                        this.mySopaBinaria[i][j] = nuevo;
                        break;

                    case "1":
                        nuevo.setValor(true);
                        this.mySopaBinaria[i][j] = nuevo;
                        contadorUnos++;
                        break;

                    default:
                        throw new Exception("Hay valores en la matriz diferentes a 0 o 1: " + "  " + valor);

                }

            }

        }
        
    }

    @Override
    public SopaBinaria getMatrizOrdenada() {

        /**
         * :)
         */
        SopaBinaria matrizParidad = new SopaBinaria();
        int listaParidades[][] = calculadorParidad();
        sort(listaParidades);
        int par = 0;
        Bit[][] sopaOrdenada = new Bit[mySopaBinaria.length][mySopaBinaria[0].length];
        for (int i = 0; i < mySopaBinaria[0].length; i++) {
            for (int j = 0; j < mySopaBinaria.length; j++) {
                int bit = listaParidades[par][0];
                sopaOrdenada[j][i] = mySopaBinaria[j][bit];

            }
            par++;

        }

        matrizParidad.setMySopaBinaria(sopaOrdenada);

        return matrizParidad;

    }

    public void sort(int arr[][]) {
        int mayor = -1;
        int mayotAnt = 0;
        int mayorI = 0;
        int indiceI = 0;
        int j = 0;
        int colAnt=0;
        while (j != arr.length - 1) {
            mayor = -1;
            for (int i = indiceI; i < arr.length; i++) {
                if (arr[i][1] > mayor) {
                    mayor = arr[i][1];
                    mayorI = i;
                    mayotAnt = i;
                    colAnt =arr[i][0];
                }
                if (arr[i][1] == mayor) {
                    if(colAnt<arr[i][0])
                    mayorI = mayotAnt;
                    else mayorI=i;
                    mayotAnt = i;
                    colAnt=arr[i][0];
                }

            }
            indiceI++;
            swap(arr, mayorI, j);
            j++;

        }

    }

    private void swap(int arr[][], int i, int j) {
        int anterior[] = arr[i];
        int nuevo[] = arr[j];
        arr[i] = nuevo;
        arr[j] = anterior;
    }

    @Override
    public int[][] calculadorParidad() {
        int contParidad = 0;
        int contUnos = 0;
        int contCeros = 0;
        int paridadCol[][] = new int[this.mySopaBinaria[0].length][2];
        //i:Columnas
        //j: Filas
        for (int i = 0; i < this.mySopaBinaria[0].length; i++) {
            for (int j = 0; j < this.mySopaBinaria.length; j++) {

                if (this.mySopaBinaria[j][i].isValor()) {
                    contUnos++;
                    contCeros = 0;
                } else {

                    if (contUnos % 2 == 0 && contUnos != 0) {
                        if (contCeros == 0) {
                            contParidad++;
                        }
                    }
                    contUnos = 0;
                    contCeros++;
                }
                if (this.mySopaBinaria.length - 1 == j) {
                    if (contUnos % 2 == 0 && contUnos != 0) {
                        if (contCeros == 0) {
                            contParidad++;
                        }
                    }
                }

            }
            int col[] = new int[2];
            col[0] = i;
            col[1] = contParidad;
            paridadCol[i] = col;
            contParidad = 0;
            contCeros = 0;
            contUnos = 0;
        }
        return paridadCol;

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String msg = "";
        for (Bit filas[] : this.mySopaBinaria) {
            for (Bit valor : filas) {
                msg += valor.toString() + "\t";
            }
            msg += "\n";
        }

        return msg;
    }
     /**
     * Devuelve la matriz de bits en forma de matriz de String
     * @return 
     */
    public String[][] getMatrizEnString() {

        String matrizEnString[][] = new String[this.mySopaBinaria.length][this.mySopaBinaria[0].length];
        String valor = "0";
        for (int i = 0; i < mySopaBinaria.length; i++) {
            matrizEnString[i] = new String[this.mySopaBinaria[i].length];
            for (int j = 0; j < matrizEnString[i].length; j++) {
                valor = this.mySopaBinaria[i][j].toString();
                matrizEnString[i][j] = valor;
            }

        }
        return matrizEnString;
    }
    /**
     * Este es solo un método de practica con recursividad para poder pintar las casillas,
     * ademas encuentra la paridad de cada columna.
     * El metodo marca cada casilla que forme parte de la solución como bit de paridad.
     * NO FORMA PARTE DE LA SOLUCIÓN DEL EJERCICIO.
     * @param i Fila
     * @param j Columna
     * @param contadorUnos
     * @param paridad
     * @return 
     */
    public int marcarParidad(int i, int j,int contadorUnos,int paridad){
    
        paridad=0;
        if(this.mySopaBinaria[i][j].isValor()){
            contadorUnos++;
            if(contadorUnos%2==0 && contadorUnos!=0 
                    && i!=this.mySopaBinaria.length-1 
                    && !this.mySopaBinaria[i+1][j].isValor() ){
                paridad++;
                this.mySopaBinaria[i][j].setParidad(true);
 
            }
            if( i==this.mySopaBinaria.length-1 ){
                if(contadorUnos%2==0 && contadorUnos!=0 ){
                    paridad++;
                    this.mySopaBinaria[i][j].setParidad(true);
                }
                return paridad;
            }
            int antParidad=paridad;
             paridad+=marcarParidad(i+1, j, contadorUnos, paridad);
             if(paridad>antParidad)this.mySopaBinaria[i][j].setParidad(true);
               
        }
        else{
            
            contadorUnos=0;
            if( i==this.mySopaBinaria.length-1 )return paridad;                    
             paridad+=marcarParidad(i+1, j, contadorUnos, paridad);
       
        }
        return paridad;
    }
}
