/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Utakmica;
import model.Kolo;

/**
 *
 * @author lukovp
 */

@Singleton
public class UtakmicaDAO {
    
    @PersistenceContext
    private EntityManager em;

    public List<Utakmica> vratiSveUtakmice() {
        return em.createNamedQuery("Utakmica.findAll").getResultList();
    }
     
    public void sacuvaj(Utakmica utakmica) {
        em.persist(utakmica);
    }

    public List<Utakmica> vratiUtakmiceZaKolo(Kolo kolo) {
        return  em.createQuery("Select u from Utakmica u where u.kolo=:koloID")
                .setParameter("koloID", kolo).getResultList();
    }   
    
    public void azuriraj(Utakmica utakmica){
        em.merge(utakmica);
    }
}
