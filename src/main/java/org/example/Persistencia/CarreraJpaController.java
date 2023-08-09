/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.example.Logica.Materia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.example.Logica.Carrera;
import org.example.Persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author agust
 */
public class CarreraJpaController implements Serializable {

    public CarreraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public CarreraJpaController(){
        emf = Persistence.createEntityManagerFactory("pruebaJPA-PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carrera carrera) {
        if (carrera.getListaMaterias() == null) {
            carrera.setListaMaterias(new ArrayList<Materia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Materia> attachedListaMaterias = new ArrayList<Materia>();
            for (Materia listaMateriasMateriaToAttach : carrera.getListaMaterias()) {
                listaMateriasMateriaToAttach =
                        em.getReference(listaMateriasMateriaToAttach.getClass(),
                        listaMateriasMateriaToAttach.getId());
                attachedListaMaterias.add(listaMateriasMateriaToAttach);
            }
            carrera.setListaMaterias(attachedListaMaterias);
            em.persist(carrera);
            for (Materia listaMateriasMateria : carrera.getListaMaterias()) {
                Carrera oldCarrOfListaMateriasMateria =
                        listaMateriasMateria.getCarr();
                listaMateriasMateria.setCarr(carrera);
                listaMateriasMateria = em.merge(listaMateriasMateria);
                if (oldCarrOfListaMateriasMateria != null) {
                    oldCarrOfListaMateriasMateria.getListaMaterias().
                            remove(listaMateriasMateria);
                    oldCarrOfListaMateriasMateria =
                            em.merge(oldCarrOfListaMateriasMateria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carrera carrera) throws NonexistentEntityException,
            Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera persistentCarrera = em.find(Carrera.class, carrera.getId());
            ArrayList<Materia> listaMateriasOld =
                    persistentCarrera.getListaMaterias();
            ArrayList<Materia> listaMateriasNew = carrera.getListaMaterias();
            ArrayList<Materia> attachedListaMateriasNew =
                    new ArrayList<Materia>();
            for (Materia listaMateriasNewMateriaToAttach : listaMateriasNew) {
                listaMateriasNewMateriaToAttach =
                        em.getReference(listaMateriasNewMateriaToAttach.getClass(),
                        listaMateriasNewMateriaToAttach.getId());
                attachedListaMateriasNew.add(listaMateriasNewMateriaToAttach);
            }
            listaMateriasNew = attachedListaMateriasNew;
            carrera.setListaMaterias(listaMateriasNew);
            carrera = em.merge(carrera);
            for (Materia listaMateriasOldMateria : listaMateriasOld) {
                if (!listaMateriasNew.contains(listaMateriasOldMateria)) {
                    listaMateriasOldMateria.setCarr(null);
                    listaMateriasOldMateria = em.merge(listaMateriasOldMateria);
                }
            }
            for (Materia listaMateriasNewMateria : listaMateriasNew) {
                if (!listaMateriasOld.contains(listaMateriasNewMateria)) {
                    Carrera oldCarrOfListaMateriasNewMateria =
                            listaMateriasNewMateria.getCarr();
                    listaMateriasNewMateria.setCarr(carrera);
                    listaMateriasNewMateria = em.merge(listaMateriasNewMateria);
                    if (oldCarrOfListaMateriasNewMateria != null &&
                            !oldCarrOfListaMateriasNewMateria.equals(carrera)) {
                        oldCarrOfListaMateriasNewMateria.getListaMaterias().
                                remove(listaMateriasNewMateria);
                        oldCarrOfListaMateriasNewMateria =
                                em.merge(oldCarrOfListaMateriasNewMateria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = carrera.getId();
                if (findCarrera(id) == null) {
                    throw new NonexistentEntityException("The carrera with id " +
                            id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera carrera;
            try {
                carrera = em.getReference(Carrera.class, id);
                carrera.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carrera with id " + id +
                        " no longer exists.", enfe);
            }
            ArrayList<Materia> listaMaterias = carrera.getListaMaterias();
            for (Materia listaMateriasMateria : listaMaterias) {
                listaMateriasMateria.setCarr(null);
                listaMateriasMateria = em.merge(listaMateriasMateria);
            }
            em.remove(carrera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carrera> findCarreraEntities() {
        return findCarreraEntities(true, -1, -1);
    }

    public List<Carrera> findCarreraEntities(int maxResults, int firstResult) {
        return findCarreraEntities(false, maxResults, firstResult);
    }

    private List<Carrera> findCarreraEntities(boolean all, int maxResults,
            int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrera.class));
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

    public Carrera findCarrera(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarreraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrera> rt = cq.from(Carrera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
