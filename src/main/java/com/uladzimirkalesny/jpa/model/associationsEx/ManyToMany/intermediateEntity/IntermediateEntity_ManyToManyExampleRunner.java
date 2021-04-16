package com.uladzimirkalesny.jpa.model.associationsEx.ManyToMany.intermediateEntity;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class IntermediateEntity_ManyToManyExampleRunner {

    static Server server;

    static {
        try {
            server = Server.createTcpServer("-tcpPort", "8082", "-tcpAllowOthers").start();
        } catch (SQLException throwables) {
            // NOP
        }
    }

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Category someCategory = new Category();
            someCategory.setName("Some Category");
            Category otherCategory = new Category();
            otherCategory.setName("Other Category");

            entityManager.persist(someCategory);
            entityManager.persist(otherCategory);

            Item someItem = new Item();
            someItem.setName("Some Item");
            Item otherItem = new Item();
            otherItem.setName("Other Item");

            entityManager.persist(someItem);
            entityManager.persist(otherItem);

            CategorizedItem linkOne = new CategorizedItem("johndoe", someCategory, someItem);
            CategorizedItem linkTwo = new CategorizedItem("johndoe", someCategory, otherItem);
            CategorizedItem linkThree = new CategorizedItem("johndoe", otherCategory, someItem);

            entityManager.persist(linkOne);
            entityManager.persist(linkTwo);
            entityManager.persist(linkThree);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();

            server.shutdown();
        }

    }


}
