/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Igrac;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import model.Klub;

/**
 *
 * @author LUP1BG
 */
@ApplicationScoped
public class IgracServis {
    
    private final static Logger LOG = Logger.getLogger(IgracServis.class.getName());
    
    private WebTarget wt;

    private ObjectMapper maper;

    private final ResourceBundle bundle = ResourceBundle.getBundle("rb.konfiguracija");

    public List<Igrac> vratiIgraceZaKlub(Klub klub) {
        try {
            Client klijent = ClientBuilder.newClient();
            wt = klijent.target(bundle.getString("rest.servis.igraci.url")).queryParam("klubID", klub.getKlubID());

            JsonObject obj = wt.request().accept(MediaType.APPLICATION_JSON).get(JsonObject.class);

            klijent.close();

            maper = new ObjectMapper();
            Igrac[] igraci = maper.readValue(obj.getJsonArray("igraci").toString(), Igrac[].class);

            return Arrays.asList(igraci);
        } catch (Exception ex) {
            LOG.warning("Doslo je do greske prilikom komunikacije sa REST servisom!");
            return null;
        }
    }

}
