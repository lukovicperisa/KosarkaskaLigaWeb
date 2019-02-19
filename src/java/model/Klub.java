/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author LUP1BG
 */
@Entity
@Table(name = "klub")
public class Klub implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KlubID")
    private Integer klubID;
    @Size(max = 30)
    @Column(name = "Naziv")
    private String naziv;
    @Size(max = 1000)
    @Column(name = "Opis")
    private String opis;
    @Size(max = 100)
    @Column(name = "Arena")
    private String arena;
    @Size(max = 100)
    @Column(name = "Slika")
    private String slika;
    @OneToMany(mappedBy = "domacin")
    private List<Utakmica> listaUtakmicaDomacin;
    @OneToMany(mappedBy = "gost")
    private List<Utakmica> listaUtakmicaGost;
    @JoinColumn(name = "DrzavaID", referencedColumnName = "DrzavaID")
    @ManyToOne
    private Drzava drzava;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klub")
    private List<Ucesnik> listaUcesnika;
    @OneToMany(mappedBy = "sampion")
    private List<Sezona> pobednickeSezone;

    public Klub() {
    }

    public Klub(Integer klubID) {
        this.klubID = klubID;
    }

    public Integer getKlubID() {
        return klubID;
    }

    public void setKlubID(Integer klubID) {
        this.klubID = klubID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public List<Utakmica> getListaUtakmicaDomacin() {
        return listaUtakmicaDomacin;
    }

    public void setListaUtakmicaDomacin(List<Utakmica> listaUtakmicaDomacin) {
        this.listaUtakmicaDomacin = listaUtakmicaDomacin;
    }

    public List<Utakmica> getListaUtakmicaGost() {
        return listaUtakmicaGost;
    }

    public void setListaUtakmicaGost(List<Utakmica> listaUtakmicaGost) {
        this.listaUtakmicaGost = listaUtakmicaGost;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public List<Ucesnik> getListaUcesnika() {
        return listaUcesnika;
    }

    public void setListaUcesnika(List<Ucesnik> listaUcesnika) {
        this.listaUcesnika = listaUcesnika;
    }

    public List<Sezona> getPobednickeSezone() {
        return pobednickeSezone;
    }

    public void setPobednickeSezone(List<Sezona> pobednickeSezone) {
        this.pobednickeSezone = pobednickeSezone;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klub)) {
            return false;
        }
        Klub other = (Klub) object;
        if ((this.klubID == null && other.klubID != null) || (this.klubID != null && !this.klubID.equals(other.klubID))) {
            return false;
        }
        return true;
    }
    
}
