package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Klub;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-20T01:43:59")
@StaticMetamodel(Drzava.class)
public class Drzava_ { 

    public static volatile SingularAttribute<Drzava, Integer> drzavaId;
    public static volatile ListAttribute<Drzava, Klub> listaKlubova;
    public static volatile SingularAttribute<Drzava, String> naziv;

}