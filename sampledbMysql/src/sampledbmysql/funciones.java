/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Johan Gil
 */
public class funciones {

    public void select() {
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            String sql = "SELECT * FROM users";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()) {
                String name = result.getString(2);
                String pass = result.getString(3);
                String fullname = result.getString("fullname");
                String email = result.getString("email");

                String output = "User #%d: %s - %s - %s - %s";
                System.out.println(String.format(output, ++count, name, pass, fullname, email));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(String pass, String full, String correo, int id) { //modificar username
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            String sql = "UPDATE users SET password=?, fullname=?, email=? WHERE user_id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, pass);
            statement.setString(2, full);
            statement.setString(3, correo);
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insert(int id, String user, String pass, String full, String correo) {
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            String sql = "INSERT INTO users (user_id,username, password, fullname, email) VALUES (? ,?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, user);
            statement.setString(3, pass);
            statement.setString(4, full);
            statement.setString(5, correo);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Datos invalidos ");
        }
    }

    public void delete(int id) {
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            String sql = "DELETE FROM users WHERE user_id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }

        } catch (SQLException ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Datos invalidos ");
        }
    }

}
