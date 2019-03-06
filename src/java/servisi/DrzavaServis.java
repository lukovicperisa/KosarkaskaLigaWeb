/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.DrzavaDAO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import model.Drzava;

/**
 *
 * @author lukovp
 */

@ApplicationScoped
public class DrzavaServis {
    
    private final static Logger LOG = Logger.getLogger(DrzavaServis.class.getName());
    
    @Inject
    private DrzavaDAO drzavaDAO;
    
    @Transactional
    public List<Drzava> vratiSveDrzave(){
        try {
        return drzavaDAO.vratiSveDrzave();
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje drzave!");
            return null;
        }
    }

    @Transactional
    public Drzava vratiDrzavuZaID(int drzavaID) {
        try {
        return drzavaDAO.vratiDrzavuZaID(drzavaID); 
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje drzavu za zadati kriterijum!");
            return null;
        }
    }    
}
