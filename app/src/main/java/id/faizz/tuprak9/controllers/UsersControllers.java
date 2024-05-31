package id.faizz.tuprak9.controllers;

import java.sql.Blob;
import java.sql.ResultSet;

import id.faizz.tuprak9.config.DbConfig;
import id.faizz.tuprak9.models.RegistrationResult;
import id.faizz.tuprak9.models.Users;
import javafx.scene.control.TableView.ResizeFeatures;

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
                    String namaPenerima = userResult.getString("namaPenerima");
                    String nomorHpPenerima = userResult.getString("nomorHpPenerima");
                    String foto = userResult.getString("foto");
                    String role = userResult.getString("role");
                    Users user = new Users(id, username, password, nama, nomorHp, tempatLahir, alamat, namaPenerima, nomorHpPenerima, foto, role);
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO 7: Create method updateUser(User user)
    public static boolean updateUser(int id, String nama, String nomorHp, String tempatLahir, String alamat, String namaPenerima, String nomorHpPenerima, String foto, String role) {
        String query = "UPDATE users SET nama=?, nomorHp=?, tempatLahir=?, alamat=?, namaPenerima=?, nomorHpPenerima=?, role=?, foto=? WHERE id=?";
    
        try {
            getConnection(); // Ensure this initializes 'connection'
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, nomorHp);
            preparedStatement.setString(3, tempatLahir);
            preparedStatement.setString(4, alamat);
            preparedStatement.setString(5, namaPenerima);
            preparedStatement.setString(6, nomorHpPenerima);
            preparedStatement.setString(7, role);
            preparedStatement.setString(8, foto);
            preparedStatement.setInt(9, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUserFoto(int id, String path){
        String query = "UPDATE users SET foto=? WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, path);
            preparedStatement.setInt(2, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean updateUserPassword(int id, String passwordBaru){
        String query = "UPDATE users SET password=? WHERE id=?";

        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, passwordBaru);
            preparedStatement.setInt(2, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public static boolean register(String username, String password, String role) {
        query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static RegistrationResult register(String username, String password) {
        String queryCheck = "SELECT COUNT(*) FROM users WHERE username=?";
        String queryInsert = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
    
        try {
            // Open connection
            getConnection();
            connection.setAutoCommit(false); // Begin transaction
    
            // Check if username already exists
            preparedStatement = connection.prepareStatement(queryCheck);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return RegistrationResult.USERNAME_TAKEN;
            }
    
            // Insert new user
            preparedStatement = connection.prepareStatement(queryInsert);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, "regular");
            preparedStatement.executeUpdate();
    
            // Commit transaction
            connection.commit();
            return RegistrationResult.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback transaction in case of error
                }
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close(); // Close connection
                }
            } catch (Exception closeException) {
                closeException.printStackTrace();
            }
        }
        return RegistrationResult.FAILURE;
    }
    

}