package com.uladzimirkalesny.jpa.model.associationsEx.OneToOne.foreignKey;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class OneToOne_ForeignKeyExampleRunner {

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

            Address address = new Address();
            address.setCity("City");
            address.setStreet("Street");
            address.setZipcode("Zipcode");

            User user = new User();
            user.setUsername("Uladzimir");
            user.setShippingAddress(address);

            entityManager.persist(user);

            entityManager.createQuery("from User").getResultList()
                    .forEach(System.out::println);

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
