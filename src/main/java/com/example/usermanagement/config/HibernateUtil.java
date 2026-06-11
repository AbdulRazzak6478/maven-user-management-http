package com.example.usermanagement.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        // try{

        // }catch(Exception e)
        // {
        //     e.printStackTrace();
        //     System.out.println("Session Factory creation Failed : "+e.getMessage());
        // }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
