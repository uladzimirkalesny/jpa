package com.uladzimirkalesny.jpa.model.inheritanceEx.associations.OneToMany.bidirectional;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class OneToMany_ASSOCIATION_ExampleRunner {

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

            CreditCard creditCard = new CreditCard();
            creditCard.setOwner("Uladzimir");
            creditCard.setCardNumber("1");
            creditCard.setExpMonth("12");
            creditCard.setExpYear("2222");

            User user = new User();
            user.setName("Uladzimir");
            user.setBillingDetails(new HashSet<>(Arrays.asList(creditCard)));

            entityManager.persist(creditCard);
            entityManager.persist(user);

            User foundUser = entityManager.find(User.class, user.getId());
            Set<BillingDetails> billingDetails = foundUser.getBillingDetails();
            billingDetails.forEach(System.out::println);

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
