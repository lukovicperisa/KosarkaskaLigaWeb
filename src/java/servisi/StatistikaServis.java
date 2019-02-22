/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Statistika;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author LUP1BG
 */
@ApplicationScoped
public class StatistikaServis {

    private WebTarget wt;

    private ObjectMapper maper;

    private final ResourceBundle bundle = ResourceBundle.getBundle("rb.konfiguracija");

    public Statistika vratiStatistikuUtakmice() {

        Client klijent = ClientBuilder.newClient();
        wt = klijent.target(bundle.getString("rest.servis.statistika.url"));

        JsonObject obj = wt.request().accept(MediaType.APPLICATION_JSON).get(JsonObject.class);
        maper = new ObjectMapper();

        klijent.close();
        Statistika statistika = new Statistika();

        try {
            statistika = maper.readValue(obj.getJsonObject("statistika").toString(), Statistika.class);
        } catch (IOException ex) {
            Logger.getLogger(StatistikaServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statistika;
    }

}
