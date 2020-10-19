/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Interface.IBinario;
import Modelo.Bit;
import Negocio.SopaBinaria;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author madar
 */
public class PruebaPrevio {

    public static void main(String[] args) throws Exception {
        // :)

        IBinario sopa = new SopaBinaria("src/Datos/binarios.xls");
        SopaBinaria sopaParidad = sopa.getMatrizOrdenada();
        String matriz[][]=sopaParidad.getMatrizEnString();

        System.out.println(sopa.toString());

        int paridad[][] = sopa.calculadorParidad();

        SopaBinaria nueva = sopa.getMatrizOrdenada();
        System.out.println("Matriz paridad");
        System.out.println(nueva.toString());
        
        System.out.println("Imprimiendo en PDF...");
        int unos=0;
        int paridadP=0;
        
        //Marca las casillas que sean parte de la paridad
        for (int i = 0; i < matriz[0].length; i++) {
            int k=sopaParidad.marcarParidad(0, i, unos, paridadP);     
        }

        Document doc = new Document();
        try {
            FileOutputStream ficheroPdf = new FileOutputStream("src/Datos/Sol_SopaBinaria.pdf");
            PdfWriter.getInstance(doc, ficheroPdf);
            doc.open();
            Paragraph parrafo = new Paragraph();
            parrafo.add("Matriz de paridad \n");
            doc.add(parrafo);
            //Creando una tabla:
            int columnas = sopa.getMatrizOrdenada().getMySopaBinaria()[0].length;
            BaseColor colores []={BaseColor.BLUE,BaseColor.GREEN,BaseColor.YELLOW,BaseColor.ORANGE,BaseColor.MAGENTA,BaseColor.CYAN};
            PdfPTable tabla = new PdfPTable(columnas);
            for (int i = 0; i < matriz.length; i++) {
                
                for (int j = 0; j < columnas; j++) {

                   

                    PdfPCell celda = new PdfPCell(new Phrase(matriz[i][j]));
                    if(sopaParidad.getMySopaBinaria()[i][j].isParidad())
                        celda.setBackgroundColor(colores[j]);
                     else
                         celda.setBackgroundColor(BaseColor.WHITE);
                   
                    tabla.addCell(celda);

                }

            }
            doc.add(tabla);
            doc.close();
            Pdf(doc);
        } catch (Exception ex) {
             System.err.println(ex.getMessage());
        }
        System.out.println("Guardado en: src/Datos/Sol_SopaBinaria.pdf");

    }
    public static void Pdf(Document d){
     
          try {
     File path = new File ("src/Datos/Sol_SopaBinaria.pdf");
     Desktop.getDesktop().open(path);
     }catch (IOException ex) {
     ex.printStackTrace();
}
       
         
         
     
     }

}
