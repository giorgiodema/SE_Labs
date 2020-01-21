/*
    (8) To create the client: File -> New Project -> Java Application
    Then right click to the project: New -> Web Service Client
    Then insert the address of the wsdl: endpoint/?wsdl.
    Then to use a method implemented by the server go to: Web Service
    References -> .wsld -> ImplService and drag the method into the main
    procedure.
 */
package com.mycompany.lab01client;
import com.mycompany.lab01server.Client;
import com.mycompany.lab01server.Clients;
import java.util.Iterator;


public class Test {
    public static void main(String[] args){
        
        
        try { // Call Web Service Operation
            com.mycompany.lab01server.BankImplService service = new com.mycompany.lab01server.BankImplService();
            com.mycompany.lab01server.IBank port = service.getBankImplPort();
            

            // TODO process result here
            String newId = port.getNewId();
            boolean result = port.addClient(newId,"Giorgio", "De Magistris");
            newId = port.getNewId();
            result = port.addClient(newId,"Veronica", "Calabrese");

            System.out.println("Getting all clients:");
            Clients clients = port.getClients();
            Iterator<Client> it = clients.getClients().iterator();
            while(it.hasNext()){
                Client c = it.next();
                System.out.println(c.getId()+"; "+c.getName() + "; "+c.getSurname());
            }



        } catch (Exception ex) {
            // TODO handle custom exceptions here
            System.out.println(ex.getMessage());
        }



    }
}
