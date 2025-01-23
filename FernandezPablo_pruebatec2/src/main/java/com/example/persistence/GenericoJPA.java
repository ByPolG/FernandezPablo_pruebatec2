package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class GenericoJPA<T, ID> {

    private Class<T> entidadGenerica;

    public GenericoJPA(Class<T> entidadGenerica) {
        this.entidadGenerica = entidadGenerica;
    }

    // Metodo genérico para crear una entidad

    public void createGenerico(T nuevaEntidad) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            System.out.println("Intentando guardar la entidad: " + nuevaEntidad); // Log
            em.persist(nuevaEntidad);
            em.getTransaction().commit();
            System.out.println("Entidad guardada correctamente.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al guardar entidad: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Metodo genérico para encontrar una entidad usando su ID

    public Optional<T> findOneGenerico(ID idBuscado) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            return Optional.ofNullable(em.find(this.entidadGenerica, idBuscado));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return Optional.empty();
    }

    // Metodo genérico para encontrar todas las entidades

    public List<T> findAllGenerico() {
        EntityManager em = ConfigJPA.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entidadGenerica);
        Root<T> root = cq.from(this.entidadGenerica);
        cq.select(root);
        TypedQuery<T> query = em.createQuery(cq);
        return query.getResultList();
    }

    // Metodo genérico para actualizar los datos de una entidad

    public void updateGenerico(T actualizarElemento) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(actualizarElemento);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    // Metodo genérico para eliminar una entidad

    public void deleteGenerico(ID idEliminar) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            Optional<T> elementoEncontrado = Optional.ofNullable(em.find(this.entidadGenerica, idEliminar));
            if (elementoEncontrado.isPresent()) {
                em.remove(elementoEncontrado.get());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }
}