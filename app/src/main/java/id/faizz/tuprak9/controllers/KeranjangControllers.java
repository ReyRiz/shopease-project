package id.faizz.tuprak9.controllers;

import java.sql.ResultSet;

import id.faizz.tuprak9.config.DbConfig;
import id.faizz.tuprak9.models.RegistrationResult;
import id.faizz.tuprak9.models.Users;
import java.util.ArrayList;
import java.util.List;
import id.faizz.tuprak9.models.*;;

public class KeranjangControllers extends DbConfig{
    
    public static boolean updateKeranjangUser(int id, String nama, String foto, int harga, int userId){
        String query = "UPDATE keranjang SET nama=?, foto=?, harga=?, userId=? where id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, foto);
            preparedStatement.setInt(3, harga);
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Produk> getAllProdukById(int userId){
        List<Produk> produks =  new ArrayList<>();
        query = "SELECT * FROM keranjang WHERE userId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String foto = resultSet.getString("foto");
                int harga = resultSet.getInt("harga");
                Produk produk = new Produk(id, nama, foto, nama, harga, foto, userId); // Belum Selesai
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return produks;// Belum selesai
    }
}
