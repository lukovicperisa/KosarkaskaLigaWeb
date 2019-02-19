/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import util.Stranice;

/**
 *
 * @author LUP1BG
 */

@Named(value = "mbStranice")
@ApplicationScoped
public class MBStranice implements Serializable{
    
    public String index(){
        return Stranice.INDEX;
    }
    
    public String klubovi(){
        return Stranice.KLUBOVI;
    }
    
    public String registracijaKluba(){
        return Stranice.REGISTRACIJA_KLUBA;
    }
    
    public String detaljiKluba(){
        return Stranice.DETALJI_KLUBA;
    }
    
    public String liga(){
        return Stranice.LIGA;
    }
    
    public String registracijaSezone(){
        return Stranice.REGISTRACIJA_SEZONE;
    }
    
}
