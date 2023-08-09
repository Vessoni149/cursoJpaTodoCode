/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.example.Logica.Carrera;
import org.example.Logica.Materia;
import org.example.Persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author agust
 */
public class MateriaJpaController implements Serializable {

    public MateriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public MateriaJpaController(){
        emf = Persistence.createEntityManagerFactory("pruebaJPA-PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materia materia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera carr = materia.getCarr();
            if (carr != null) {
                carr = em.getReference(carr.getClass(), carr.getId());
                materia.setCarr(carr);
            }
            em.persist(materia);
            if (carr != null) {
                carr.getListaMaterias().add(materia);
                carr = em.merge(carr);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materia materia) throws NonexistentEntityException,
            Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia persistentMateria = em.find(Materia.class, materia.getId());
            Carrera carrOld = persistentMateria.getCarr();
            Carrera carrNew = materia.getCarr();
            if (carrNew != null) {
                carrNew = em.getReference(carrNew.getClass(), carrNew.getId());
                materia.setCarr(carrNew);
            }
            materia = em.merge(materia);
            if (carrOld != null && !carrOld.equals(carrNew)) {
                carrOld.getListaMaterias().remove(materia);
                carrOld = em.merge(carrOld);
            }
            if (carrNew != null && !carrNew.equals(carrOld)) {
                carrNew.getListaMaterias().add(materia);
                carrNew = em.merge(carrNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = materia.getId();
                if (findMateria(id) == null) {
                    throw new NonexistentEntityException("The materia with id " +
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
            Materia materia;
            try {
                materia = em.getReference(Materia.class, id);
                materia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materia with id " + id +
                        " no longer exists.", enfe);
            }
            Carrera carr = materia.getCarr();
            if (carr != null) {
                carr.getListaMaterias().remove(materia);
                carr = em.merge(carr);
            }
            em.remove(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materia> findMateriaEntities() {
        return findMateriaEntities(true, -1, -1);
    }

    public List<Materia> findMateriaEntities(int maxResults, int firstResult) {
        return findMateriaEntities(false, maxResults, firstResult);
    }

    private List<Materia> findMateriaEntities(boolean all, int maxResults,
            int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materia.class));
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

    public Materia findMateria(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materia> rt = cq.from(Materia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
