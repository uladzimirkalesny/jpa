package com.uladzimirkalesny.jpa.model.inheritanceEx.mapped_super_class;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class MAPPED_SUPER_CLASS_InheritanceExampleRunner {
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
            creditCard.setExpMonth("03");
            creditCard.setExpYear("2022");

            entityManager.persist(creditCard);

            BankAccount bankAccount = new BankAccount();
            bankAccount.setOwner("Uladzimir");
            bankAccount.setBankName("ASD");
            bankAccount.setAccount("ACC");
            bankAccount.setSwift("SWF");

            entityManager.persist(bankAccount);

            Query billingDetailsQuery = entityManager.createQuery("select bd from BillingDetails bd");
            List<BillingDetails> resultList = (List<BillingDetails>) billingDetailsQuery.getResultList();
            resultList.forEach(System.out::println);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
