/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatori;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author LUP1BG
 */
@ApplicationScoped
public class UtakmicaValidator extends AbstraktniValidator{
    
    public void validirajUtakmicu(ComponentSystemEvent event) {
        UIComponent komponenta = event.getComponent();
        UIInput komponentaDomaci = (UIInput) komponenta.findComponent("poenaDomaci");
        UIInput komponentaGosti = (UIInput) komponenta.findComponent("poenaGosti");
        String poljeIspisa = komponentaGosti.getClientId();

        try {
            int poenaDomaci = Integer.parseInt(vratiKomponentuKaoString(komponentaDomaci));
            int poenaGosti = Integer.parseInt(vratiKomponentuKaoString(komponentaGosti));

            if (poenaDomaci == poenaGosti) {
                komponentaDomaci.resetValue();
                komponentaGosti.resetValue();
                dodajPoruku(poljeIspisa, "validacija.utakmica.poeni.nereseno");
            }
        } catch (NumberFormatException numberFormatException) {
            Logger.getAnonymousLogger().info(numberFormatException.getMessage());
        }
    }
    
}
