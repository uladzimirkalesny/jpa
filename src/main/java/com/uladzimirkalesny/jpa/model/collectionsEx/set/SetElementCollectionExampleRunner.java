package com.uladzimirkalesny.jpa.model.collectionsEx.set;

import com.uladzimirkalesny.jpa.constant.JpaConstants;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

public class SetElementCollectionExampleRunner {

    static Server server;

    static {
        try {
            server = Server.createTcpServer("-tcpPort", "8082", "-tcpAllowOthers").start();
        } catch (SQLException throwables) {
            // NOP
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(JpaConstants.PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Item item = new Item();

            Image image1 = new Image("img1", "image1", 640, 480);
            Image image2 = new Image("img2", "image2", 640, 480);

            item.setName("BEST ITEM EVER");
            item.setImages(new HashSet<>(Arrays.asList(image1, image2)));

            entityManager.persist(item);

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
