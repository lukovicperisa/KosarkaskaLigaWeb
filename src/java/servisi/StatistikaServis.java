/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Statistika;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import model.Utakmica;

/**
 *
 * @author LUP1BG
 */
@ApplicationScoped
public class StatistikaServis {
    
    private static final Logger LOG = Logger.getLogger(StatistikaServis.class.getName());

    private WebTarget wt;

    private ObjectMapper maper;

    private final ResourceBundle bundle = ResourceBundle.getBundle("rb.konfiguracija");

    public Statistika vratiStatistikuUtakmice(Utakmica utakmica) {
        try{
            Client klijent = ClientBuilder.newClient();
            wt = klijent.target(bundle.getString("rest.servis.statistika.url")).queryParam("utakmicaID", utakmica.getUtakmicaID());

            JsonObject obj = wt.request().accept(MediaType.APPLICATION_JSON).get(JsonObject.class);
            maper = new ObjectMapper();

            klijent.close();

            Statistika statistika = maper.readValue(obj.getJsonObject("statistika").toString(), Statistika.class);
            return statistika;
        }catch(Exception ex) {
            LOG.warning("Doslo je do greske prilikom komunikacije sa REST servisom!");
            return null;
        }
    }

}
