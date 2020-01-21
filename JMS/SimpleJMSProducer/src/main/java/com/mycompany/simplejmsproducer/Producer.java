/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplejmsproducer;

import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author biar
 */
public class Producer {

        // Call this method to start the producer
	public void start() throws NamingException, JMSException {
                
                Context jndiContext = null;
                ConnectionFactory connectionFactory = null;
                Connection connection = null;
                Session session = null;
                Destination destination = null;
                MessageProducer producer = null;
                // NB dynamicTopics prefix is mandatory, because it's part of
                // JNDI default namespace, if you do not use this prefix you'll
                // get an error when you lookup "destinationName" in the 
                // jndiContext
                String destinationName = "dynamicTopics/myTopic";
        
         /*
         * Create a JNDI API InitialContext object, this is used to bind this
         * Producer to a real broker, that in this case is implemented by the
         * class org.apache.activemq.broker.BrokerService and it's active at
         * the address tcp://localhost:61616, see Project StartBroker
         */
        try {
            
            Properties props = new Properties();
        
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
            jndiContext = new InitialContext(props);        
                
        } catch (NamingException e) {
            System.out.println("ERROR in JNDI: " + e.toString());
            System.exit(1);
        }

        /*
         * Look up connection factory and destination.
         */
        try {
            connectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
            destination = (Destination)jndiContext.lookup(destinationName);
        } catch (NamingException e) {
            System.out.println("JNDI API lookup failed: " + e);
            System.exit(1);
        }

        /*
         * Create connection. Create session from connection; false means
         * session is not transacted. Create sender and text message. Send
         * messages, varying text slightly. Send end-of-messages message.
         * Finally, close connection.
         */
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(destination);
            
              
                
                TextMessage message = null;
		String messageType = null;
		
                message = session.createTextMessage();

		int i = 0;
		while (true) {
			i++;
			message.setStringProperty("name", "["+Integer.toString(i)+"] message");
			message.setIntProperty("value", i);
			producer.send(message);
                        System.out.println("["+Integer.toString(i)+"] Sent");

			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
        
        catch (JMSException e) {
            System.out.println("Exception occurred: " + e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
}
}
