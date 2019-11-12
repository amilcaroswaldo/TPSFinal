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
import Acceso_Datos.HistorialTrabajador;
import Acceso_Datos.TipoUsuario;
import java.util.ArrayList;
import java.util.Collection;
import Acceso_Datos.Usuario;
import Logica_Negocios.exceptions.NonexistentEntityException;
import Logica_Negocios.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;

/**
 *
 * @author Amilcar
 */
public class TipoUsuarioJpaController implements Serializable {

    public TipoUsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ColegioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoUsuario tipoUsuario) throws PreexistingEntityException, Exception {
        if (tipoUsuario.getHistorialTrabajadorCollection() == null) {
            tipoUsuario.setHistorialTrabajadorCollection(new ArrayList<HistorialTrabajador>());
        }
        if (tipoUsuario.getUsuarioCollection() == null) {
            tipoUsuario.setUsuarioCollection(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<HistorialTrabajador> attachedHistorialTrabajadorCollection = new ArrayList<HistorialTrabajador>();
            for (HistorialTrabajador historialTrabajadorCollectionHistorialTrabajadorToAttach : tipoUsuario.getHistorialTrabajadorCollection()) {
                historialTrabajadorCollectionHistorialTrabajadorToAttach = em.getReference(historialTrabajadorCollectionHistorialTrabajadorToAttach.getClass(), historialTrabajadorCollectionHistorialTrabajadorToAttach.getIdHistorialTrabajador());
                attachedHistorialTrabajadorCollection.add(historialTrabajadorCollectionHistorialTrabajadorToAttach);
            }
            tipoUsuario.setHistorialTrabajadorCollection(attachedHistorialTrabajadorCollection);
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : tipoUsuario.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getIdUsuario());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            tipoUsuario.setUsuarioCollection(attachedUsuarioCollection);
            em.persist(tipoUsuario);
            for (HistorialTrabajador historialTrabajadorCollectionHistorialTrabajador : tipoUsuario.getHistorialTrabajadorCollection()) {
                TipoUsuario oldIdTipoOfHistorialTrabajadorCollectionHistorialTrabajador = historialTrabajadorCollectionHistorialTrabajador.getIdTipo();
                historialTrabajadorCollectionHistorialTrabajador.setIdTipo(tipoUsuario);
                historialTrabajadorCollectionHistorialTrabajador = em.merge(historialTrabajadorCollectionHistorialTrabajador);
                if (oldIdTipoOfHistorialTrabajadorCollectionHistorialTrabajador != null) {
                    oldIdTipoOfHistorialTrabajadorCollectionHistorialTrabajador.getHistorialTrabajadorCollection().remove(historialTrabajadorCollectionHistorialTrabajador);
                    oldIdTipoOfHistorialTrabajadorCollectionHistorialTrabajador = em.merge(oldIdTipoOfHistorialTrabajadorCollectionHistorialTrabajador);
                }
            }
            for (Usuario usuarioCollectionUsuario : tipoUsuario.getUsuarioCollection()) {
                TipoUsuario oldIdTipoOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getIdTipo();
                usuarioCollectionUsuario.setIdTipo(tipoUsuario);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldIdTipoOfUsuarioCollectionUsuario != null) {
                    oldIdTipoOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldIdTipoOfUsuarioCollectionUsuario = em.merge(oldIdTipoOfUsuarioCollectionUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoUsuario(tipoUsuario.getIdTipo()) != null) {
                throw new PreexistingEntityException("TipoUsuario " + tipoUsuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoUsuario tipoUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoUsuario persistentTipoUsuario = em.find(TipoUsuario.class, tipoUsuario.getIdTipo());
            Collection<HistorialTrabajador> historialTrabajadorCollectionOld = persistentTipoUsuario.getHistorialTrabajadorCollection();
            Collection<HistorialTrabajador> historialTrabajadorCollectionNew = tipoUsuario.getHistorialTrabajadorCollection();
            Collection<Usuario> usuarioCollectionOld = persistentTipoUsuario.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = tipoUsuario.getUsuarioCollection();
            Collection<HistorialTrabajador> attachedHistorialTrabajadorCollectionNew = new ArrayList<HistorialTrabajador>();
            for (HistorialTrabajador historialTrabajadorCollectionNewHistorialTrabajadorToAttach : historialTrabajadorCollectionNew) {
                historialTrabajadorCollectionNewHistorialTrabajadorToAttach = em.getReference(historialTrabajadorCollectionNewHistorialTrabajadorToAttach.getClass(), historialTrabajadorCollectionNewHistorialTrabajadorToAttach.getIdHistorialTrabajador());
                attachedHistorialTrabajadorCollectionNew.add(historialTrabajadorCollectionNewHistorialTrabajadorToAttach);
            }
            historialTrabajadorCollectionNew = attachedHistorialTrabajadorCollectionNew;
            tipoUsuario.setHistorialTrabajadorCollection(historialTrabajadorCollectionNew);
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getIdUsuario());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            tipoUsuario.setUsuarioCollection(usuarioCollectionNew);
            tipoUsuario = em.merge(tipoUsuario);
            for (HistorialTrabajador historialTrabajadorCollectionOldHistorialTrabajador : historialTrabajadorCollectionOld) {
                if (!historialTrabajadorCollectionNew.contains(historialTrabajadorCollectionOldHistorialTrabajador)) {
                    historialTrabajadorCollectionOldHistorialTrabajador.setIdTipo(null);
                    historialTrabajadorCollectionOldHistorialTrabajador = em.merge(historialTrabajadorCollectionOldHistorialTrabajador);
                }
            }
            for (HistorialTrabajador historialTrabajadorCollectionNewHistorialTrabajador : historialTrabajadorCollectionNew) {
                if (!historialTrabajadorCollectionOld.contains(historialTrabajadorCollectionNewHistorialTrabajador)) {
                    TipoUsuario oldIdTipoOfHistorialTrabajadorCollectionNewHistorialTrabajador = historialTrabajadorCollectionNewHistorialTrabajador.getIdTipo();
                    historialTrabajadorCollectionNewHistorialTrabajador.setIdTipo(tipoUsuario);
                    historialTrabajadorCollectionNewHistorialTrabajador = em.merge(historialTrabajadorCollectionNewHistorialTrabajador);
                    if (oldIdTipoOfHistorialTrabajadorCollectionNewHistorialTrabajador != null && !oldIdTipoOfHistorialTrabajadorCollectionNewHistorialTrabajador.equals(tipoUsuario)) {
                        oldIdTipoOfHistorialTrabajadorCollectionNewHistorialTrabajador.getHistorialTrabajadorCollection().remove(historialTrabajadorCollectionNewHistorialTrabajador);
                        oldIdTipoOfHistorialTrabajadorCollectionNewHistorialTrabajador = em.merge(oldIdTipoOfHistorialTrabajadorCollectionNewHistorialTrabajador);
                    }
                }
            }
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    usuarioCollectionOldUsuario.setIdTipo(null);
                    usuarioCollectionOldUsuario = em.merge(usuarioCollectionOldUsuario);
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    TipoUsuario oldIdTipoOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getIdTipo();
                    usuarioCollectionNewUsuario.setIdTipo(tipoUsuario);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldIdTipoOfUsuarioCollectionNewUsuario != null && !oldIdTipoOfUsuarioCollectionNewUsuario.equals(tipoUsuario)) {
                        oldIdTipoOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldIdTipoOfUsuarioCollectionNewUsuario = em.merge(oldIdTipoOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = tipoUsuario.getIdTipo();
                if (findTipoUsuario(id) == null) {
                    throw new NonexistentEntityException("The tipoUsuario with id " + id + " no longer exists.");
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
            TipoUsuario tipoUsuario;
            try {
                tipoUsuario = em.getReference(TipoUsuario.class, id);
                tipoUsuario.getIdTipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoUsuario with id " + id + " no longer exists.", enfe);
            }
            Collection<HistorialTrabajador> historialTrabajadorCollection = tipoUsuario.getHistorialTrabajadorCollection();
            for (HistorialTrabajador historialTrabajadorCollectionHistorialTrabajador : historialTrabajadorCollection) {
                historialTrabajadorCollectionHistorialTrabajador.setIdTipo(null);
                historialTrabajadorCollectionHistorialTrabajador = em.merge(historialTrabajadorCollectionHistorialTrabajador);
            }
            Collection<Usuario> usuarioCollection = tipoUsuario.getUsuarioCollection();
            for (Usuario usuarioCollectionUsuario : usuarioCollection) {
                usuarioCollectionUsuario.setIdTipo(null);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            em.remove(tipoUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoUsuario> findTipoUsuarioEntities() {
        return findTipoUsuarioEntities(true, -1, -1);
    }

    public List<TipoUsuario> findTipoUsuarioEntities(int maxResults, int firstResult) {
        return findTipoUsuarioEntities(false, maxResults, firstResult);
    }

    private List<TipoUsuario> findTipoUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoUsuario.class));
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

    public TipoUsuario findTipoUsuario(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoUsuario> rt = cq.from(TipoUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public void comboTipo(JComboBox<TipoUsuario> combo){
        try {
            List<TipoUsuario> lista = findTipoUsuarioEntities();
            for (TipoUsuario item : lista) {
                combo.addItem(new TipoUsuario(
                        item.getIdTipo(),
                        item.getTipo()
                )
                );
            }
        } catch (Exception e) {
        }
    }
}
