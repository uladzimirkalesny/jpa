package com.uladzimirkalesny.jpa.model.associationsEx.ManyToMany;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class ManyToManyExampleRunner {

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

            Category category1 = new Category();
            category1.setName("CATEGORY_1");

            Category category2 = new Category();
            category2.setName("CATEGORY_2");


            Item item1 = new Item();
            item1.setName("ITEM_1");

            Item item2 = new Item();
            item2.setName("ITEM_2");


            category1.addItem(item1);
            item1.addCategory(category1);

            category1.addItem(item2);
            item2.addCategory(category1);

            category2.addItem(item1);
            item1.addCategory(category2);

            entityManager.persist(category1);
            entityManager.persist(category2);

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
