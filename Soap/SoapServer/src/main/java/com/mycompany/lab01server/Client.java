
package com.mycompany.lab01server;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/*
    (5) This class implements a Type that can be directly converted to xml,
    The XmlType annotation specifies the name of the type we're going to 
    create. If we want an field to be visible in the xml we need to specify
    that annotating the field with XmlElement annotation. In Clients
    class the same procedure is applied to a collection.
*/

@XmlType(name="Client")
public class Client{
    
    @XmlElement(name="name")
    final private String name;
    @XmlElement(name="surname")
    final private String surname;
    @XmlElement(name="id")
    final private String id;

    
    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getId() {
        return this.id;
    }

    public Client(String name,String surname,String id){
        this.name = name;
        this.surname = surname;
        this.id=id;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Client))
            return false;
        Client c = (Client) o;
        return c.id.equals(this.id) && c.name.equals(this.name) && this.surname.equals(c.surname);
    }
    
    @Override
    public int hashCode(){
        return this.id.hashCode()+this.name.hashCode()+this.surname.hashCode();
    }

}
