package com.uladzimirkalesny.jpa.model.compositionEx;

import com.uladzimirkalesny.jpa.model.compositionEx.Address;
import com.uladzimirkalesny.jpa.model.compositionEx.City;
import com.uladzimirkalesny.jpa.model.compositionEx.User;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class CompositionExampleRunner {

    static Server server;

    static {
        try {
            server = Server.createTcpServer("-tcpPort", "8082", "-tcpAllowOthers").start();
        } catch (SQLException throwables) {
            //
        }
    }

    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            User user = new User();
            user.setHomeAddress(new Address("Sovetskaya", new City("234123", "GOMEL", "RB")));
            user.setBillingAddress(new Address("Rechitsiy", new City("123123", "GOMEL", "RB")));
            user.setUserName("Uladzimir");

            entityManager.persist(user);

            entityManager.createQuery("from User").getResultList().forEach(System.out::println);

            transaction.commit();
        } finally {
            if(transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
//            server.shutdown();
        }
    }
}
