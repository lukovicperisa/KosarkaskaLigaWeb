/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author LUP1BG
 */
@Embeddable
public class UcesnikPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SezonaID")
    private int sezonaID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KlubID")
    private int klubID;

    public UcesnikPK() {
    }

    public UcesnikPK(int sezonaID, int klubID) {
        this.sezonaID = sezonaID;
        this.klubID = klubID;
    }

    public int getSezonaID() {
        return sezonaID;
    }

    public void setSezonaID(int sezonaID) {
        this.sezonaID = sezonaID;
    }

    public int getKlubID() {
        return klubID;
    }

    public void setKlubID(int klubID) {
        this.klubID = klubID;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UcesnikPK)) {
            return false;
        }
        UcesnikPK other = (UcesnikPK) object;
        if (this.sezonaID != other.sezonaID) {
            return false;
        }
        if (this.klubID != other.klubID) {
            return false;
        }
        return true;
    }
    
}
