package eu.ubitech.samples.jpa.tutorial;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Created by lumi on 21/10/2015.
 */
public class Main {

    public static void main(String args[]) {

        System.out.println("DataNucleus examples using JPA");
        System.out.println("=============================");
        
        Scanner keyboard = new Scanner(System.in);

        
        // Create an EntityManagerFactory for this "persistence-unit"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tutorial");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        Inventory inv = new Inventory("My Inventory");

        // Persistence of a Product and a Book.
        performPersistenceOfProducts(inv, em, emf, tx, keyboard);
        //
        performARetrieveOfTheInventoryAndDetach(em, emf, tx, keyboard);

        // Perform some query operations
        // Comparison operators
        performQeryUsingComparisonOperators(em, emf, tx, keyboard);
        //
        performARetrieveOfTheInventoryAndDetach(em, emf, tx, keyboard);

        // Arithmetic operators
        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Update Query for a Book's price");
            Query q = em.createQuery("UPDATE Book b SET b.price = 300 WHERE b.name = :name").setParameter("name", "Salamander");
            int numRowsUpdated = q.executeUpdate();
            System.out.println(">  Update was executed: " + numRowsUpdated);

            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Update Query for NULL values in Books");
            Query q = em.createQuery("UPDATE Book b SET b.author = 'Mpamias' WHERE b.name IS NULL");
            int numRowsUpdated = q.executeUpdate();
            System.out.println(">  Update was executed: " + numRowsUpdated);

            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();
        //
        performARetrieveOfTheInventoryAndDetach(em, emf, tx, keyboard);

        // Logical operators
        //
        // Clean out the database
        //performCleanOutOfTheDatabase(inv, em, emf, tx, keyboard);
        System.out.println("");
        System.out.println("End of Tutorial");

    }

