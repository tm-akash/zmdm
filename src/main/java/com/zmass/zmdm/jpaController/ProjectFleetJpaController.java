/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.jpaController;

import com.zmass.zmdm.jpaController.exceptions.NonexistentEntityException;
import com.zmss.zmdm.entity.ProjectFleet;
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
public class ProjectFleetJpaController implements Serializable {

    public ProjectFleetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProjectFleet projectFleet) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(projectFleet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProjectFleet projectFleet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            projectFleet = em.merge(projectFleet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = projectFleet.getId();
                if (findProjectFleet(id) == null) {
                    throw new NonexistentEntityException("The projectFleet with id " + id + " no longer exists.");
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
            ProjectFleet projectFleet;
            try {
                projectFleet = em.getReference(ProjectFleet.class, id);
                projectFleet.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The projectFleet with id " + id + " no longer exists.", enfe);
            }
            em.remove(projectFleet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProjectFleet> findProjectFleetEntities() {
        return findProjectFleetEntities(true, -1, -1);
    }

    public List<ProjectFleet> findProjectFleetEntities(int maxResults, int firstResult) {
        return findProjectFleetEntities(false, maxResults, firstResult);
    }

    private List<ProjectFleet> findProjectFleetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProjectFleet.class));
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

    public ProjectFleet findProjectFleet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProjectFleet.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectFleetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProjectFleet> rt = cq.from(ProjectFleet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<ProjectFleet> findProjectFleetByProfileId(Integer profileId){
        EntityManager em = getEntityManager();
        try {
           List<ProjectFleet> fleetList = (List<ProjectFleet>) em.createQuery("Select pf from ProjectFleet pf where pf.profileId="+profileId+"").getResultList();
           return fleetList;
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
