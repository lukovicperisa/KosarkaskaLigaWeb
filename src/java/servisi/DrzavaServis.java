/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.DrzavaDAO;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import model.Drzava;

/**
 *
 * @author lukovp
 */

@ApplicationScoped
public class DrzavaServis {
    
    @Inject
    private DrzavaDAO drzavaDAO;
    
    public List<Drzava> vratiSveDrzave(){
        return drzavaDAO.vratiSveDrzave();
    }

    public Drzava vratiDrzavuZaID(int drzavaID) {
        return drzavaDAO.vratiDrzavuZaID(drzavaID);        
    }    
}
