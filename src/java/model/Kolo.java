/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LUP1BG
 */
@Entity
@Table(name = "kolo")
@NamedQueries({
    @NamedQuery(name = "Kolo.findAll", query = "SELECT k FROM Kolo k")
})
public class Kolo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KoloID")
    private Integer koloID;
    @Column(name = "BrojKola")
    private Integer brojKola;
    @OneToMany(mappedBy = "kolo")
    private List<Utakmica> listaUtakmica;
    @JoinColumn(name = "SezonaID", referencedColumnName = "SezonaID")
    @ManyToOne
    private Sezona sezona;

    public Kolo() {
    }

    public Kolo(Integer koloID) {
        this.koloID = koloID;
    }

    public Integer getKoloID() {
        return koloID;
    }

    public void setKoloID(Integer koloID) {
        this.koloID = koloID;
    }

    public Integer getBrojKola() {
        return brojKola;
    }

    public void setBrojKola(Integer brojKola) {
        this.brojKola = brojKola;
    }

    public List<Utakmica> getListaUtakmica() {
        return listaUtakmica;
    }

    public void setListaUtakmica(List<Utakmica> listaUtakmica) {
        this.listaUtakmica = listaUtakmica;
    }

    public Sezona getSezona() {
        return sezona;
    }

    public void setSezona(Sezona sezona) {
        this.sezona = sezona;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kolo)) {
            return false;
        }
        Kolo other = (Kolo) object;
        if ((this.koloID == null && other.koloID != null) || (this.koloID != null && !this.koloID.equals(other.koloID))) {
            return false;
        }
        return true;
    }
    
}
