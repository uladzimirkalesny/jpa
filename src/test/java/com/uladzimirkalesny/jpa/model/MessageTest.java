package com.uladzimirkalesny.jpa.model;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

class MessageTest {
    @Test
    void should_be_added_first_message_in_db() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-examples");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Message message = new Message();
            message.setContent("Hello");

            transaction.commit();
        } finally {
            if(transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }
    }
}