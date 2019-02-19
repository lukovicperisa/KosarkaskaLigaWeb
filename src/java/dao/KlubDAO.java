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
import model.Klub;

/**
 *
 * @author lukovp
 */
@Singleton
public class KlubDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Klub> vratiSveKlubove() {
        return em.createQuery("Select k from Klub k Order by k.klubID").getResultList();
    }

    public Klub vratiKlubZaID(int id) {
        return (Klub) em.createQuery("Select k from Klub k where k.klubID=:klubID")
                .setParameter("klubID", id).getSingleResult();
    }

    public void sacuvaj(Klub klub) {
        em.persist(klub);
    }

    public void azuriraj(Klub klub) {
        em.merge(klub);
    }
}
