/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author madar
 */
public class Bit {
    
    private boolean valor;
    private boolean paridad;

    public boolean isParidad() {
        return paridad;
    }

    public void setParidad(boolean paridad) {
        this.paridad = paridad;
    }

    public Bit(boolean valor) {
        this.valor = valor;
    }

    public Bit() {
    }

    public boolean isValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }
    @Override
    public String toString()
    {
        String valor ="0";
        if(isValor()){
            valor="1";
            return valor;
        }
        else return valor;
    }
    public boolean equals(Bit pBit){
        boolean isEquals=false;
        if(pBit.isValor()==this.isValor())
           isEquals=true;
        
        return isEquals;
    
    }
    
    
    
}
