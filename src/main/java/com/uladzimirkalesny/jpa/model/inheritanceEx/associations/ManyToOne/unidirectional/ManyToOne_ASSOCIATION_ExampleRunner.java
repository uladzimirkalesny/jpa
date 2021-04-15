package com.uladzimirkalesny.jpa.model.inheritanceEx.associations.ManyToOne.unidirectional;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class ManyToOne_ASSOCIATION_ExampleRunner {

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
            user.setDefaultBilling(creditCard);

            entityManager.persist(creditCard);
            entityManager.persist(user);

            User foundUser = entityManager.find(User.class, user.getId());
            BillingDetails defaultBilling = foundUser.getDefaultBilling();
            System.out.println("USER has default Billing Detail: " + defaultBilling);

            CreditCard creditCardReference = entityManager.getReference(CreditCard.class, defaultBilling.getId());
            System.out.println("creditCardReference -> " + creditCardReference);

            System.out.println("defaultBilling == creditCardReference ? -> " + (defaultBilling == creditCardReference));

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
