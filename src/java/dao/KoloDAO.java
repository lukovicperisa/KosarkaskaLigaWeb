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
import model.Kolo;
import model.Sezona;

/**
 *
 * @author lukovp
 */
@Singleton
public class KoloDAO {
    
    @PersistenceContext
    private EntityManager em;

    public List<Kolo> vratiSvaKola() {
        return em.createNamedQuery("Kolo.findAll").getResultList();          
    }

    public void sacuvaj(Kolo kolo) {
        em.persist(kolo);
    }

    public List<Kolo> vratiKolaZaSezonu(Sezona sezona) {
        return  em.createQuery("Select k from Kolo k where k.sezona=:sezonaID")
                .setParameter("sezonaID", sezona).getResultList();
    }

    public void azuriraj(Kolo kolo){
        em.merge(kolo);
    }

    public Kolo vratiKoloZaID(Integer id) {
        return em.find(Kolo.class, id);
    }
}
