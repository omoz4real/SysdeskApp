/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardinal.sysdesk;

import com.cardinal.entities.Task;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author Trishna
 */
@Named(value = "requestProducerBean")
@Dependent
public class RequestProducerBean implements Serializable {
    @Resource(mappedName = "jms/myTopic")
    private Topic myTopic;


    @Resource(mappedName = "jms/myTopicFactory")
    private ConnectionFactory myTopicFactory;
    private Task task;
    /**
     * Creates a new instance of RequestProducerBean
     */
    public RequestProducerBean() {
        task = new Task();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
    public void send(Task request) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = myTopicFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(myTopic);
            request = new Task();
            request.setClient(task.getClient());
            request.setContactName(task.getContactName());
            request.setEmail(task.getEmail());
            request.setPhone(task.getPhone());
            request.setStartDate(task.getStartDate());
            request.setStatus(task.getStatus());
            ObjectMessage mm = session.createObjectMessage(request);
//            mm.setString("Client", request.getClient());
//            mm.setString("ContactName", request.getContactName());
//            mm.setString("EMail", request.getEmail());
//            mm.setString("Phone", request.getPhone());
//            mm.setString("StartDate", request.getStartDate().toString());
//            mm.setString("EndDate", request.getEndDate().toString());
//            mm.setString("Status", request.getStatus());
//            mm.setStringProperty("Region", region);
//            mm.setObject(region, mm);
            messageProducer.send(mm);
            System.out.println(" This is the JMS Message Client " + task.getClient() + " From Method " + request.getClient());
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

//    private void sendJMSMessageToMyTopic(String messageData) {
//        myTopicFactory.createProducer().send(myTopic, messageData);
//    }

}
