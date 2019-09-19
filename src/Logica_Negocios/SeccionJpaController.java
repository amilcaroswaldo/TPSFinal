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
import Acceso_Datos.Grado;
import Acceso_Datos.Seccion;
import Acceso_Datos.Usuario;
import Logica_Negocios.exceptions.NonexistentEntityException;
import Logica_Negocios.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Amilcar
 */
public class SeccionJpaController implements Serializable {

    public SeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Seccion seccion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grado idGrado = seccion.getIdGrado();
            if (idGrado != null) {
                idGrado = em.getReference(idGrado.getClass(), idGrado.getIdGrado());
                seccion.setIdGrado(idGrado);
            }
            Usuario idUsuario = seccion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                seccion.setIdUsuario(idUsuario);
            }
            em.persist(seccion);
            if (idGrado != null) {
                idGrado.getSeccionCollection().add(seccion);
                idGrado = em.merge(idGrado);
            }
            if (idUsuario != null) {
                idUsuario.getSeccionCollection().add(seccion);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeccion(seccion.getIdSeccion()) != null) {
                throw new PreexistingEntityException("Seccion " + seccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Seccion seccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion persistentSeccion = em.find(Seccion.class, seccion.getIdSeccion());
            Grado idGradoOld = persistentSeccion.getIdGrado();
            Grado idGradoNew = seccion.getIdGrado();
            Usuario idUsuarioOld = persistentSeccion.getIdUsuario();
            Usuario idUsuarioNew = seccion.getIdUsuario();
            if (idGradoNew != null) {
                idGradoNew = em.getReference(idGradoNew.getClass(), idGradoNew.getIdGrado());
                seccion.setIdGrado(idGradoNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                seccion.setIdUsuario(idUsuarioNew);
            }
            seccion = em.merge(seccion);
            if (idGradoOld != null && !idGradoOld.equals(idGradoNew)) {
                idGradoOld.getSeccionCollection().remove(seccion);
                idGradoOld = em.merge(idGradoOld);
            }
            if (idGradoNew != null && !idGradoNew.equals(idGradoOld)) {
                idGradoNew.getSeccionCollection().add(seccion);
                idGradoNew = em.merge(idGradoNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getSeccionCollection().remove(seccion);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getSeccionCollection().add(seccion);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = seccion.getIdSeccion();
                if (findSeccion(id) == null) {
                    throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.");
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
            Seccion seccion;
            try {
                seccion = em.getReference(Seccion.class, id);
                seccion.getIdSeccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.", enfe);
            }
            Grado idGrado = seccion.getIdGrado();
            if (idGrado != null) {
                idGrado.getSeccionCollection().remove(seccion);
                idGrado = em.merge(idGrado);
            }
            Usuario idUsuario = seccion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getSeccionCollection().remove(seccion);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(seccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Seccion> findSeccionEntities() {
        return findSeccionEntities(true, -1, -1);
    }

    public List<Seccion> findSeccionEntities(int maxResults, int firstResult) {
        return findSeccionEntities(false, maxResults, firstResult);
    }

    private List<Seccion> findSeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Seccion.class));
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

    public Seccion findSeccion(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Seccion> rt = cq.from(Seccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
