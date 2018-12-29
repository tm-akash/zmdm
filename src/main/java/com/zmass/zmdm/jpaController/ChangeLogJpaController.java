/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.jpaController;

import com.zmass.zmdm.jpaController.exceptions.NonexistentEntityException;
import com.zmss.zmdm.entity.ChangeLog;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ZMF
 */
public class ChangeLogJpaController implements Serializable {

    public ChangeLogJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ChangeLog changeLog) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(changeLog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ChangeLog changeLog) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            changeLog = em.merge(changeLog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = changeLog.getId();
                if (findChangeLog(id) == null) {
                    throw new NonexistentEntityException("The changeLog with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ChangeLog changeLog;
            try {
                changeLog = em.getReference(ChangeLog.class, id);
                changeLog.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The changeLog with id " + id + " no longer exists.", enfe);
            }
            em.remove(changeLog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ChangeLog> findChangeLogEntities() {
        return findChangeLogEntities(true, -1, -1);
    }

    public List<ChangeLog> findChangeLogEntities(int maxResults, int firstResult) {
        return findChangeLogEntities(false, maxResults, firstResult);
    }

    private List<ChangeLog> findChangeLogEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ChangeLog.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ChangeLog findChangeLog(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ChangeLog.class, id);
        } finally {
            em.close();
        }
    }

    public int getChangeLogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ChangeLog> rt = cq.from(ChangeLog.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
