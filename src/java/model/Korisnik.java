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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author LUP1BG
 */
@Entity
@Table(name = "korisnik")
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "KorisnikID")
    private int korisnikID;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Prezime")
    private String prezime;

    @Column(name = "AdminRola")
    private boolean adminRola;

    @Column(name = "KorisnickoIme")
    private String korisnickoIme;

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
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) object;
        if (this.korisnikID != other.korisnikID) {
            return false;
        }
        return true;
    }

}
