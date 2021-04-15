package com.uladzimirkalesny.jpa.model.associationsEx.ManyToOne;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class ManyToOneAssociationExampleRunner {

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


            Item item = new Item();
            item.setName("TEST ITEM");

            Bid bid = new Bid();
            bid.setAmount(100);
            bid.setItem(item);
            entityManager.persist(bid);

            Bid bid2 = new Bid();
            bid2.setAmount(200);
            bid2.setItem(item);

            entityManager.persist(bid2);

            transaction.commit();


            transaction.begin();

            Query query = entityManager.createQuery("select b from Bid b where b.item = :itemParameter");
            query.setParameter("itemParameter", item);
            List<Bid> resultList = (List<Bid>) query.getResultList();
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
