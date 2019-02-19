package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Drzava;
import model.Sezona;
import model.Ucesnik;
import model.Utakmica;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-19T18:58:36")
@StaticMetamodel(Klub.class)
public class Klub_ { 

    public static volatile SingularAttribute<Klub, String> slika;
    public static volatile ListAttribute<Klub, Ucesnik> listaUcesnika;
    public static volatile SingularAttribute<Klub, Drzava> drzava;
    public static volatile ListAttribute<Klub, Sezona> pobednickeSezone;
    public static volatile SingularAttribute<Klub, Integer> klubID;
    public static volatile ListAttribute<Klub, Utakmica> listaUtakmicaDomacin;
    public static volatile SingularAttribute<Klub, String> naziv;
    public static volatile ListAttribute<Klub, Utakmica> listaUtakmicaGost;
    public static volatile SingularAttribute<Klub, String> arena;
    public static volatile SingularAttribute<Klub, String> opis;

}