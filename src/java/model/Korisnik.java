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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author LUP1BG
 */
@Entity
@Table(name = "korisnik")
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KorisnikID")
    private Integer korisnikID;

    @Size(max = 45)
    @Column(name = "Ime")
    private String ime;

    @Size(max = 45)
    @Column(name = "Prezime")
    private String prezime;

    @Column(name = "AdminRola")
    private boolean adminRola;

    @Size(max = 45)
    @Column(name = "KorisnickoIme")
    private String korisnickoIme;

    @Size(max = 45)
    @Column(name = "Lozinka")
    private String lozinka;

    public int getId() {
        return korisnikID;
    }

    public void setId(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public boolean isAdminRola() {
        return adminRola;
    }

    public void setAdminRola(boolean adminRola) {
        this.adminRola = adminRola;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.korisnikID == null && other.korisnikID != null) || (this.korisnikID != null && !this.korisnikID.equals(other.korisnikID))) {
            return false;
        }
        return true;
    }

}
