package com.uladzimirkalesny.jpa.model.queries.criteria;

import org.h2.tools.Server;
import org.hibernate.Metamodel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class CriteriaExampleRunner {

    private static Server server;

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

            Item item = new Item();
            item.setTitle("Title");

            entityManager.persist(item);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        try {
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Item> criteria = criteriaBuilder.createQuery(Item.class);
            criteria.select(criteria.from(Item.class))
                    .where(criteriaBuilder.equal(criteria.from(Item.class).get(Item_.id), 1L)); // Item_ -> Metamodel class

            TypedQuery<Item> query = entityManager.createQuery(criteria);
            Item result = query.getSingleResult();

            System.out.println(result + " = result from ITEMS table");

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
