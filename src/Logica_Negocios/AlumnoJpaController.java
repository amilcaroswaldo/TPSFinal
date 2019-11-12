/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocios;

import Acceso_Datos.Alumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Acceso_Datos.Parentesco;
import Acceso_Datos.Responsable;
import Logica_Negocios.exceptions.NonexistentEntityException;
import Logica_Negocios.exceptions.PreexistingEntityException;
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
public class AlumnoJpaController implements Serializable {

    // variables
    ParentescoJpaController controlParentesco = new ParentescoJpaController();
    ResponsableJpaController controlResp = new ResponsableJpaController();
    public AlumnoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ColegioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Parentesco idParentesco = alumno.getIdParentesco();
            if (idParentesco != null) {
                idParentesco = em.getReference(idParentesco.getClass(), idParentesco.getIdParentesco());
                alumno.setIdParentesco(idParentesco);
            }
            Responsable idResponsable = alumno.getIdResponsable();
            if (idResponsable != null) {
                idResponsable = em.getReference(idResponsable.getClass(), idResponsable.getIdResponsable());
                alumno.setIdResponsable(idResponsable);
            }
            em.persist(alumno);
            if (idParentesco != null) {
                idParentesco.getAlumnoCollection().add(alumno);
                idParentesco = em.merge(idParentesco);
            }
            if (idResponsable != null) {
                idResponsable.getAlumnoCollection().add(alumno);
                idResponsable = em.merge(idResponsable);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlumno(alumno.getIdAlumno()) != null) {
                throw new PreexistingEntityException("Alumno " + alumno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getIdAlumno());
            Parentesco idParentescoOld = persistentAlumno.getIdParentesco();
            Parentesco idParentescoNew = alumno.getIdParentesco();
            Responsable idResponsableOld = persistentAlumno.getIdResponsable();
            Responsable idResponsableNew = alumno.getIdResponsable();
            if (idParentescoNew != null) {
                idParentescoNew = em.getReference(idParentescoNew.getClass(), idParentescoNew.getIdParentesco());
                alumno.setIdParentesco(idParentescoNew);
            }
            if (idResponsableNew != null) {
                idResponsableNew = em.getReference(idResponsableNew.getClass(), idResponsableNew.getIdResponsable());
                alumno.setIdResponsable(idResponsableNew);
            }
            alumno = em.merge(alumno);
            if (idParentescoOld != null && !idParentescoOld.equals(idParentescoNew)) {
                idParentescoOld.getAlumnoCollection().remove(alumno);
                idParentescoOld = em.merge(idParentescoOld);
            }
            if (idParentescoNew != null && !idParentescoNew.equals(idParentescoOld)) {
                idParentescoNew.getAlumnoCollection().add(alumno);
                idParentescoNew = em.merge(idParentescoNew);
            }
            if (idResponsableOld != null && !idResponsableOld.equals(idResponsableNew)) {
                idResponsableOld.getAlumnoCollection().remove(alumno);
                idResponsableOld = em.merge(idResponsableOld);
            }
            if (idResponsableNew != null && !idResponsableNew.equals(idResponsableOld)) {
                idResponsableNew.getAlumnoCollection().add(alumno);
                idResponsableNew = em.merge(idResponsableNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = alumno.getIdAlumno();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
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
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getIdAlumno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            Parentesco idParentesco = alumno.getIdParentesco();
            if (idParentesco != null) {
                idParentesco.getAlumnoCollection().remove(alumno);
                idParentesco = em.merge(idParentesco);
            }
            Responsable idResponsable = alumno.getIdResponsable();
            if (idResponsable != null) {
                idResponsable.getAlumnoCollection().remove(alumno);
                idResponsable = em.merge(idResponsable);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
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

    public Alumno findAlumno(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void mostrarAlumno(JTable tabla){
       DefaultTableModel modelo = null;
       String[] titulo = {"ID Alumno","ID Responsable", "Responsable", "Nombre", "Apellido","Fecha de nacimiento", "Problemas de salud","Parentesco"};
        modelo = new DefaultTableModel(null,titulo);
        List<Alumno> lista = findAlumnoEntities();
        List<Responsable> listaResp = controlResp.findResponsableEntities();
        String  responsable="", item1R, item2R;
        String[] camposRepresentante = new String[11];        
        for (Alumno item : lista) {         
            for (Responsable item3 : listaResp) {
                 item1R=item.getIdResponsable()+"";
                 item2R=item3.getIdResponsable()+"";
                if (item1R.equals(item2R)) {
                    responsable= item3.getNombre()+item3.getApellido();
                }
            }
            camposRepresentante[0] = item.getIdAlumno()+"";
            camposRepresentante[1] = item.getIdResponsable()+"";
            camposRepresentante[2] = responsable;
            camposRepresentante[3] = item.getNombre()+"";
            camposRepresentante[4] = item.getApellido()+"";
            camposRepresentante[5] = item.getFechaNacimiento()+"";
            camposRepresentante[6] = item.getProblemasSalud()+"";
            camposRepresentante[7] =item.getIdParentesco()+"";
            
            modelo.addRow(camposRepresentante);
        }
        tabla.setModel(modelo);
    }
}
