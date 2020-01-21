/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab01server;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/*
    (6) To convert a collection into xml we need to create a "container"
    data type. Then we use a collection as field and we annotate the field
    with XmlElement annotation. Next go to Server (7)
*/
@XmlType(name="Clients")
public class Clients {
    @XmlElement(name="clients")
    private Set<Client> clients = null;
    
    public Clients(Set<Client> c){
        this.clients=c;
    }
    
    public Clients(){
        this.clients = new HashSet<Client>();
    }
    
    public boolean addClient(Client c){
        this.clients.add(c);
        return true;
   
    }
    
    public boolean constains(Client c){
        return this.clients.contains(c);
    }
    
    public int getLength(){
        return this.clients.size();
    }
    
    
    
}
