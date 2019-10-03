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
import Acceso_Datos.Materia;
import Acceso_Datos.Usuario;
import Acceso_Datos.UsuarioMate;
import Logica_Negocios.exceptions.NonexistentEntityException;
import Logica_Negocios.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Amilcar
 */
public class UsuarioMateJpaController implements Serializable {

    public UsuarioMateJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ColegioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioMate usuarioMate) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia idMateria = usuarioMate.getIdMateria();
            if (idMateria != null) {
                idMateria = em.getReference(idMateria.getClass(), idMateria.getIdMateria());
                usuarioMate.setIdMateria(idMateria);
            }
            Usuario idUsuario = usuarioMate.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                usuarioMate.setIdUsuario(idUsuario);
            }
            em.persist(usuarioMate);
            if (idMateria != null) {
                idMateria.getUsuarioMateCollection().add(usuarioMate);
                idMateria = em.merge(idMateria);
            }
            if (idUsuario != null) {
                idUsuario.getUsuarioMateCollection().add(usuarioMate);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarioMate(usuarioMate.getIdUsuaMate()) != null) {
                throw new PreexistingEntityException("UsuarioMate " + usuarioMate + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioMate usuarioMate) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioMate persistentUsuarioMate = em.find(UsuarioMate.class, usuarioMate.getIdUsuaMate());
            Materia idMateriaOld = persistentUsuarioMate.getIdMateria();
            Materia idMateriaNew = usuarioMate.getIdMateria();
            Usuario idUsuarioOld = persistentUsuarioMate.getIdUsuario();
            Usuario idUsuarioNew = usuarioMate.getIdUsuario();
            if (idMateriaNew != null) {
                idMateriaNew = em.getReference(idMateriaNew.getClass(), idMateriaNew.getIdMateria());
                usuarioMate.setIdMateria(idMateriaNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                usuarioMate.setIdUsuario(idUsuarioNew);
            }
            usuarioMate = em.merge(usuarioMate);
            if (idMateriaOld != null && !idMateriaOld.equals(idMateriaNew)) {
                idMateriaOld.getUsuarioMateCollection().remove(usuarioMate);
                idMateriaOld = em.merge(idMateriaOld);
            }
            if (idMateriaNew != null && !idMateriaNew.equals(idMateriaOld)) {
                idMateriaNew.getUsuarioMateCollection().add(usuarioMate);
                idMateriaNew = em.merge(idMateriaNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getUsuarioMateCollection().remove(usuarioMate);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getUsuarioMateCollection().add(usuarioMate);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = usuarioMate.getIdUsuaMate();
                if (findUsuarioMate(id) == null) {
                    throw new NonexistentEntityException("The usuarioMate with id " + id + " no longer exists.");
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
            UsuarioMate usuarioMate;
            try {
                usuarioMate = em.getReference(UsuarioMate.class, id);
                usuarioMate.getIdUsuaMate();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioMate with id " + id + " no longer exists.", enfe);
            }
            Materia idMateria = usuarioMate.getIdMateria();
            if (idMateria != null) {
                idMateria.getUsuarioMateCollection().remove(usuarioMate);
                idMateria = em.merge(idMateria);
            }
            Usuario idUsuario = usuarioMate.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getUsuarioMateCollection().remove(usuarioMate);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(usuarioMate);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioMate> findUsuarioMateEntities() {
        return findUsuarioMateEntities(true, -1, -1);
    }

    public List<UsuarioMate> findUsuarioMateEntities(int maxResults, int firstResult) {
        return findUsuarioMateEntities(false, maxResults, firstResult);
    }

    private List<UsuarioMate> findUsuarioMateEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioMate.class));
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

    public UsuarioMate findUsuarioMate(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioMate.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioMateCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioMate> rt = cq.from(UsuarioMate.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
