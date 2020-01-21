package com.mycompany.lab02server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/*
    This is the entry point for web service, and it is mapped to handle
    all URLs starting with bank
*/
@Path("bank")
@Produces("text/xml")
public class Bank {
    
    private Set<Client> clients;
    
    public Bank(){
        clients = new HashSet<Client>();
    }
    
    @GET
    @Path("/clients/{clientID}")
    public Client getClientById(@PathParam("clientID") String clientID){
        Iterator<Client> it = clients.iterator();
        while(it.hasNext()){
            Client c = it.next();
            if(c.getClientID().equals(clientID))
                return c;
        }
        return null;
    }
    
    @GET
    @Path("/clients")
    public Clients getClients(){
        Clients c = new Clients();
        List<Client> l = new ArrayList<Client>(clients);
        c.setClients(l);
        return c;
    }
    
    @GET
    @Path("/clients/{clientID}/operations")
    public Operations getOperations(@PathParam("clientID") String clientID){
        Iterator<Client> it = clients.iterator();
        while(it.hasNext()){
            Client c = it.next();
            if(c.getClientID().equals(clientID)){
                Operations ops = new Operations();
                ops.setOperations(c.getOps());
                return ops;
            }
        }
        return null;
    }
    
    @POST
    @Path("/clients")
    public Response addCLient(Client c){
        Iterator<Client> it = clients.iterator();
        while(it.hasNext()){
            Client a = it.next();
            if (a.getClientID().equals(c.getClientID()))
                return Response.status(Response.Status.CONFLICT).build();
            if(a.getName().equals(c.getName()) && a.getSurname().equals(c.getSurname()) && a.getDateOfBirth().equals(c.getDateOfBirth()))
                return Response.status(Response.Status.CONFLICT).build();
        }
        clients.add(c);
        return Response.status(Response.Status.CREATED).build();
    }
    
    
    @PUT
    @Path("/clients/{clientID}/operations")
    public Response addOperation(@PathParam("clientID")String clientID,Operation op){
        Iterator<Client> it = clients.iterator();
        while(it.hasNext()){
            Client c = it.next();
            if(c.getClientID().equals(clientID)){
                c.addOperation(op);
                return Response.status(Response.Status.CREATED).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    
    
    
}
