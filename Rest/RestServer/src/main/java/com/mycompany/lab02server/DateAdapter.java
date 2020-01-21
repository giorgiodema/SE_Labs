/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab02server;

import java.lang.annotation.Annotation;
import java.sql.Date;
import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author biar
 */

class DateAdapter extends XmlAdapter{

    @Override
    public Object unmarshal(Object v) throws Exception {
        String s = (String) v;
        return LocalDate.parse(s);
    }

    @Override
    public Object marshal(Object v) throws Exception {
        LocalDate d = (LocalDate)v;
        return v.toString();
    }
}

    

