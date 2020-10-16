package Interface;


import Negocio.SopaBinaria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author madar
 */
public interface IBinario {
    
    /**
      * Ordena la matriz según su código de paridad
      * @return 
      */
    public SopaBinaria getMatrizOrdenada();

    public int[][] calculadorParidad();
    
}
