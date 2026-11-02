/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codenova.taskOne;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Nicole
 */
public class ContactProcessor {
    private Connection connection;

    public void connectDatabase() throws SQLException {
        String url = "jdbc:derby://localhost:1527/contactsDB";
        String user = "app";
        String password = "app";
        connection = DriverManager.getConnection(url, user, password);
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ignored) {}
    }

    public ArrayList<ContactDetails> getAllContacts() throws SQLException {
        ArrayList<ContactDetails> contactList = new ArrayList<>();
        String sql = "SELECT * FROM APP.CONTACTS";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                ContactDetails details = new ContactDetails(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getBoolean(6),
                    resultSet.getDate(7).toLocalDate()
                );
                contactList.add(details);
            }
        }
        return contactList;
    }

    public int addContact(ContactDetails details) throws SQLException {
        String addSql = "INSERT INTO CONTACTS (CONTACT_NAME, EMAIL_ADDRESS, PHONE_NUMBER, CATEGORY, ACTIVE, DATE_ADDED) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(addSql)) {
            preparedStatement.setString(1, details.getContactName());
            preparedStatement.setString(2, details.getEmailAddress());
            preparedStatement.setString(3, details.getPhoneNumber());
            preparedStatement.setString(4, details.getCategory());
            preparedStatement.setBoolean(5, details.isActive());
            preparedStatement.setDate(6, java.sql.Date.valueOf(details.getDateAdded()));
            
            return preparedStatement.executeUpdate();
        }
    }

    public int updateContact(int userIdNum, ContactDetails details) throws SQLException {
        String sql = "UPDATE APP.CONTACTS SET CONTACT_NAME = ?, EMAIL_ADDRESS = ?, PHONE_NUMBER = ?, CATEGORY = ?, ACTIVE = ?, DATE_ADDED = ? WHERE CONTACT_ID = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, details.getContactName());
            preparedStatement.setString(2, details.getEmailAddress());
            preparedStatement.setString(3, details.getPhoneNumber());
            preparedStatement.setString(4, details.getCategory());
            preparedStatement.setBoolean(5, details.isActive());
            preparedStatement.setDate(6, java.sql.Date.valueOf(details.getDateAdded()));
            preparedStatement.setInt(7, userIdNum);
            
            return preparedStatement.executeUpdate();
        }
    }

    public int deleteContact(int id) throws SQLException {
        String sql = "DELETE FROM CONTACTS WHERE CONTACT_ID = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
    }
}