    public static void performPersistenceOfProducts(Inventory inv, EntityManager em, EntityManagerFactory emf, EntityTransaction tx, Scanner keyboard) {
        try {
            tx.begin();

            Product product = new Product("Sony Discman", "A standard discman from Sony", 200);
            inv.getProducts().add(product);
            Book book = new Book("Lord of the Rings", "The classic story", 49, "JRR Tolkien", "12345678", "MyBooks Factory");
            inv.getProducts().add(book);
            book = new Book("Salamander", "The classic story", 100, "Tsantilis", "1234567", "Saturn");
            inv.getProducts().add(book);
            book = new Book("Salami", "The classic story", 150, "Tsantilis", "123456", "Saturn");
            inv.getProducts().add(book);
            book = new Book("Salvador", "The classic story", 50, "Gouvas", "12345", "Saturn");
            inv.getProducts().add(book);
            book = new Book("Spiderman", "The amazing story", 60, "Tsalampamouni", "87654321", "Saturn");
            inv.getProducts().add(book);
            book = new Book("Superman", "Fighting in Crypton", 79, null, null, null);
            inv.getProducts().add(book);
            book = new Book("Captain America", "Fighting in Germany", 120, null, null, null);
            inv.getProducts().add(book);
            book = new Book("Ant Man", "Fighting in Crypton", 46, null, null, null);
            inv.getProducts().add(book);

            em.persist(inv);

            tx.commit();
            System.out.println("Products and Books have been persisted");

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

    }

    public static void performARetrieveOfTheInventoryAndDetach(EntityManager em, EntityManagerFactory emf, EntityTransaction tx, Scanner keyboard) {
        // Perform a retrieve of the Inventory and detach it (by closing the EM)
        em = emf.createEntityManager();

        tx = em.getTransaction();
        Inventory inv = null;
        try {
            tx.begin();
            System.out.println("Executing find() on Inventory");
            inv = em.find(Inventory.class, new Long(1));
            System.out.println("Retrieved Inventory as " + inv);

            // Access Products field so it is loaded before detach
            // Note that you could alternately make it EAGER fetch, or use DN fetch groups
            inv.getProducts();

            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close(); // This will detach all current managed objects

        }
        for (Product prod : inv.getProducts()) {
            System.out.println(">> After Detach : Inventory has a product=" + prod);

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

    }

    public static void performQeryUsingComparisonOperators(EntityManager em, EntityManagerFactory emf, EntityTransaction tx, Scanner keyboard) {
        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Query for Books with Name equals Salamander");
            Query q = em.createQuery("SELECT b FROM Book b WHERE b.name = :name ORDER BY b.price").setParameter("name", "Salamander");
            List results = q.getResultList();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                System.out.println(">  " + obj);

            }
            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Query for Books with products with name LIKE -sa-");
            String param = "sa";
            Query q = em.createQuery("SELECT b FROM Book b WHERE b.author LIKE :author ORDER BY b.price").setParameter("author", "%" + param + "%");
            List results = q.getResultList();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                System.out.println(">  " + obj);

            }
            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Query for Products with price < 150");
            Query q = em.createQuery("SELECT p FROM Product p WHERE p.price < 150.00 ORDER BY p.price");
            List results = q.getResultList();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                System.out.println(">  " + obj);

            }
            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Query for Products with price >= 150");
            Query q = em.createQuery("SELECT p FROM Product p WHERE p.price >= 150.00 ORDER BY p.price");
            List results = q.getResultList();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                System.out.println(">  " + obj);

            }
            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Query for Books with Author not Tsantilis");
            Query q = em.createQuery("SELECT b FROM Book b WHERE b.author <> 'Tsantilis' ORDER BY b.price");
            List results = q.getResultList();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                System.out.println(">  " + obj);

            }
            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Query for Books with Author not NULL");
            Query q = em.createQuery("SELECT b FROM Book b WHERE b.author IS NOT NULL ORDER BY b.price");
            List results = q.getResultList();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                System.out.println(">  " + obj);

            }
            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Query for Books with Author being NULL");
            Query q = em.createQuery("SELECT b FROM Book b WHERE b.author IS NULL ORDER BY b.price");
            List results = q.getResultList();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                System.out.println(">  " + obj);

            }
            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Query for Products with price being between 60 and 120");
            Query q = em.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN 60 AND 120 ORDER BY p.price");
            List results = q.getResultList();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                System.out.println(">  " + obj);

            }
            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Executing Query for Products with price not being between 60 and 120");
            Query q = em.createQuery("SELECT p FROM Product p WHERE p.price NOT BETWEEN 60 AND 120 ORDER BY p.price");
            List results = q.getResultList();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                System.out.println(">  " + obj);

            }
            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }
        System.out.println("");
        System.out.println("press enter to continue");
        keyboard.nextLine();

    }

    public static void performCleanOutOfTheDatabase(Inventory inv, EntityManager em, EntityManagerFactory emf, EntityTransaction tx, Scanner keyboard) {
        em = emf.createEntityManager();

        tx = em.getTransaction();
        try {
            tx.begin();

            System.out.println("Deleting all products from persistence");
            inv = (Inventory) em.find(Inventory.class, "My Inventory");

            System.out.println("Clearing out Inventory");
            inv.getProducts().clear();
            em.flush();

            System.out.println("Deleting Inventory");
            em.remove(inv);

            System.out.println("Deleting all products from persistence");
            Query q = em.createQuery("SELECT p FROM Product p");
            List<Product> products = q.getResultList();
            int numDeleted = 0;
            for (Product prod : products) {
                em.remove(prod);
                numDeleted++;

            }
            System.out.println("Deleted " + numDeleted + " products");

            tx.commit();

        } catch (Exception e) {
            System.out.println("Bulk delete encountered an error " + e.getMessage());

        } finally {
            if (tx.isActive()) {
                tx.rollback();

            }
            em.close();

        }

    }

    public static void performQeryUsingArithmeticOperators(EntityManager em, EntityManagerFactory emf, EntityTransaction tx, Scanner keyboard) {

    }

}
