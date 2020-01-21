/*
    (7) To run the server just instantiate the implementor and publish the
    implementor on a public address
 */
package com.mycompany.lab01server;
import javax.xml.ws.Endpoint;

public class Server {

    public static void main(String args[]) throws InterruptedException {
        BankImpl implementor = new BankImpl();
        String address = "http://localhost:8080/bank";
        Endpoint.publish(address, implementor);
        System.out.print("Server running...");
        Thread.sleep(600 * 1000);        
        System.exit(0);
    }

}