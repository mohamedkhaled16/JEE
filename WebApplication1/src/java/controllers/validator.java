/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Singleton;

/**
 *
 * @author mohamedk
 */
@Singleton
public class validator implements validatorLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean isNull(String s) {
        return s != null;
        
    }

    public boolean length(String s, int len) {
        return s.length() >= len;
    }

    public boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public boolean intRange(String i, int min, int max) {
        try{
        return Integer.parseInt(i) >= min && Integer.parseInt(i) <= max;
        }
        catch(NumberFormatException e){
        return false;
        }
    }

    public boolean isEmail(String s) {
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        return m.find();
    }

    public boolean isPhone(String s) {
        String pattern = "^\\d{10,11}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        return m.find();
    }

    public boolean isDate(String s) {
        String pattern = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        return m.find();
    }
}
