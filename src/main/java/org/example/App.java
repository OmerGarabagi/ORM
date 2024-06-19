package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {

    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Owner.class);
        configuration.addAnnotatedClass(Picture.class);
        configuration.addAnnotatedClass(RepairShop.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void generateOwnersCarsAndShops() throws Exception {
        Random random = new Random();

        List<RepairShop> repairShops = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            RepairShop shop = new RepairShop("Hanamal" + random.nextInt(60), "+972528180190" + random.nextInt(19));
            session.save(shop);
            repairShops.add(shop);
            session.flush();
        }

        for (int i = 0; i < 8; i++) {
            Owner owner = new Owner("FirstName" + i, "LastName" + i, "email" + i + "@yahoo.com", "shnitzel" + i);
            session.save(owner);
            session.flush();

            for (int j = 0; j < 2; j++) {
                Car car = new Car("MOO-" + random.nextInt(), 100000 + random.nextInt(1000) , 2000 + random.nextInt(19), owner);
                session.save(car);
                session.flush();

                Picture picture = new Picture("http://example.com/picture" + random.nextInt() + ".jpg", car);
                car.setPicture(picture);
                session.save(picture);
                session.flush();

                // Associate car with random repair shops
                car.setRepairShops(repairShops);
                for (RepairShop shop : repairShops) {
                    if (shop.getCars() == null) {
                        shop.setCars(new ArrayList<>());
                    }
                    shop.getCars().add(car);
                }
                session.save(car);
                session.flush();
            }
        }
    }

    private static List<Car> getAllCars() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);
        query.from(Car.class);
        List<Car> data = session.createQuery(query).getResultList();
        return data;
    }

    private static void printAllCars() throws Exception {
        List<Car> cars = getAllCars();
        System.out.print("Printing all cars:\n");
        for (Car car : cars) {
            System.out.print("Id: ");
            System.out.print(car.getId());
            System.out.print(", License plate: ");
            System.out.print(car.getLicensePlate());
            System.out.print(", Price: ");
            System.out.print(car.getPrice());
            System.out.print(", Year: ");
            System.out.print(car.getYear());
            System.out.print(", Owner: ");
            System.out.print(car.getOwner().getFirstName() + " " + car.getOwner().getLastName());
            //System.out.print(", Picture URL: ");
            //System.out.print(car.getPicture().getUrl());
            System.out.print(", Repair Shops: ");
            for (RepairShop shop : car.getRepairShops()) {
                System.out.print(shop.getAddress() + " ");
            }
            System.out.print('\n');
        }
    }

    private static void printAllShops() throws Exception {
        List<RepairShop> shops = getAllShops();
        System.out.print("Printing all repair shops:\n");
        for (RepairShop shop : shops) {
            System.out.print("Id: ");
            System.out.print(shop.getId());
            System.out.print(", Address: ");
            System.out.print(shop.getAddress());
            System.out.print(", Phone number: ");
            System.out.print(shop.getPhoneNumber());
            System.out.print(", Cars: ");
            for (Car car : shop.getCars()) {
                System.out.print(car.getLicensePlate() + " ");
            }
            System.out.print('\n');
        }
    }

    private static List<RepairShop> getAllShops() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RepairShop> query = builder.createQuery(RepairShop.class);
        query.from(RepairShop.class);
        List<RepairShop> data = session.createQuery(query).getResultList();
        return data;
    }

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            generateOwnersCarsAndShops();
            printAllCars();
            printAllShops();

            session.getTransaction().commit();

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occurred, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }
}
