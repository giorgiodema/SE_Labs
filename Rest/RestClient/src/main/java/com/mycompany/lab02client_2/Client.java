/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab02client_2;



import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
    @XmlElement(name="clientID")
    private String clientID;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="surname")
    private String surname;
    @XmlElement(name="dateOfBirth")
    @XmlSchemaType(name = "LocalDate")
    @XmlJavaTypeAdapter(value=DateAdapter.class, type=LocalDate.class)
    private LocalDate dateOfBirth;
    @XmlElement(name="ops")
    private List<Operation> ops;
    
    public Client(){
        clientID = null;
        name = null;
        surname = null;
        dateOfBirth = null;
        ops = new ArrayList<Operation>();
    }
  
    public Client(String name, String surname, LocalDate dob,String clientID){
        this.ops = new ArrayList<Operation>();
        this.name=name;
        this.surname=surname;
        this.dateOfBirth = dob;
        this.clientID = clientID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }


    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    

   
    public List<Operation> getOps() {
        return ops;
    }

    public void setOps(List<Operation> ops) {
        this.ops = ops;
    }
    
    public void addOperation(Operation o){
        this.ops.add(o);
    }

    
}
