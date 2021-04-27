package com.uladzimirkalesny.jpa.model.proxiesEx.lazyloading;

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

public class LazyLoadingExampleRunner {

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

            Bid bid1 = new Bid();
            bid1.setAmount(100);

            Bid bid2 = new Bid();
            bid2.setAmount(200);

            Set<Bid> bids = new HashSet<>(Arrays.asList(bid1, bid2));

            Item item = new Item();
            item.setTitle("ItemTitle");
            item.setBids(bids);

            entityManager.persist(item);

            transaction.commit();


        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
        }

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        EntityTransaction transaction2 = entityManager2.getTransaction();

        try {
            transaction2.begin();

            Item foundItem = entityManager2.find(Item.class, 1L);
            // foundItem has PersistentSet for LazyLoading collection
            System.out.println(foundItem);

            transaction2.commit();
        } finally {
            if (transaction2.isActive()) {
                transaction2.rollback();
            }

            entityManager2.close();
            entityManagerFactory.close();

            server.shutdown();
        }
    }

}
