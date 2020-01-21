/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplejmsconsumer;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author biar
 */
public class Consumer implements MessageListener{
    
    private TopicConnection topicConnection;
    private TopicSession topicSession = null;
    private Destination destination = null;
    private MessageProducer producer = null;
    
    
    public Consumer(){
        
        Context jndiContext = null;
        ConnectionFactory topicConnectionFactory = null;

        String destinationName = "dynamicTopics/myTopic";

        try {

            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
            jndiContext = new InitialContext(props);   
                    
                    
                    		
			
            topicConnectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
            destination = (Destination)jndiContext.lookup(destinationName);
            topicConnection = (TopicConnection)topicConnectionFactory.createConnection();
            topicSession = (TopicSession)topicConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                        
            TopicSubscriber topicSubscriber =  
            topicSession.createSubscriber((Topic)destination);
		
            // Set this class to be the handler for the incoming messages.
            // Register this class as MessageListener, the class should
            // then implement the method onMessage
            topicSubscriber.setMessageListener(this);
            } catch (JMSException err) {
                    err.printStackTrace();
            } catch (NamingException err) {
                    err.printStackTrace();
            }
    }
    
    /**
     * Close connection on dynamicTopics/myTopic
     */
    public void stop() {
            try {
                    topicConnection.stop();
            } catch (JMSException err) {
                    err.printStackTrace();
            }
    }

    /**
     * Open connection on topics/myTopic
     */
    public void start() {
            try {
                    topicConnection.start();
            } catch (JMSException err) {
                    err.printStackTrace();
            }
    }
    
    // To implement the MessageListener interface. The class has been previously
    // registered (in the costructor) as listener when the subscriber was
    // created
    @Override
    public void onMessage(Message msg) {
        try {
            String name = msg.getStringProperty("name");
            int value = msg.getIntProperty("value");
            
            System.out.println("Received message:\n\t\t-> name: "+name+
                    "\n\t\t-> value: "+Integer.toString(value));
        } catch (JMSException ex) {
            System.out.println("Error in Consumer:::onMessage: "+ex.getMessage());
        }
    }
    
}
