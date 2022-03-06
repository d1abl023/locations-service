package me.andreisuruceanu.locationsservice.service;

import me.andreisuruceanu.locationsservice.config.HibernateConfig;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

    public class HibernateService {

        private static SessionFactory sessionFactory;

        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                Configuration configuration = HibernateConfig.getConfiguration();
                sessionFactory = configuration.buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties())
                                .build()
                );
            }

            return sessionFactory;
        }

        public static EntityManager getEntityManager() {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("me.andreisuruceanu.restafe.userdataservice.dao");
            return entityManagerFactory.createEntityManager();
        }
    }
