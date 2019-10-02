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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Logica_Negocios.SeccionJpaController;
/**
 *
 * @author Amilcar
 */
public class GradoJpaController implements Serializable {

    public GradoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ColegioPU");
    }
    //variables
    private EntityManagerFactory emf = null;
    Seccion clasSeccion = new Seccion();
    SeccionJpaController controlSeccion = new SeccionJpaController();
    Character varSecc;
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grado grado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion idSeccion = grado.getIdSeccion();
            if (idSeccion != null) {
                idSeccion = em.getReference(idSeccion.getClass(), idSeccion.getIdSeccion());
                grado.setIdSeccion(idSeccion);
            }
            em.persist(grado);
            if (idSeccion != null) {
                idSeccion.getGradoCollection().add(grado);
                idSeccion = em.merge(idSeccion);
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
            Seccion idSeccionOld = persistentGrado.getIdSeccion();
            Seccion idSeccionNew = grado.getIdSeccion();
            if (idSeccionNew != null) {
                idSeccionNew = em.getReference(idSeccionNew.getClass(), idSeccionNew.getIdSeccion());
                grado.setIdSeccion(idSeccionNew);
            }
            grado = em.merge(grado);
            if (idSeccionOld != null && !idSeccionOld.equals(idSeccionNew)) {
                idSeccionOld.getGradoCollection().remove(grado);
                idSeccionOld = em.merge(idSeccionOld);
            }
            if (idSeccionNew != null && !idSeccionNew.equals(idSeccionOld)) {
                idSeccionNew.getGradoCollection().add(grado);
                idSeccionNew = em.merge(idSeccionNew);
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
            Seccion idSeccion = grado.getIdSeccion();
            if (idSeccion != null) {
                idSeccion.getGradoCollection().remove(grado);
                idSeccion = em.merge(idSeccion);
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
     public void mostrarGrado(JTable tabla){
       DefaultTableModel modelo = null;
       String[] titulo = {"ID grado", "Grado", "Seccion", "Año creacion"};
        modelo = new DefaultTableModel(null,titulo);
        List<Grado> lista = findGradoEntities();
        List<Seccion> listaSec = controlSeccion.findSeccionEntities();        
        String[] camposGrado= new String[11];
        String id1="", id2="";
        for (Grado item : lista) {  
            id1=item.getIdSeccion().toString();
            for (Seccion sec : listaSec) {
                id2=sec.getSeccion().toString();
                if (id1.equals(id2)) {
                    varSecc=sec.getSeccion();
                }                
            }
            camposGrado[0] = item.getIdGrado()+"";
            camposGrado[1] = item.getGrado()+"";
            camposGrado[2] = varSecc+"";
            camposGrado[3] = item.getAnioCreacion()+"";
            
             
            
            modelo.addRow(camposGrado);
        }
        tabla.setModel(modelo);
    }

}
