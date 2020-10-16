/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Interface.IBinario;
import Negocio.SopaBinaria;

/**
 *
 * @author madar
 */
public class PruebaPrevio {
    public static void main(String[] args) throws Exception {
        // :)
        
        IBinario sopa = new SopaBinaria("src/Datos/binarios.xls");
        
        System.out.println(sopa.toString());
        
        int paridad[][]= sopa.calculadorParidad();
        
        
      
        
        SopaBinaria nueva = sopa.getMatrizOrdenada();
        System.out.println("Matriz paridad");
        System.out.println(nueva.toString());
        
        
    }
    
}
