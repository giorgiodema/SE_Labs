/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab02server;


import java.time.LocalDate;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="Operation")
public class Operation {
    private Float amount;
    private String description;
    private LocalDate date;
    
    
    public Operation(){
        this.amount=null;
        this.description=null;
        this.date=null;
        
    }
    
    public Operation(float amount, String description,LocalDate date){
        this.amount = amount;
        this.description = description;
        this.date = date;
    }


    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setDate(LocalDate date) {
        this.date = date;
    }


}
