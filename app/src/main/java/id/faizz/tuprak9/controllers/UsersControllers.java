package id.faizz.tuprak9.controllers;

import java.sql.ResultSet;

import id.faizz.tuprak9.config.DbConfig;
import id.faizz.tuprak9.models.Users;

public class UsersControllers extends DbConfig {

    public static Users login(String username, String password) {
        getConnection();
        query = "SELECT * FROM users WHERE username=? AND password =?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet userResult = preparedStatement.executeQuery()) {
                if (userResult.next()) {
                    int id = userResult.getInt("id");
                    String role = userResult.getString("role");
                    Users users = new Users(id, role);
                    return users;
                }
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Users getUserById(int id) {
        query = "SELECT * FROM users WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            try (ResultSet userResult = preparedStatement.executeQuery()) {
                while (userResult.next()) {
                    String username = userResult.getString("username");
                    String password = userResult.getString("password");
                    String nama = userResult.getString("nama");
                    String nomorHp = userResult.getString("nomorHp");
                    String tempatLahir = userResult.getString("tempatLahir");
                    String alamat = userResult.getString("alamat");
                    String role = userResult.getString("role");
                    Users user = new Users(id, username, password, nama, nomorHp, tempatLahir, alamat, role);
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO 7: Create method updateUser(User user)
    public static boolean updateUser(int id, String nama, String nomorHp, String tempatLahir, String alamat, String role) {
        query = "UPDATE users SET nama=?, nomorHp=?, tempatLahir=?, alamat=?, role=? WHERE id=?";

        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, nomorHp);
            preparedStatement.setString(3, tempatLahir);
            preparedStatement.setString(4, alamat);
            preparedStatement.setString(5, role);
            preparedStatement.setInt(6, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean register(String username, String password) {
        query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}