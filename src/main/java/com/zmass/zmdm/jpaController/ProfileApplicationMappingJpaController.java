/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.jpaController;

import com.zmass.zmdm.jpaController.exceptions.NonexistentEntityException;
import com.zmss.zmdm.entity.ProfileApplicationMapping;
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
public class ProfileApplicationMappingJpaController implements Serializable {

    public ProfileApplicationMappingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProfileApplicationMapping profileApplicationMapping) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(profileApplicationMapping);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProfileApplicationMapping profileApplicationMapping) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            profileApplicationMapping = em.merge(profileApplicationMapping);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profileApplicationMapping.getId();
                if (findProfileApplicationMapping(id) == null) {
                    throw new NonexistentEntityException("The profileApplicationMapping with id " + id + " no longer exists.");
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
            ProfileApplicationMapping profileApplicationMapping;
            try {
                profileApplicationMapping = em.getReference(ProfileApplicationMapping.class, id);
                profileApplicationMapping.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profileApplicationMapping with id " + id + " no longer exists.", enfe);
            }
            em.remove(profileApplicationMapping);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProfileApplicationMapping> findProfileApplicationMappingEntities() {
        return findProfileApplicationMappingEntities(true, -1, -1);
    }

    public List<ProfileApplicationMapping> findProfileApplicationMappingEntities(int maxResults, int firstResult) {
        return findProfileApplicationMappingEntities(false, maxResults, firstResult);
    }

    private List<ProfileApplicationMapping> findProfileApplicationMappingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProfileApplicationMapping.class));
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

    public ProfileApplicationMapping findProfileApplicationMapping(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProfileApplicationMapping.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfileApplicationMappingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProfileApplicationMapping> rt = cq.from(ProfileApplicationMapping.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<ProfileApplicationMapping> getProfileIdByApplicationId(String applicationId){
        EntityManager em = getEntityManager();
        try {
           List<ProfileApplicationMapping> getList = em.createQuery("select p from ProfileApplicationMapping p where p.applicationId="+applicationId+"").getResultList();
           return getList;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
    public List<ProfileApplicationMapping> getProfileIdByProfileId(String profileId){
        EntityManager em = getEntityManager();
        try {
           List<ProfileApplicationMapping> getList = em.createQuery("select p from ProfileApplicationMapping p where p.profileId = '"+profileId+"'").getResultList();
           return getList;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
    public List<ProfileApplicationMapping> deleteEntryListForProfileId(String profileId){
        EntityManager em = getEntityManager();
        try{
           List<ProfileApplicationMapping> list = em.createQuery("select p from ProfileApplicationMapping p where p.profileId = "+profileId+"").getResultList();
           return list;
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
