/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cardinal.session;

import com.cardinal.entities.Users;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Trishna
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "SysdeskAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
//    public Users findUserByEmail(String username){
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("username", username);     
//        return super.findOneResult(Users.FIND_BY_EMAIL, parameters);
//    }

    public Users findByUsername(String username) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", username);     
        return super.findOneResult(Users.FIND_BY_EMAIL, parameters);
    }
}
