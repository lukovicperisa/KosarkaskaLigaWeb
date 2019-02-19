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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author LUP1BG
 */
@Entity
@Table(name = "drzava")
@NamedQueries({
    @NamedQuery(name = "Drzava.findAll", query = "SELECT d FROM Drzava d")
})
public class Drzava implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DrzavaID")
    private Integer drzavaId;
    @Size(max = 30)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(mappedBy = "drzava")
    private List<Klub> listaKlubova;

    public Drzava() {
    }

    public Drzava(Integer drzavaId) {
        this.drzavaId = drzavaId;
    }

    public Integer getDrzavaId() {
        return drzavaId;
    }

    public void setDrzavaId(Integer drzavaId) {
        this.drzavaId = drzavaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Klub> getListaKlubova() {
        return listaKlubova;
    }

    public void setListaKlubova(List<Klub> listaKlubova) {
        this.listaKlubova = listaKlubova;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drzava)) {
            return false;
        }
        Drzava other = (Drzava) object;
        if ((this.drzavaId == null && other.drzavaId != null) || (this.drzavaId != null && !this.drzavaId.equals(other.drzavaId))) {
            return false;
        }
        return true;
    }
    
}
