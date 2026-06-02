package com.example.usermanagement.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBconnection {

    public static final String URI = "jdbc:postgresql://localhost:5432/dispute_test_db";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "post30@MARQ46";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URI, USERNAME, PASSWORD);
    }

    public void updateUser(
            int id,
            String name) {

        String sql = """
                UPDATE users
                SET name = ?
                WHERE id = ?
                """;

        try (

                Connection conn = DBconnection.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

        ) {

            ps.setString(1, name);

            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            System.out.println(
                    "Updated Rows : " + rows);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
