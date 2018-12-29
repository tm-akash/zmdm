/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.entityManagerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ZMF
 */
public class EntityManagerUtility {
    
     public static EntityManagerFactory entityManagerFactory;
    public static EntityManagerFactory getEntity() {

        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("ZMDM");
        }
        return entityManagerFactory;

    }

    
}
