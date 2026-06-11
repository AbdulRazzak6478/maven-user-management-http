package com.example.usermanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.usermanagement.config.HibernateUtil;
import com.example.usermanagement.controllers.UserController;
import com.example.usermanagement.models.DBconnection;
import com.example.usermanagement.models.Gender;
import com.example.usermanagement.models.User;
import com.example.usermanagement.repositories.UserRepository;
import com.example.usermanagement.services.UserService;
import com.sun.net.httpserver.HttpServer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        System.out.println("Started learning the Maven with user management http ");

        UserRepository userRepository = new UserRepository();

        UserService userService = new UserService(userRepository);

        UserController userController = new UserController(userService);

        HttpServer server = HttpServer.create(
                new InetSocketAddress(4000), 0);

        server.createContext("/users", userController);

        server.setExecutor(null);

        server.start();

        System.out.println(
                "Server started on port 4000");

        String uri = "jdbc:postgresql://localhost:5432/dispute_test_db";
        String username = "postgres";
        String password = "post30@MARQ46";
        String test = """
                Testing testing
                """;
        try (
                //
                // Connection conn =/ DriverManager.getConnection(uri, username, password);
                Connection conn = DBconnection.getConnection();

                Statement stmt = conn.createStatement();

                ResultSet result = stmt.executeQuery("SELECT * FROM merchants");

        ) {

            while (result.next()) {
                System.out.println("id : " + result.getString("id"));
                System.out.println("customId : " + result.getString("merchant_id"));
                System.out.println("email : " + result.getString("email"));
                System.out.println("name : " + result.getString("name"));
                System.out.println("mobileNumber : " + result.getString("mobile_number"));
                System.out.println("total Disputes : " + result.getInt("total_disputes"));
                System.out.println("total analyst : " + result.getInt("total_analysts"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in jdbc : " + e.getMessage());
        }
        System.out.println();
        System.out.println();
        String sqlQuery = "SELECT * FROM merchants where email=? AND mobile_number=?";
        try (
                Connection conn = DBconnection.getConnection();
                PreparedStatement preStmt = conn.prepareStatement(sqlQuery);) {
            preStmt.setString(1, "yuzi@skygoalnext.com");
            preStmt.setString(2, "+919515426479");
            ResultSet rs = preStmt.executeQuery();
            // boolean hasData = rs.next();

            // System.out.println("Has Data : " + hasData);
            // System.out.println("Result Set : " + rs);

            while (rs.next()) {
                System.out.println("id: " + rs.getString("id"));
                System.out.println("email: " + rs.getString("email"));
                System.out.println("mobile number: " + rs.getString("mobile_number"));
                System.out.println("total Disputes: " + rs.getInt("total_disputes"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        try {
            // User user = new User();
            // user.setId(UUID.randomUUID().toString());
            // user.setFirstName("Tharun");
            // user.setLastName("Ghangipalli");
            // user.setEmail("tharun@skygoalnext.com");
            // user.setMobileNumber("+919515426478");
            // user.setProfession("Backend Developer");
            // user.setEducation("B.Tech");
            // user.setDOB(LocalDate.parse("2002-02-21"));
            // user.setGender(Gender.MALE);
            // user.setSalary(25000);
            // user.setUserActiveStatus(true);
            // user.setUpdatedAt(LocalDateTime.now());
            // user.setCreatedAt(LocalDateTime.now());

            // session.persist(user);

            User getUser = session.get(User.class, 1);
            System.out.println("id:" + getUser.getLastUpdatedAt());

            // session.persist(user); // Insert
            // session.merge(user,id); // Update
            // session.remove(args);; // Update
            // session.get(user.class,id); // Update

            // session.load(user.class,id); // Update
            // session.getReference(user.class,id); // Get // it use lazy loading
            // session.get(user.class,id); // Update --> create 2 object and use eager
            // loading
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in hibernate insert : " + e.getMessage());
        }

        transaction.commit();
        session.close();

        System.out.println("User Created");

        // ode:
        // Configuration config = null;
        // SessionFactory sessionFactory = null;
        // Session session = null;
        // Transaction transaction = null;
        // boolean flag = false;
        // config = new Configuration();
        // // Setting Hibernate properties directly using setProperty method
        // config.setProperty("hibernate.connection.driver_class",
        // "com.mysql.cj.jdbc.Driver");
        // config.setProperty("hibernate.connection.url",
        // "jdbc:mysql://localhost:3306/telusko_db");
        // config.setProperty("hibernate.connection.password", "root123");
        // config.setProperty("hibernate.connection.username", "root");
        // config.setProperty("hibernate.dialect",
        // "org.hibernate.dialect.MySQLDialect");
        // config.setProperty("hibernate.hbm2ddl.auto", "update");
        // config.setProperty("hibernate.show_sql", "true");
        // config.setProperty("hibernate.format_sql", "true");
        // // Adding annotated class
        // config.addAnnotatedClass(Student.class);
        // // Building session factory
        // sessionFactory = config.buildSessionFactory();

        // @Lob
        // FileInputStream fis = new FileInputStream("D:\\IO\\Java.JPG");
        // image = new byte[fis.available()];
        // fis.read(image);

        // File Reader
        // File file = new File("D:\\IO\\PersonalInfo.txt");
        // reader = new FileReader(file);
        // textFile = new char[(int) file.length()];
        // reader.read(textFile);

        // FileOutputStream fos = new FileOutputStream("Java.JPG");
        // fos.write(studentInfo.getImage());

        // FileWriter writer = new FileWriter("PersonalInfo.txt");
        // writer.write(studentInfo.getTextFile());

        // HQL
        // String hql = "FROM Student WHERE name = :name";
        // Query query = session.createQuery(hql, Student.class);
        // query.setParameter("name", "Navin");
        // List<Student> result = query.getResultList();
        // System.out.println(result.size())

    }
}
