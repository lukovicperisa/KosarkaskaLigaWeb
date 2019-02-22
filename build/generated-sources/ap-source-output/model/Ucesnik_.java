package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Klub;
import model.Sezona;
import model.UcesnikPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T14:39:09")
@StaticMetamodel(Ucesnik.class)
public class Ucesnik_ { 

    public static volatile SingularAttribute<Ucesnik, Integer> brojPobeda;
    public static volatile SingularAttribute<Ucesnik, UcesnikPK> ucesnikPK;
    public static volatile SingularAttribute<Ucesnik, Integer> brojOdigranihUtakmica;
    public static volatile SingularAttribute<Ucesnik, Klub> klub;
    public static volatile SingularAttribute<Ucesnik, Sezona> sezona;

}