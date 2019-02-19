/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author LUP1BG
 */
@Entity
@Table(name = "utakmica")
@NamedQueries({
    @NamedQuery(name = "Utakmica.findAll", query = "SELECT u FROM Utakmica u")
})
public class Utakmica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UtakmicaID")
    private Integer utakmicaID;
    @Column(name = "PoenaDomaci")
    private Integer poenaDomaci;
    @Column(name = "PoenaGosti")
    private Integer poenaGosti;
    @JoinColumn(name = "DomacinID", referencedColumnName = "KlubID")
    @ManyToOne
    private Klub domacin;
    @JoinColumn(name = "GostID", referencedColumnName = "KlubID")
    @ManyToOne
    private Klub gost;
    @JoinColumn(name = "KoloID", referencedColumnName = "KoloID")
    @ManyToOne
    private Kolo kolo;

    public Utakmica() {
    }

    public Utakmica(Integer utakmicaID) {
        this.utakmicaID = utakmicaID;
    }

    public Integer getUtakmicaID() {
        return utakmicaID;
    }

    public void setUtakmicaID(Integer utakmicaID) {
        this.utakmicaID = utakmicaID;
    }

    public Integer getPoenaDomaci() {
        return poenaDomaci;
    }

    public void setPoenaDomaci(Integer poenaDomaci) {
        this.poenaDomaci = poenaDomaci;
    }

    public Integer getPoenaGosti() {
        return poenaGosti;
    }

    public void setPoenaGosti(Integer poenaGosti) {
        this.poenaGosti = poenaGosti;
    }

    public Klub getDomacin() {
        return domacin;
    }

    public void setDomacin(Klub domacin) {
        this.domacin = domacin;
    }

    public Klub getGost() {
        return gost;
    }

    public void setGost(Klub gost) {
        this.gost = gost;
    }

    public Kolo getKolo() {
        return kolo;
    }

    public void setKolo(Kolo kolo) {
        this.kolo = kolo;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utakmica)) {
            return false;
        }
        Utakmica other = (Utakmica) object;
        if ((this.utakmicaID == null && other.utakmicaID != null) || (this.utakmicaID != null && !this.utakmicaID.equals(other.utakmicaID))) {
            return false;
        }
        return true;
    }
    
}
