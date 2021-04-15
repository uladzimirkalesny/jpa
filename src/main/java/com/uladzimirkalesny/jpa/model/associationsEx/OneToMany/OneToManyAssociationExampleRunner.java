package com.uladzimirkalesny.jpa.model.associationsEx.OneToMany;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class OneToManyAssociationExampleRunner {

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

            Item item = new Item();
            item.setName("Test name");
            item.addBid(bid1);
            item.addBid(bid2);

            User user = new User();
            user.setName("Uladzimir");
            user.addBid(bid1);
            user.addBid(bid2);

            entityManager.persist(user);
            entityManager.persist(item);
            transaction.commit();

            transaction.begin();

            var query = entityManager.createQuery("select b from Bid b where b.item = :itemParameter");
            query.setParameter("itemParameter", item);
            List<Bid> resultList = (List<Bid>)query.getResultList();

            resultList.forEach(System.out::println);
            resultList.stream().map(Bid::getItem).collect(Collectors.toList()).forEach(System.out::println);

            transaction.commit();


            transaction.begin();

            User foundUser = entityManager.find(User.class, 1L);

            Item foundItem = entityManager.find(Item.class, 2L);
            foundItem.getBids().remove(foundItem.getBids().iterator().next()); // 1 bid was removed for item
            System.out.println("foundUser.getBids().size() = " + foundUser.getBids().size()); // still 2 bids here for user(bidder)
            entityManager.remove(foundItem);

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
