/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Util.LeerMatriz_Excel;
import java.io.IOException;

/**
 *
 * @author madar
 */
public class PruebaLeerExcel_Binario {
    public static void main(String[] args) throws IOException {
        LeerMatriz_Excel myExcel=new LeerMatriz_Excel("src/Datos/binarios.xls",0);
        String datos[][]=myExcel.getMatriz();
        System.out.println("Matriz De ejemplo:");
        impMatriz(datos);
        System.out.println("Esta matriz es de error y debería generarle su excepción, \n en la última columuna de la primera fila cntiene un número diferente a 0 o 1");
        myExcel=new LeerMatriz_Excel("src/Datos/binarios_error.xls",0);
        datos=myExcel.getMatriz();
        impMatriz(datos);
    }
    
    
    public static void impMatriz(String datos[][])
    {
    
        for(String filas[]:datos)
        {
                for(String datoColumna:filas)
                    System.out.print(datoColumna+"\t");
        System.out.println("\n");
        }
    }
}
