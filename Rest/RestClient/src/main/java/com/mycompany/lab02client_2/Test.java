/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab02client_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author biar
 */
public class Test {
    
    public static String BASE_URL = "http://localhost:8080/bank/";
    public static String CACHE_PATH = "./tmp/object.xml";
    public static CloseableHttpClient client = HttpClients.createDefault();
    

    
    public static List<Client> getClients() throws JAXBException, MalformedURLException, IOException{
        // Clients marshaller and unmarshaller
        JAXBContext clientsContext = JAXBContext.newInstance( Clients.class );
        Marshaller clientsMarshaller = clientsContext.createMarshaller();
        Unmarshaller clientsUnmarshaller = clientsContext.createUnmarshaller();
        
        URL url = new URL(BASE_URL + "clients");
        InputStream input = url.openStream();
        Clients clients = (Clients) clientsUnmarshaller.unmarshal(input);
        return clients.getClients();
    }
    
    public static int addClient(Client c) throws JAXBException, FileNotFoundException, IOException{
        // Client marshaller and unmarshaller
        JAXBContext clientContext = JAXBContext.newInstance( Client.class );
        Marshaller clientMarshaller = clientContext.createMarshaller();
        Unmarshaller clientUnmarshaller = clientContext.createUnmarshaller();
        
        clientMarshaller.marshal(c, new File(CACHE_PATH));
        InputStream input = new FileInputStream(new File(CACHE_PATH));
        HttpPost post = new HttpPost(BASE_URL+"clients");
        post.setEntity(new InputStreamEntity(input));
        post.setHeader("Content-Type", "text/xml");
        HttpResponse response = client.execute(post);
        return response.getStatusLine().getStatusCode();
    }
    
    public static List<Operation> getOperations(String clientID) throws JAXBException, MalformedURLException, IOException{
        // Operations marshaller and unmarshaller
        JAXBContext opsContext = JAXBContext.newInstance( Operations.class );
        Marshaller opsMarshaller = opsContext.createMarshaller();
        Unmarshaller opsUnmarshaller = opsContext.createUnmarshaller();
        
        URL url = new URL(BASE_URL + "clients/"+clientID+"/operations");
        InputStream input = url.openStream();
        Operations ops = (Operations) opsUnmarshaller.unmarshal(input);
        return ops.getOperations();
    }
    
    public static Client getClient(String clientID) throws JAXBException, MalformedURLException, IOException{
        // Clients marshaller and unmarshaller
        JAXBContext clientContext = JAXBContext.newInstance( Client.class );
        Marshaller clientMarshaller = clientContext.createMarshaller();
        Unmarshaller clientUnmarshaller = clientContext.createUnmarshaller();
        
        URL url = new URL(BASE_URL + "clients/"+clientID);
        InputStream input = url.openStream();
        Client c = (Client) clientUnmarshaller.unmarshal(input);
        return c;
    }
    
    public static int addOperation(String clientID,Operation o) throws MalformedURLException, JAXBException, FileNotFoundException, IOException{
        // Operations marshaller and unmarshaller
        JAXBContext opContext = JAXBContext.newInstance( Operation.class );
        Marshaller opMarshaller = opContext.createMarshaller();
        Unmarshaller opUnmarshaller = opContext.createUnmarshaller();
        
        opMarshaller.marshal(o, new File(CACHE_PATH));
        InputStream input = new FileInputStream(new File(CACHE_PATH));
        HttpPut put = new HttpPut(BASE_URL + "clients/"+clientID+"/operations");
        put.setEntity(new InputStreamEntity(input));
        put.setHeader("Content-Type", "text/xml");
        HttpResponse response = client.execute(put);
        return response.getStatusLine().getStatusCode();
        
    }
    
    public static void printOperation(Operation o){
        System.out.println("\t\t\t--> "+o.getAmount()+"Eur"+"; "+o.getDescription()+"; "+o.getDate().toString());
    }
    
    public static void printClient(Client c){
        System.out.println("## CLIENT ##\n"
                + "Name and Surname : "+c.getName()+" "+c.getSurname()+"\n"
                + "ClientID         : "+c.getClientID()+"\n"
                + "Operations       :\n");
        Iterator<Operation> it = c.getOps().iterator();
        while(it.hasNext()){
            Operation o = it.next();
            printOperation(o);
        }
    }
    
    public static void printClients(List<Client> l){
        Iterator<Client> it = l.iterator();
        while(it.hasNext()){
            printClient(it.next());
        }
    }
    
    public static void printOperations(List<Operation> l){
        Iterator<Operation> it = l.iterator();
        while(it.hasNext()){
            printOperation(it.next());
        }
    }
    
    public static void main(String[] args) throws JAXBException, FileNotFoundException, IOException{
        
        System.out.println("Adding new clients to the bank");
        addClient(new Client("Giorgio","De Magistris",LocalDate.parse("1995-07-09"),"1"));
        addClient(new Client("Veronica","Calabrese",LocalDate.parse("1994-02-22"),"2"));
        System.out.println("Printing all clients from the server");
        printClients(getClients());
        System.out.println("Adding operations to client 1");
        System.out.println("Status: " + addOperation("1",new Operation((float) 10.0,"Benzina",LocalDate.now())));
        System.out.println("Status: " + addOperation("1",new Operation((float) 20.0,"Pizza",LocalDate.now())));
        System.out.println("Printing operations of client 1 from server");
        printOperations(getOperations("1"));
        
        

        

        
        System.out.println("END");
    }
    
}
