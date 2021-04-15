package com.uladzimirkalesny.jpa;

import com.uladzimirkalesny.jpa.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class JpaApplication {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Message message = new Message();
            message.setContent("Hello");

            entityManager.persist(message);

            var cb = entityManager.getCriteriaBuilder();
            var query = cb.createQuery(Message.class);
            var fromMessage = query.from(Message.class);
            query.select(fromMessage);
            List<Message> messages = entityManager.createQuery(query).getResultList();

            messages.forEach(System.out::println);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        try {
            transaction.begin();
            List<Message> messages = entityManager.createQuery("select m from Message m").getResultList();
            messages.get(0).setContent("Take me to your leader!");
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
