package com.itfactory.PhoneBookWeb.repos;

import com.itfactory.PhoneBookWeb.model.Contact;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository //sinonim pt @Component - creeaza un obiect automat din aceasta clasa

public class ContactRepository {

    //ne conectam la baza de date

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/phone_book";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "Ioana1997@";

    //creem o metoda care returneaza lista de contacte

    public List<Contact> getAllContacts() {
        List<Contact> listaContacte = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");
            while (resultSet.next()) {
                Contact newContact = new Contact();
                newContact.setId(Integer.parseInt(resultSet.getString("id")));
                newContact.setName(resultSet.getString("name"));
                newContact.setSurname(resultSet.getString("surname"));
                newContact.setPhoneNumber(resultSet.getString("phoneNumber"));
                newContact.setEmail(resultSet.getString("email"));

                listaContacte.add(newContact);
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

        return listaContacte;

    }

    public void addContact(Contact newContact) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS))
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO contacts(name, surname, phoneNumber, email) VALUES(?,?,?,?)");
            statement.setString(1, newContact.getName());
            statement.setString(2, newContact.getSurname());
            statement.setString(3, newContact.getPhoneNumber());
            statement.setString(4, newContact.getEmail());

            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public void deleteContact(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS))
        {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM contacts WHERE ID=?");
            statement.setInt(1, id);

            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
    }







