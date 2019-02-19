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
import model.Drzava;

/**
 *
 * @author lukovp
 */

@Singleton
public class DrzavaDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Drzava> vratiSveDrzave(){        
        return em.createNamedQuery("Drzava.findAll").getResultList();    
    }

    public Drzava vratiDrzavuZaID(int drzavaID) {
        return (Drzava) em.find(Drzava.class, drzavaID);
    }    
}
