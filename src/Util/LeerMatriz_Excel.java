/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author madar
 */
public class LeerMatriz_Excel {
    
    
    String matriz[][];
    
    public LeerMatriz_Excel()
    {
        
        
    
    
    }
    
    
    public LeerMatriz_Excel(String nombreArchivo, int numHoja) throws IOException
    {
     HSSFWorkbook archivoExcel = new HSSFWorkbook(new FileInputStream(nombreArchivo));
        //Obtiene la hoja 1
        HSSFSheet hoja = archivoExcel.getSheetAt(numHoja);
        //Obtiene el número de la última fila con datos de la hoja.
        int canFilas = hoja.getLastRowNum()+1;
        this.matriz=new String[canFilas][];
        for (int i = 0; i < canFilas; i++) {
            HSSFRow filas = hoja.getRow(i);
            int cantCol=filas.getLastCellNum();
            this.matriz[i]=new String[cantCol];
            
            
        for(int j=0;j<cantCol;j++)    
        {
     
            
            
            
            //String valor=filas.getCell(j).getStringCellValue();
               String valor= filas.getCell(j) == null?"":
                                (filas.getCell(j).getCellType() == CellType.STRING)?filas.getCell(j).getStringCellValue():
                                (filas.getCell(j).getCellType() == CellType.NUMERIC)?"" + filas.getCell(j).getNumericCellValue():
                                (filas.getCell(j).getCellType() == CellType.BOOLEAN)?"" + filas.getCell(j).getBooleanCellValue():
                                (filas.getCell(j).getCellType() == CellType.BLANK)?"BLANK":
                                (filas.getCell(j).getCellType() == CellType.FORMULA)?"FORMULA":
                                (filas.getCell(j).getCellType() == CellType.ERROR)?"ERROR":""; 
            
            
            this.matriz[i][j]=valor;
        }
     
       }
        
        
    
    }
    
    
    public void imp()
    {
        System.out.println(this.toString());
        
    }
    
    
    public String toString()
    {
    String msg="";
        for(String filas[]:this.matriz)
        {
            for(String valor:filas)
                msg+=valor+"\t";
        msg+="\n";
        }
    return msg;
    }

    public String[][] getMatriz() {
        return matriz;
    }
    
    
    
    
    
}
