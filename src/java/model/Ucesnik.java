/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author LUP1BG
 */
@Entity
@Table(name = "ucesnik")
@NamedQueries({
    @NamedQuery(name = "Ucesnik.findBySezonaID", query = "SELECT u FROM Ucesnik u WHERE u.ucesnikPK.sezonaID = :sezonaID")
})
public class Ucesnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UcesnikPK ucesnikPK;
    @Column(name = "BrojOdigranihUtakmica")
    private Integer brojOdigranihUtakmica;
    @Column(name = "BrojPobeda")
    private Integer brojPobeda;
    @JoinColumn(name = "SezonaID", referencedColumnName = "SezonaID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sezona sezona;
    @JoinColumn(name = "KlubID", referencedColumnName = "KlubID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Klub klub;

    public Ucesnik() {
    }

    public Ucesnik(UcesnikPK ucesnikPK) {
        this.ucesnikPK = ucesnikPK;
    }

    public Ucesnik(int sezonaID, int klubID) {
        this.ucesnikPK = new UcesnikPK(sezonaID, klubID);
    }

    public UcesnikPK getUcesnikPK() {
        return ucesnikPK;
    }

    public void setUcesnikPK(UcesnikPK ucesnikPK) {
        this.ucesnikPK = ucesnikPK;
    }

    public Integer getBrojOdigranihUtakmica() {
        return brojOdigranihUtakmica;
    }

    public void setBrojOdigranihUtakmica(Integer brojOdigranihUtakmica) {
        this.brojOdigranihUtakmica = brojOdigranihUtakmica;
    }

    public Integer getBrojPobeda() {
        return brojPobeda;
    }

    public void setBrojPobeda(Integer brojPobeda) {
        this.brojPobeda = brojPobeda;
    }

    public Sezona getSezona() {
        return sezona;
    }

    public void setSezona(Sezona sezona) {
        this.sezona = sezona;
    }

    public Klub getKlub() {
        return klub;
    }

    public void setKlub(Klub klub) {
        this.klub = klub;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ucesnik)) {
            return false;
        }
        Ucesnik other = (Ucesnik) object;
        if ((this.ucesnikPK == null && other.ucesnikPK != null) || (this.ucesnikPK != null && !this.ucesnikPK.equals(other.ucesnikPK))) {
            return false;
        }
        return true;
    }
    
}
