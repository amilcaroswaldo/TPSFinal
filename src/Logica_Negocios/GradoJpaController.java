/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocios;

import Acceso_Datos.Grado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Acceso_Datos.Seccion;
import Logica_Negocios.exceptions.NonexistentEntityException;
import Logica_Negocios.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Amilcar
 */
public class GradoJpaController implements Serializable {

    public GradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grado grado) throws PreexistingEntityException, Exception {
        if (grado.getSeccionCollection() == null) {
            grado.setSeccionCollection(new ArrayList<Seccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Seccion> attachedSeccionCollection = new ArrayList<Seccion>();
            for (Seccion seccionCollectionSeccionToAttach : grado.getSeccionCollection()) {
                seccionCollectionSeccionToAttach = em.getReference(seccionCollectionSeccionToAttach.getClass(), seccionCollectionSeccionToAttach.getIdSeccion());
                attachedSeccionCollection.add(seccionCollectionSeccionToAttach);
            }
            grado.setSeccionCollection(attachedSeccionCollection);
            em.persist(grado);
            for (Seccion seccionCollectionSeccion : grado.getSeccionCollection()) {
                Grado oldIdGradoOfSeccionCollectionSeccion = seccionCollectionSeccion.getIdGrado();
                seccionCollectionSeccion.setIdGrado(grado);
                seccionCollectionSeccion = em.merge(seccionCollectionSeccion);
                if (oldIdGradoOfSeccionCollectionSeccion != null) {
                    oldIdGradoOfSeccionCollectionSeccion.getSeccionCollection().remove(seccionCollectionSeccion);
                    oldIdGradoOfSeccionCollectionSeccion = em.merge(oldIdGradoOfSeccionCollectionSeccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGrado(grado.getIdGrado()) != null) {
                throw new PreexistingEntityException("Grado " + grado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grado grado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grado persistentGrado = em.find(Grado.class, grado.getIdGrado());
            Collection<Seccion> seccionCollectionOld = persistentGrado.getSeccionCollection();
            Collection<Seccion> seccionCollectionNew = grado.getSeccionCollection();
            Collection<Seccion> attachedSeccionCollectionNew = new ArrayList<Seccion>();
            for (Seccion seccionCollectionNewSeccionToAttach : seccionCollectionNew) {
                seccionCollectionNewSeccionToAttach = em.getReference(seccionCollectionNewSeccionToAttach.getClass(), seccionCollectionNewSeccionToAttach.getIdSeccion());
                attachedSeccionCollectionNew.add(seccionCollectionNewSeccionToAttach);
            }
            seccionCollectionNew = attachedSeccionCollectionNew;
            grado.setSeccionCollection(seccionCollectionNew);
            grado = em.merge(grado);
            for (Seccion seccionCollectionOldSeccion : seccionCollectionOld) {
                if (!seccionCollectionNew.contains(seccionCollectionOldSeccion)) {
                    seccionCollectionOldSeccion.setIdGrado(null);
                    seccionCollectionOldSeccion = em.merge(seccionCollectionOldSeccion);
                }
            }
            for (Seccion seccionCollectionNewSeccion : seccionCollectionNew) {
                if (!seccionCollectionOld.contains(seccionCollectionNewSeccion)) {
                    Grado oldIdGradoOfSeccionCollectionNewSeccion = seccionCollectionNewSeccion.getIdGrado();
                    seccionCollectionNewSeccion.setIdGrado(grado);
                    seccionCollectionNewSeccion = em.merge(seccionCollectionNewSeccion);
                    if (oldIdGradoOfSeccionCollectionNewSeccion != null && !oldIdGradoOfSeccionCollectionNewSeccion.equals(grado)) {
                        oldIdGradoOfSeccionCollectionNewSeccion.getSeccionCollection().remove(seccionCollectionNewSeccion);
                        oldIdGradoOfSeccionCollectionNewSeccion = em.merge(oldIdGradoOfSeccionCollectionNewSeccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = grado.getIdGrado();
                if (findGrado(id) == null) {
                    throw new NonexistentEntityException("The grado with id " + id + " no longer exists.");
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
            Grado grado;
            try {
                grado = em.getReference(Grado.class, id);
                grado.getIdGrado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grado with id " + id + " no longer exists.", enfe);
            }
            Collection<Seccion> seccionCollection = grado.getSeccionCollection();
            for (Seccion seccionCollectionSeccion : seccionCollection) {
                seccionCollectionSeccion.setIdGrado(null);
                seccionCollectionSeccion = em.merge(seccionCollectionSeccion);
            }
            em.remove(grado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grado> findGradoEntities() {
        return findGradoEntities(true, -1, -1);
    }

    public List<Grado> findGradoEntities(int maxResults, int firstResult) {
        return findGradoEntities(false, maxResults, firstResult);
    }

    private List<Grado> findGradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grado.class));
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

    public Grado findGrado(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grado.class, id);
        } finally {
            em.close();
        }
    }

    public int getGradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grado> rt = cq.from(Grado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
