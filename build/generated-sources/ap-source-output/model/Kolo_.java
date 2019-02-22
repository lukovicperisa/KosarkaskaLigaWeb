package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Sezona;
import model.Utakmica;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T15:36:30")
@StaticMetamodel(Kolo.class)
public class Kolo_ { 

    public static volatile SingularAttribute<Kolo, Integer> koloID;
    public static volatile SingularAttribute<Kolo, Integer> brojKola;
    public static volatile ListAttribute<Kolo, Utakmica> listaUtakmica;
    public static volatile SingularAttribute<Kolo, Sezona> sezona;

}