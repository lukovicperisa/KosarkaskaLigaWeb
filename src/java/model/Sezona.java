/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author LUP1BG
 */
@Entity
@Table(name = "sezona")
@NamedQueries({
    @NamedQuery(name = "Sezona.findAll", query = "SELECT s FROM Sezona s")
})
public class Sezona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SezonaID")
    private Integer sezonaID;
    @Column(name = "Sistem")
    private Integer sistem;
    @Column(name = "DatumPocetka")
    @Temporal(TemporalType.DATE)
    private Date datumPocetka;
    @Column(name = "DatumZavrsetka")
    @Temporal(TemporalType.DATE)
    private Date datumZavrsetka;
    @Size(max = 50)
    @Column(name = "NazivLige")
    private String nazivLige;
    @OneToMany(mappedBy = "sezona")
    private List<Kolo> listaKola;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sezona")
    private List<Ucesnik> listaUcesnika;
    @JoinColumn(name = "SampionID", referencedColumnName = "KlubID")
    @ManyToOne
    private Klub sampion;

    public Sezona() {
    }

    public Sezona(Integer sezonaID) {
        this.sezonaID = sezonaID;
    }

    public Integer getSezonaID() {
        return sezonaID;
    }

    public void setSezonaID(Integer sezonaID) {
        this.sezonaID = sezonaID;
    }

    public Integer getSistem() {
        return sistem;
    }

    public void setSistem(Integer sistem) {
        this.sistem = sistem;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public String getNazivLige() {
        return nazivLige;
    }

    public void setNazivLige(String nazivLige) {
        this.nazivLige = nazivLige;
    }

    public List<Kolo> getListaKola() {
        return listaKola;
    }

    public void setListaKola(List<Kolo> listaKola) {
        this.listaKola = listaKola;
    }

    public List<Ucesnik> getListaUcesnika() {
        return listaUcesnika;
    }

    public void setListaUcesnika(List<Ucesnik> listaUcesnika) {
        this.listaUcesnika = listaUcesnika;
    }

    public Klub getSampion() {
        return sampion;
    }

    public void setSampion(Klub sampion) {
        this.sampion = sampion;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sezona)) {
            return false;
        }
        Sezona other = (Sezona) object;
        if ((this.sezonaID == null && other.sezonaID != null) || (this.sezonaID != null && !this.sezonaID.equals(other.sezonaID))) {
            return false;
        }
        return true;
    }
    
}
