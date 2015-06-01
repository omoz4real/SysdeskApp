/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cardinal.session;

import com.cardinal.entities.Task;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Trishna
 */
@Stateless
public class TaskFacade extends AbstractFacade<Task> {
    @PersistenceContext(unitName = "SysdeskAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskFacade() {
        super(Task.class);
    }
    
}
