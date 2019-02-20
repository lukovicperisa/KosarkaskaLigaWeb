package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Klub;
import model.Kolo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-20T01:43:59")
@StaticMetamodel(Utakmica.class)
public class Utakmica_ { 

    public static volatile SingularAttribute<Utakmica, Integer> poenaDomaci;
    public static volatile SingularAttribute<Utakmica, Integer> poenaGosti;
    public static volatile SingularAttribute<Utakmica, Kolo> kolo;
    public static volatile SingularAttribute<Utakmica, Integer> utakmicaID;
    public static volatile SingularAttribute<Utakmica, Klub> gost;
    public static volatile SingularAttribute<Utakmica, Klub> domacin;

}