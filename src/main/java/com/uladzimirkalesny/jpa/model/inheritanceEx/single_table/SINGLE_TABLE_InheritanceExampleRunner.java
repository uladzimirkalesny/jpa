package com.uladzimirkalesny.jpa.model.inheritanceEx.single_table;

import com.uladzimirkalesny.jpa.constant.JpaConstants;
import com.uladzimirkalesny.jpa.model.inheritanceEx.single_table.BankAccount;
import com.uladzimirkalesny.jpa.model.inheritanceEx.single_table.BillingDetails;
import com.uladzimirkalesny.jpa.model.inheritanceEx.single_table.CreditCard;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class SINGLE_TABLE_InheritanceExampleRunner {
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

            Query billingDetailsQuery = entityManager.createQuery("from BillingDetails");
            List<BillingDetails> resultList = (List<BillingDetails>) billingDetailsQuery.getResultList();
            resultList.forEach(System.out::println);

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
