/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.Korisnik;

/**
 *
 * @author LUP1BG
 */
@Singleton
public class KorisnikDAO {

    @PersistenceContext
    private EntityManager em;

    public Korisnik vratiKorisnikaZaID(String korisnickoIme) {
        try {
            Korisnik korisnik = (Korisnik) em.createQuery("Select k from Korisnik k where k.korisnickoIme=:korisnickoIme")
                                             .setParameter("korisnickoIme", korisnickoIme).getSingleResult();
            return korisnik;
        } catch(NoResultException ex){
            return null;
        }
    }

}
