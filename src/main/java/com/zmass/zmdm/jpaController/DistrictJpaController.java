/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.jpaController;

import com.zmass.zmdm.jpaController.exceptions.NonexistentEntityException;
import com.zmass.zmdm.jpaController.exceptions.PreexistingEntityException;
import com.zmss.zmdm.entity.District;
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
public class DistrictJpaController implements Serializable {

    public DistrictJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(District district) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(district);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDistrict(district.getId()) != null) {
                throw new PreexistingEntityException("District " + district + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(District district) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            district = em.merge(district);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = district.getId();
                if (findDistrict(id) == null) {
                    throw new NonexistentEntityException("The district with id " + id + " no longer exists.");
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
            District district;
            try {
                district = em.getReference(District.class, id);
                district.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The district with id " + id + " no longer exists.", enfe);
            }
            em.remove(district);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<District> findDistrictEntities() {
        return findDistrictEntities(true, -1, -1);
    }

    public List<District> findDistrictEntities(int maxResults, int firstResult) {
        return findDistrictEntities(false, maxResults, firstResult);
    }

    private List<District> findDistrictEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(District.class));
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

    public District findDistrict(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(District.class, id);
        } finally {
            em.close();
        }
    }

    public int getDistrictCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<District> rt = cq.from(District.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<District> findDistrictList(String stateCharCode) {
        EntityManager em = getEntityManager();
        try {
            List districtList = em.createQuery("select d from District d where d.stateDigitCode = '" + stateCharCode + "'").getResultList();
            return districtList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

}
