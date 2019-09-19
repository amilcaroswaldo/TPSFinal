/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocios;

import Acceso_Datos.HistorialAlumno;
import Logica_Negocios.exceptions.NonexistentEntityException;
import Logica_Negocios.exceptions.PreexistingEntityException;
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
 * @author Amilcar
 */
public class HistorialAlumnoJpaController implements Serializable {

    public HistorialAlumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistorialAlumno historialAlumno) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historialAlumno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistorialAlumno(historialAlumno.getIdHistorialAlumno()) != null) {
                throw new PreexistingEntityException("HistorialAlumno " + historialAlumno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistorialAlumno historialAlumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historialAlumno = em.merge(historialAlumno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = historialAlumno.getIdHistorialAlumno();
                if (findHistorialAlumno(id) == null) {
                    throw new NonexistentEntityException("The historialAlumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistorialAlumno historialAlumno;
            try {
                historialAlumno = em.getReference(HistorialAlumno.class, id);
                historialAlumno.getIdHistorialAlumno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialAlumno with id " + id + " no longer exists.", enfe);
            }
            em.remove(historialAlumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistorialAlumno> findHistorialAlumnoEntities() {
        return findHistorialAlumnoEntities(true, -1, -1);
    }

    public List<HistorialAlumno> findHistorialAlumnoEntities(int maxResults, int firstResult) {
        return findHistorialAlumnoEntities(false, maxResults, firstResult);
    }

    private List<HistorialAlumno> findHistorialAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialAlumno.class));
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

    public HistorialAlumno findHistorialAlumno(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialAlumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistorialAlumno> rt = cq.from(HistorialAlumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
