package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Klub;
import model.Kolo;
import model.Ucesnik;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T14:39:09")
@StaticMetamodel(Sezona.class)
public class Sezona_ { 

    public static volatile SingularAttribute<Sezona, Klub> sampion;
    public static volatile SingularAttribute<Sezona, Date> datumPocetka;
    public static volatile ListAttribute<Sezona, Ucesnik> listaUcesnika;
    public static volatile SingularAttribute<Sezona, Integer> sistem;
    public static volatile SingularAttribute<Sezona, Date> datumZavrsetka;
    public static volatile SingularAttribute<Sezona, Integer> sezonaID;
    public static volatile SingularAttribute<Sezona, String> nazivLige;
    public static volatile ListAttribute<Sezona, Kolo> listaKola;

}