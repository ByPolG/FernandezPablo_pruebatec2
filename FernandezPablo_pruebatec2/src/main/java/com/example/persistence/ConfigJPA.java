package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfigJPA {

    // Se crea una instancia estática de EntityManagerFactory

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("turneroPU");

    // Se obtiene un EntityManager para interactuar con la base de datos

    public static EntityManager getEntityManager() {

        return emf.createEntityManager();
    }

    // Se cierra el EntityManagerFactory

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}