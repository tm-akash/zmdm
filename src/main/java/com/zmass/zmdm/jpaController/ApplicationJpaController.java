/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.jpaController;

import com.zmass.zmdm.jpaController.exceptions.NonexistentEntityException;
import com.zmss.zmdm.entity.Application;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ZMF
 */
public class ApplicationJpaController implements Serializable {

    public ApplicationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Application application) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(application);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Application application) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            application = em.merge(application);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = application.getId();
                if (findApplication(id) == null) {
                    throw new NonexistentEntityException("The application with id " + id + " no longer exists.");
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
            Application application;
            try {
                application = em.getReference(Application.class, id);
                application.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The application with id " + id + " no longer exists.", enfe);
            }
            em.remove(application);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Application> findApplicationEntities() {
        return findApplicationEntities(true, -1, -1);
    }

    public List<Application> findApplicationEntities(int maxResults, int firstResult) {
        return findApplicationEntities(false, maxResults, firstResult);
    }

    private List<Application> findApplicationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Application.class));
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

    public Application findApplication(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Application.class, id);
        } finally {
            em.close();
        }
    }

    public int getApplicationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Application> rt = cq.from(Application.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Application getApplicationByApplicationPackageNameAndVersionCode(Application application){
        EntityManager em = getEntityManager();
        try{
            Application applicationDetail = (Application) em.createQuery("select a from Application a where a.versionCode = "+application.getVersionCode()+" and a.packageName = '"+application.getPackageName()+"'").getSingleResult();
            return applicationDetail;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
}
