package com.mycompany.lab01server;

import javax.jws.WebService;

/*
    (4) This is the class that implements all the functionalities of the
    web service, remember to specify the interface defined in point (3)
    in the annotation. Next we need to create the classes that will be 
    converted into xml files and sent to the client. Go to Client (5) and
    Clients (6) classes to see implementation
*/
@WebService(endpointInterface="com.mycompany.lab01server.IBank")
public class BankImpl implements IBank{
    
    private Clients clients = null;

    public BankImpl(){
        this.clients = new Clients();
    }

    @Override
    public Clients getClients() {
        return clients;
    }

    @Override
    public boolean addClient(String id, String name, String surname) {
        Client c = new Client(name,surname,id);
        return clients.addClient(c);
    }

    @Override
    public String getNewId() {
        return new Integer(this.clients.getLength()).toString();
    }

}
