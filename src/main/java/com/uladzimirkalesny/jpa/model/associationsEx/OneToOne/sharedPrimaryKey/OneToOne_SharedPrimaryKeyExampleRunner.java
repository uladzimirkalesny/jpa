package com.uladzimirkalesny.jpa.model.associationsEx.OneToOne.sharedPrimaryKey;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class OneToOne_SharedPrimaryKeyExampleRunner {

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

            Address shippingAddress = new Address();
            shippingAddress.setCity("City");
            shippingAddress.setStreet("Street");
            shippingAddress.setZipcode("ZIPCODE");

            entityManager.persist(shippingAddress);

            System.out.println("shippingAddress.getId() = " + shippingAddress.getId());

            User user = new User(shippingAddress.getId(), "Uladzimir", shippingAddress);
            entityManager.persist(user);

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
