/*
    (1) To create the server: File -> New Project -> Maven -> Java Application
    Next step go to the pom.xml file to add dependencies and plugins
 */
package com.mycompany.lab01server;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/*
    (3) This is the interface of the web server, remember the annotation
    @WebService, next we need to implement this interface, go to the
    class BankImpl
*/
@WebService
public interface IBank {
    public Clients getClients();
    public boolean addClient(String id,String name,String surname);
    public String getNewId();
}
