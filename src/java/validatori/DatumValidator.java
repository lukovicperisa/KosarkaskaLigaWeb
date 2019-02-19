/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatori;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author LUP1BG
 */
@ApplicationScoped
public class DatumValidator extends AbstraktniValidator{
    
    public void validirajDatume(ComponentSystemEvent event) {
        UIComponent komponenta = event.getComponent();
        UIInput komponentaPocetak = (UIInput) komponenta.findComponent("datumPocetka");
        UIInput komponentaZavrsetak = (UIInput) komponenta.findComponent("datumZavrsetka");
        String poljeIspisa = komponentaZavrsetak.getClientId();
        
        String pocetakString = vratiKomponentuKaoString(komponentaPocetak);
        String zavrsetakString = vratiKomponentuKaoString(komponentaZavrsetak);

        if (pocetakString.isEmpty() || zavrsetakString.isEmpty()) {
            dodajPoruku(poljeIspisa, "validacija.sezona.datum.prazan");
            return;
        }
        
        Date datum1 = (Date) komponentaPocetak.getValue();
        Date datum2 = (Date) komponentaZavrsetak.getValue();
       
        LocalDate datumPocetka = datum1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate datumZavrsetka = datum2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (!datumPocetka.isBefore(datumZavrsetka)) {
            dodajPoruku(poljeIspisa, "validacija.sezona.datum");
        }

    }
    
}
