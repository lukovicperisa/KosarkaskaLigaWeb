/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author LUP1BG
 */
public class Statistika implements Serializable{
    
    private UcesnikUtakmice domaci;
    
    private UcesnikUtakmice gosti;

    public UcesnikUtakmice getDomaci() {
        return domaci;
    }

    public void setDomaci(UcesnikUtakmice domaci) {
        this.domaci = domaci;
    }

    public UcesnikUtakmice getGosti() {
        return gosti;
    }

    public void setGosti(UcesnikUtakmice gosti) {
        this.gosti = gosti;
    }
    
    
}
