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
import model.Sezona;

/**
 *
 * @author lukovp
 */
@Singleton
public class SezonaDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Sezona> vratiSveSezone() {
        return em.createNamedQuery("Sezona.findAll").getResultList();
    }

    public Sezona vratiSezonuZaID(Integer id) {
        return em.find(Sezona.class, id);
    }

    public void sacuvaj(Sezona sezona) {        
        em.persist(sezona);
    }

    public void azuriraj(Sezona sezona) {
        em.merge(sezona);
    }

}
