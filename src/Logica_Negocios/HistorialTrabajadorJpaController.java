/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocios;

import Acceso_Datos.HistorialTrabajador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Acceso_Datos.TipoUsuario;
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
public class HistorialTrabajadorJpaController implements Serializable {

    public HistorialTrabajadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistorialTrabajador historialTrabajador) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoUsuario idTipo = historialTrabajador.getIdTipo();
            if (idTipo != null) {
                idTipo = em.getReference(idTipo.getClass(), idTipo.getIdTipo());
                historialTrabajador.setIdTipo(idTipo);
            }
            Usuario idUsuario = historialTrabajador.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                historialTrabajador.setIdUsuario(idUsuario);
            }
            em.persist(historialTrabajador);
            if (idTipo != null) {
                idTipo.getHistorialTrabajadorCollection().add(historialTrabajador);
                idTipo = em.merge(idTipo);
            }
            if (idUsuario != null) {
                idUsuario.getHistorialTrabajadorCollection().add(historialTrabajador);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistorialTrabajador(historialTrabajador.getIdHistorialTrabajador()) != null) {
                throw new PreexistingEntityException("HistorialTrabajador " + historialTrabajador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistorialTrabajador historialTrabajador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistorialTrabajador persistentHistorialTrabajador = em.find(HistorialTrabajador.class, historialTrabajador.getIdHistorialTrabajador());
            TipoUsuario idTipoOld = persistentHistorialTrabajador.getIdTipo();
            TipoUsuario idTipoNew = historialTrabajador.getIdTipo();
            Usuario idUsuarioOld = persistentHistorialTrabajador.getIdUsuario();
            Usuario idUsuarioNew = historialTrabajador.getIdUsuario();
            if (idTipoNew != null) {
                idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getIdTipo());
                historialTrabajador.setIdTipo(idTipoNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                historialTrabajador.setIdUsuario(idUsuarioNew);
            }
            historialTrabajador = em.merge(historialTrabajador);
            if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
                idTipoOld.getHistorialTrabajadorCollection().remove(historialTrabajador);
                idTipoOld = em.merge(idTipoOld);
            }
            if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
                idTipoNew.getHistorialTrabajadorCollection().add(historialTrabajador);
                idTipoNew = em.merge(idTipoNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getHistorialTrabajadorCollection().remove(historialTrabajador);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getHistorialTrabajadorCollection().add(historialTrabajador);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = historialTrabajador.getIdHistorialTrabajador();
                if (findHistorialTrabajador(id) == null) {
                    throw new NonexistentEntityException("The historialTrabajador with id " + id + " no longer exists.");
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
            HistorialTrabajador historialTrabajador;
            try {
                historialTrabajador = em.getReference(HistorialTrabajador.class, id);
                historialTrabajador.getIdHistorialTrabajador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialTrabajador with id " + id + " no longer exists.", enfe);
            }
            TipoUsuario idTipo = historialTrabajador.getIdTipo();
            if (idTipo != null) {
                idTipo.getHistorialTrabajadorCollection().remove(historialTrabajador);
                idTipo = em.merge(idTipo);
            }
            Usuario idUsuario = historialTrabajador.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getHistorialTrabajadorCollection().remove(historialTrabajador);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(historialTrabajador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistorialTrabajador> findHistorialTrabajadorEntities() {
        return findHistorialTrabajadorEntities(true, -1, -1);
    }

    public List<HistorialTrabajador> findHistorialTrabajadorEntities(int maxResults, int firstResult) {
        return findHistorialTrabajadorEntities(false, maxResults, firstResult);
    }

    private List<HistorialTrabajador> findHistorialTrabajadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialTrabajador.class));
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

    public HistorialTrabajador findHistorialTrabajador(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialTrabajador.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialTrabajadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistorialTrabajador> rt = cq.from(HistorialTrabajador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
