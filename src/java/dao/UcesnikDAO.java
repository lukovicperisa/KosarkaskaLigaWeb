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
import model.Ucesnik;
import model.Sezona;

/**
 *
 * @author LUP1BG
 */
@Singleton
public class UcesnikDAO {
    
    @PersistenceContext
    private EntityManager em;

    public List<Ucesnik> vratiUcesnikeZaSezonu(Sezona sezona){
        return em.createNamedQuery("Ucesnik.findBySezonaID").setParameter("sezonaID", sezona.getSezonaID())
                .getResultList();
    }
    
    public void azuriraj(Ucesnik ucesnik) {
        em.merge(ucesnik);
    }
    
}
