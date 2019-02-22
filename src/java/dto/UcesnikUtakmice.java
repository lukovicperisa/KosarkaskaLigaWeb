/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author LUP1BG
 */
public class UcesnikUtakmice implements Serializable {
    
    private TimskaStatistika tim;
    
    private List<PojedinacnaStatistika> igraci;

    public TimskaStatistika getTim() {
        return tim;
    }

    public void setTim(TimskaStatistika tim) {
        this.tim = tim;
    }

    public List<PojedinacnaStatistika> getIgraci() {
        return igraci;
    }

    public void setIgraci(List<PojedinacnaStatistika> igraci) {
        this.igraci = igraci;
    }
    
    
}
