/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardinal.ejb;

import com.cardinal.sysdesk.Customer;
import com.cardinal.sysdesk.DepList;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Trishna
 */
@Named 
@Stateless
public class CustomerSessionBean implements Serializable {

    private final ArrayList<Customer> customers;
    private DataModel<Customer> model ;
    @Inject
    private DepList deplist;
    @Inject
    private Customer customer;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public CustomerSessionBean() throws ParseException {
        customers = new ArrayList<Customer>();
        customers.add(new Customer());
        customer = new Customer();
        model = new ListDataModel(customers);
    }

    public DataModel<Customer> getModel() {
        return model;
    }

    public void setModel(DataModel<Customer> model) {
        this.model = model;
    }

//    public DataModel getModel() {
//        return model;
//    }

    public void addStudent(Customer customer) {
        ArrayList<Customer> currentCustomers
                = (ArrayList<Customer>) model.getWrappedData();
        currentCustomers.add(customer);
        model.setWrappedData(currentCustomers);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public DepList getDeplist() {
        return deplist;
    }

    public void setDeplist(DepList deplist) {
        this.deplist = deplist;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
