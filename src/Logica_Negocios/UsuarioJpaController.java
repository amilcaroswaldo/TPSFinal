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
import Acceso_Datos.TipoUsuario;
import Acceso_Datos.HistorialTrabajador;
import Acceso_Datos.Usuario;
import Logica_Negocios.exceptions.NonexistentEntityException;
import Logica_Negocios.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;

/**
 *
 * @author Amilcar
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ColegioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getHistorialTrabajadorCollection() == null) {
            usuario.setHistorialTrabajadorCollection(new ArrayList<HistorialTrabajador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoUsuario idTipo = usuario.getIdTipo();
            if (idTipo != null) {
                idTipo = em.getReference(idTipo.getClass(), idTipo.getIdTipo());
                usuario.setIdTipo(idTipo);
            }
            Collection<HistorialTrabajador> attachedHistorialTrabajadorCollection = new ArrayList<HistorialTrabajador>();
            for (HistorialTrabajador historialTrabajadorCollectionHistorialTrabajadorToAttach : usuario.getHistorialTrabajadorCollection()) {
                historialTrabajadorCollectionHistorialTrabajadorToAttach = em.getReference(historialTrabajadorCollectionHistorialTrabajadorToAttach.getClass(), historialTrabajadorCollectionHistorialTrabajadorToAttach.getIdHistorialTrabajador());
                attachedHistorialTrabajadorCollection.add(historialTrabajadorCollectionHistorialTrabajadorToAttach);
            }
            usuario.setHistorialTrabajadorCollection(attachedHistorialTrabajadorCollection);
            em.persist(usuario);
            if (idTipo != null) {
                idTipo.getUsuarioCollection().add(usuario);
                idTipo = em.merge(idTipo);
            }
            for (HistorialTrabajador historialTrabajadorCollectionHistorialTrabajador : usuario.getHistorialTrabajadorCollection()) {
                Usuario oldIdUsuarioOfHistorialTrabajadorCollectionHistorialTrabajador = historialTrabajadorCollectionHistorialTrabajador.getIdUsuario();
                historialTrabajadorCollectionHistorialTrabajador.setIdUsuario(usuario);
                historialTrabajadorCollectionHistorialTrabajador = em.merge(historialTrabajadorCollectionHistorialTrabajador);
                if (oldIdUsuarioOfHistorialTrabajadorCollectionHistorialTrabajador != null) {
                    oldIdUsuarioOfHistorialTrabajadorCollectionHistorialTrabajador.getHistorialTrabajadorCollection().remove(historialTrabajadorCollectionHistorialTrabajador);
                    oldIdUsuarioOfHistorialTrabajadorCollectionHistorialTrabajador = em.merge(oldIdUsuarioOfHistorialTrabajadorCollectionHistorialTrabajador);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            TipoUsuario idTipoOld = persistentUsuario.getIdTipo();
            TipoUsuario idTipoNew = usuario.getIdTipo();
            Collection<HistorialTrabajador> historialTrabajadorCollectionOld = persistentUsuario.getHistorialTrabajadorCollection();
            Collection<HistorialTrabajador> historialTrabajadorCollectionNew = usuario.getHistorialTrabajadorCollection();
            if (idTipoNew != null) {
                idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getIdTipo());
                usuario.setIdTipo(idTipoNew);
            }
            Collection<HistorialTrabajador> attachedHistorialTrabajadorCollectionNew = new ArrayList<HistorialTrabajador>();
            for (HistorialTrabajador historialTrabajadorCollectionNewHistorialTrabajadorToAttach : historialTrabajadorCollectionNew) {
                historialTrabajadorCollectionNewHistorialTrabajadorToAttach = em.getReference(historialTrabajadorCollectionNewHistorialTrabajadorToAttach.getClass(), historialTrabajadorCollectionNewHistorialTrabajadorToAttach.getIdHistorialTrabajador());
                attachedHistorialTrabajadorCollectionNew.add(historialTrabajadorCollectionNewHistorialTrabajadorToAttach);
            }
            historialTrabajadorCollectionNew = attachedHistorialTrabajadorCollectionNew;
            usuario.setHistorialTrabajadorCollection(historialTrabajadorCollectionNew);
            usuario = em.merge(usuario);
            if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
                idTipoOld.getUsuarioCollection().remove(usuario);
                idTipoOld = em.merge(idTipoOld);
            }
            if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
                idTipoNew.getUsuarioCollection().add(usuario);
                idTipoNew = em.merge(idTipoNew);
            }
            for (HistorialTrabajador historialTrabajadorCollectionOldHistorialTrabajador : historialTrabajadorCollectionOld) {
                if (!historialTrabajadorCollectionNew.contains(historialTrabajadorCollectionOldHistorialTrabajador)) {
                    historialTrabajadorCollectionOldHistorialTrabajador.setIdUsuario(null);
                    historialTrabajadorCollectionOldHistorialTrabajador = em.merge(historialTrabajadorCollectionOldHistorialTrabajador);
                }
            }
            for (HistorialTrabajador historialTrabajadorCollectionNewHistorialTrabajador : historialTrabajadorCollectionNew) {
                if (!historialTrabajadorCollectionOld.contains(historialTrabajadorCollectionNewHistorialTrabajador)) {
                    Usuario oldIdUsuarioOfHistorialTrabajadorCollectionNewHistorialTrabajador = historialTrabajadorCollectionNewHistorialTrabajador.getIdUsuario();
                    historialTrabajadorCollectionNewHistorialTrabajador.setIdUsuario(usuario);
                    historialTrabajadorCollectionNewHistorialTrabajador = em.merge(historialTrabajadorCollectionNewHistorialTrabajador);
                    if (oldIdUsuarioOfHistorialTrabajadorCollectionNewHistorialTrabajador != null && !oldIdUsuarioOfHistorialTrabajadorCollectionNewHistorialTrabajador.equals(usuario)) {
                        oldIdUsuarioOfHistorialTrabajadorCollectionNewHistorialTrabajador.getHistorialTrabajadorCollection().remove(historialTrabajadorCollectionNewHistorialTrabajador);
                        oldIdUsuarioOfHistorialTrabajadorCollectionNewHistorialTrabajador = em.merge(oldIdUsuarioOfHistorialTrabajadorCollectionNewHistorialTrabajador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            TipoUsuario idTipo = usuario.getIdTipo();
            if (idTipo != null) {
                idTipo.getUsuarioCollection().remove(usuario);
                idTipo = em.merge(idTipo);
            }
            Collection<HistorialTrabajador> historialTrabajadorCollection = usuario.getHistorialTrabajadorCollection();
            for (HistorialTrabajador historialTrabajadorCollectionHistorialTrabajador : historialTrabajadorCollection) {
                historialTrabajadorCollectionHistorialTrabajador.setIdUsuario(null);
                historialTrabajadorCollectionHistorialTrabajador = em.merge(historialTrabajadorCollectionHistorialTrabajador);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public void comboUsuario(JComboBox<Usuario> combo){
        try {
            List<Usuario> lista = findUsuarioEntities();
            for (Usuario item : lista) {
                String prueba = item.getIdUsuario()+"";
                if (prueba.equals("2")) {
                       combo.addItem(new Usuario(
                            item.getIdUsuario(),
                            item.getNombre(),
                            item.getApellido()         
                             )
                        );
                }
                
            }
        } catch (Exception e) {
        }
    }
    
}
