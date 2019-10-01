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
import Acceso_Datos.Responsable;
import Logica_Negocios.exceptions.NonexistentEntityException;
import Logica_Negocios.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amilcar
 */
public class ResponsableJpaController implements Serializable {

    public ResponsableJpaController() {
       // this.emf = Persistence.createEntityManagerFactory("ColegioPU");
        this.emf = Persistence.createEntityManagerFactory("ColegioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Responsable responsable) throws PreexistingEntityException, Exception {
        if (responsable.getAlumnoCollection() == null) {
            responsable.setAlumnoCollection(new ArrayList<Alumno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Alumno> attachedAlumnoCollection = new ArrayList<Alumno>();
            for (Alumno alumnoCollectionAlumnoToAttach : responsable.getAlumnoCollection()) {
                alumnoCollectionAlumnoToAttach = em.getReference(alumnoCollectionAlumnoToAttach.getClass(), alumnoCollectionAlumnoToAttach.getIdAlumno());
                attachedAlumnoCollection.add(alumnoCollectionAlumnoToAttach);
            }
            responsable.setAlumnoCollection(attachedAlumnoCollection);
            em.persist(responsable);
            for (Alumno alumnoCollectionAlumno : responsable.getAlumnoCollection()) {
                Responsable oldIdResponsableOfAlumnoCollectionAlumno = alumnoCollectionAlumno.getIdResponsable();
                alumnoCollectionAlumno.setIdResponsable(responsable);
                alumnoCollectionAlumno = em.merge(alumnoCollectionAlumno);
                if (oldIdResponsableOfAlumnoCollectionAlumno != null) {
                    oldIdResponsableOfAlumnoCollectionAlumno.getAlumnoCollection().remove(alumnoCollectionAlumno);
                    oldIdResponsableOfAlumnoCollectionAlumno = em.merge(oldIdResponsableOfAlumnoCollectionAlumno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResponsable(responsable.getIdResponsable()) != null) {
                throw new PreexistingEntityException("Responsable " + responsable + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Responsable responsable) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsable persistentResponsable = em.find(Responsable.class, responsable.getIdResponsable());
            Collection<Alumno> alumnoCollectionOld = persistentResponsable.getAlumnoCollection();
            Collection<Alumno> alumnoCollectionNew = responsable.getAlumnoCollection();
            Collection<Alumno> attachedAlumnoCollectionNew = new ArrayList<Alumno>();
            for (Alumno alumnoCollectionNewAlumnoToAttach : alumnoCollectionNew) {
                alumnoCollectionNewAlumnoToAttach = em.getReference(alumnoCollectionNewAlumnoToAttach.getClass(), alumnoCollectionNewAlumnoToAttach.getIdAlumno());
                attachedAlumnoCollectionNew.add(alumnoCollectionNewAlumnoToAttach);
            }
            alumnoCollectionNew = attachedAlumnoCollectionNew;
            responsable.setAlumnoCollection(alumnoCollectionNew);
            responsable = em.merge(responsable);
            for (Alumno alumnoCollectionOldAlumno : alumnoCollectionOld) {
                if (!alumnoCollectionNew.contains(alumnoCollectionOldAlumno)) {
                    alumnoCollectionOldAlumno.setIdResponsable(null);
                    alumnoCollectionOldAlumno = em.merge(alumnoCollectionOldAlumno);
                }
            }
            for (Alumno alumnoCollectionNewAlumno : alumnoCollectionNew) {
                if (!alumnoCollectionOld.contains(alumnoCollectionNewAlumno)) {
                    Responsable oldIdResponsableOfAlumnoCollectionNewAlumno = alumnoCollectionNewAlumno.getIdResponsable();
                    alumnoCollectionNewAlumno.setIdResponsable(responsable);
                    alumnoCollectionNewAlumno = em.merge(alumnoCollectionNewAlumno);
                    if (oldIdResponsableOfAlumnoCollectionNewAlumno != null && !oldIdResponsableOfAlumnoCollectionNewAlumno.equals(responsable)) {
                        oldIdResponsableOfAlumnoCollectionNewAlumno.getAlumnoCollection().remove(alumnoCollectionNewAlumno);
                        oldIdResponsableOfAlumnoCollectionNewAlumno = em.merge(oldIdResponsableOfAlumnoCollectionNewAlumno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = responsable.getIdResponsable();
                if (findResponsable(id) == null) {
                    throw new NonexistentEntityException("The responsable with id " + id + " no longer exists.");
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
            Responsable responsable;
            try {
                responsable = em.getReference(Responsable.class, id);
                responsable.getIdResponsable();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The responsable with id " + id + " no longer exists.", enfe);
            }
            Collection<Alumno> alumnoCollection = responsable.getAlumnoCollection();
            for (Alumno alumnoCollectionAlumno : alumnoCollection) {
                alumnoCollectionAlumno.setIdResponsable(null);
                alumnoCollectionAlumno = em.merge(alumnoCollectionAlumno);
            }
            em.remove(responsable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Responsable> findResponsableEntities() {
        return findResponsableEntities(true, -1, -1);
    }

    public List<Responsable> findResponsableEntities(int maxResults, int firstResult) {
        return findResponsableEntities(false, maxResults, firstResult);
    }

    private List<Responsable> findResponsableEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Responsable.class));
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

    public Responsable findResponsable(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Responsable.class, id);
        } finally {
            em.close();
        }
    }

    public int getResponsableCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Responsable> rt = cq.from(Responsable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public void mostrarResponsable(JTable tabla){
       DefaultTableModel modelo = null;
       String[] titulo = {"ID responsable", "Nombre", "Apellido", "Dirección"," DUI","Telefono", "Profesion","Lugar de trabajo", "Telefono de trabajo","Dirección trabajo"};
        modelo = new DefaultTableModel(null,titulo);
        List<Responsable> lista = findResponsableEntities();
        String[] camposRepresentante = new String[11];
        for (Responsable item : lista) {
            camposRepresentante[0] = item.getIdResponsable()+"";
            camposRepresentante[1] = item.getNombre()+"";
            camposRepresentante[2] = item.getApellido()+"";
            camposRepresentante[3] = item.getDireccion()+"";
            camposRepresentante[4] = item.getDui()+"";
            camposRepresentante[5] = item.getTelefono()+"";
            camposRepresentante[6] = item.getProfesion()+"";
            camposRepresentante[7] = item.getLugarTrabajo()+"";
            camposRepresentante[8] = item.getTelefonoTrabajo()+"";
            
            modelo.addRow(camposRepresentante);
        }
        tabla.setModel(modelo);
    }
}
