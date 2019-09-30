/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocios;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Acceso_Datos.Alumno;
import Acceso_Datos.Parentesco;
import Logica_Negocios.exceptions.NonexistentEntityException;
import Logica_Negocios.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Amilcar
 */
public class ParentescoJpaController implements Serializable {

    public ParentescoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ColegioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Parentesco parentesco) throws PreexistingEntityException, Exception {
        if (parentesco.getAlumnoCollection() == null) {
            parentesco.setAlumnoCollection(new ArrayList<Alumno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Alumno> attachedAlumnoCollection = new ArrayList<Alumno>();
            for (Alumno alumnoCollectionAlumnoToAttach : parentesco.getAlumnoCollection()) {
                alumnoCollectionAlumnoToAttach = em.getReference(alumnoCollectionAlumnoToAttach.getClass(), alumnoCollectionAlumnoToAttach.getIdAlumno());
                attachedAlumnoCollection.add(alumnoCollectionAlumnoToAttach);
            }
            parentesco.setAlumnoCollection(attachedAlumnoCollection);
            em.persist(parentesco);
            for (Alumno alumnoCollectionAlumno : parentesco.getAlumnoCollection()) {
                Parentesco oldIdParentescoOfAlumnoCollectionAlumno = alumnoCollectionAlumno.getIdParentesco();
                alumnoCollectionAlumno.setIdParentesco(parentesco);
                alumnoCollectionAlumno = em.merge(alumnoCollectionAlumno);
                if (oldIdParentescoOfAlumnoCollectionAlumno != null) {
                    oldIdParentescoOfAlumnoCollectionAlumno.getAlumnoCollection().remove(alumnoCollectionAlumno);
                    oldIdParentescoOfAlumnoCollectionAlumno = em.merge(oldIdParentescoOfAlumnoCollectionAlumno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findParentesco(parentesco.getIdParentesco()) != null) {
                throw new PreexistingEntityException("Parentesco " + parentesco + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Parentesco parentesco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Parentesco persistentParentesco = em.find(Parentesco.class, parentesco.getIdParentesco());
            Collection<Alumno> alumnoCollectionOld = persistentParentesco.getAlumnoCollection();
            Collection<Alumno> alumnoCollectionNew = parentesco.getAlumnoCollection();
            Collection<Alumno> attachedAlumnoCollectionNew = new ArrayList<Alumno>();
            for (Alumno alumnoCollectionNewAlumnoToAttach : alumnoCollectionNew) {
                alumnoCollectionNewAlumnoToAttach = em.getReference(alumnoCollectionNewAlumnoToAttach.getClass(), alumnoCollectionNewAlumnoToAttach.getIdAlumno());
                attachedAlumnoCollectionNew.add(alumnoCollectionNewAlumnoToAttach);
            }
            alumnoCollectionNew = attachedAlumnoCollectionNew;
            parentesco.setAlumnoCollection(alumnoCollectionNew);
            parentesco = em.merge(parentesco);
            for (Alumno alumnoCollectionOldAlumno : alumnoCollectionOld) {
                if (!alumnoCollectionNew.contains(alumnoCollectionOldAlumno)) {
                    alumnoCollectionOldAlumno.setIdParentesco(null);
                    alumnoCollectionOldAlumno = em.merge(alumnoCollectionOldAlumno);
                }
            }
            for (Alumno alumnoCollectionNewAlumno : alumnoCollectionNew) {
                if (!alumnoCollectionOld.contains(alumnoCollectionNewAlumno)) {
                    Parentesco oldIdParentescoOfAlumnoCollectionNewAlumno = alumnoCollectionNewAlumno.getIdParentesco();
                    alumnoCollectionNewAlumno.setIdParentesco(parentesco);
                    alumnoCollectionNewAlumno = em.merge(alumnoCollectionNewAlumno);
                    if (oldIdParentescoOfAlumnoCollectionNewAlumno != null && !oldIdParentescoOfAlumnoCollectionNewAlumno.equals(parentesco)) {
                        oldIdParentescoOfAlumnoCollectionNewAlumno.getAlumnoCollection().remove(alumnoCollectionNewAlumno);
                        oldIdParentescoOfAlumnoCollectionNewAlumno = em.merge(oldIdParentescoOfAlumnoCollectionNewAlumno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = parentesco.getIdParentesco();
                if (findParentesco(id) == null) {
                    throw new NonexistentEntityException("The parentesco with id " + id + " no longer exists.");
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
            Parentesco parentesco;
            try {
                parentesco = em.getReference(Parentesco.class, id);
                parentesco.getIdParentesco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parentesco with id " + id + " no longer exists.", enfe);
            }
            Collection<Alumno> alumnoCollection = parentesco.getAlumnoCollection();
            for (Alumno alumnoCollectionAlumno : alumnoCollection) {
                alumnoCollectionAlumno.setIdParentesco(null);
                alumnoCollectionAlumno = em.merge(alumnoCollectionAlumno);
            }
            em.remove(parentesco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Parentesco> findParentescoEntities() {
        return findParentescoEntities(true, -1, -1);
    }

    public List<Parentesco> findParentescoEntities(int maxResults, int firstResult) {
        return findParentescoEntities(false, maxResults, firstResult);
    }

    private List<Parentesco> findParentescoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Parentesco.class));
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

    public Parentesco findParentesco(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Parentesco.class, id);
        } finally {
            em.close();
        }
    }

    public int getParentescoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Parentesco> rt = cq.from(Parentesco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
