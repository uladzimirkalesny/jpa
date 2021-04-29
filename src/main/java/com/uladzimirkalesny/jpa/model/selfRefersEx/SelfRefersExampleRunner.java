package com.uladzimirkalesny.jpa.model.selfRefersEx;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.PERSISTENCE_UNIT_NAME;

public class SelfRefersExampleRunner {

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

            Category parentCategory = new Category();
            parentCategory.setTitle("Parent");

            Category childCategory1 = new Category();
            childCategory1.setTitle("Child 1");
            childCategory1.setParentCategory(parentCategory);

            Category childCategory2 = new Category();
            childCategory2.setTitle("Child 2");
            childCategory2.setParentCategory(parentCategory);

            Category childChildCategory = new Category();
            childChildCategory.setTitle("Child Child 1");
            childChildCategory.setParentCategory(childCategory1);

            entityManager.persist(parentCategory);
            entityManager.persist(childCategory1);
            entityManager.persist(childCategory2);
            entityManager.persist(childChildCategory);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        try {
            transaction.begin();

            TypedQuery<Category> parentQuery =
                    entityManager.createQuery("select c from Category c where c.parentCategory is null", Category.class);

            Category foundParentCategory = parentQuery.getSingleResult();
            System.out.println("Parent category = " + foundParentCategory);


            TypedQuery<Category> childQuery =
                    entityManager.createQuery("select c from Category c, Category r where r.parentCategory is null and c.parentCategory = r", Category.class);
            List<Category> resultList = childQuery.getResultList();
            resultList.forEach(System.out::println);

            Query query = entityManager.createNamedQuery("findAllCategoriesWithLevel");
            List<Object[]> result = query.getResultList();
            result.stream()
                    .map(tuple -> {
                             CategoryResult categoryResult = CategoryResult.builder()
                                     .category((Category) tuple[0])
                                     .path((String) tuple[1])
                                     .level((Long) tuple[2])
                                     .build();
                             return categoryResult;
                         }
                    ).forEach(System.out::println);

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
