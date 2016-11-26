package controllers;

import controllers.Gender;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-26T17:29:42")
@StaticMetamodel(studentEntity.class)
public class studentEntity_ { 

    public static volatile SingularAttribute<studentEntity, String> fname;
    public static volatile SingularAttribute<studentEntity, String> lname;
    public static volatile SingularAttribute<studentEntity, String> file;
    public static volatile SingularAttribute<studentEntity, Gender> gender;
    public static volatile SingularAttribute<studentEntity, String> interest;
    public static volatile SingularAttribute<studentEntity, Date> dob;
    public static volatile SingularAttribute<studentEntity, Integer> id;
    public static volatile SingularAttribute<studentEntity, String> email;
    public static volatile SingularAttribute<studentEntity, Integer> age;

}