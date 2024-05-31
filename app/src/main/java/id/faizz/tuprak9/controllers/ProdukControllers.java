package id.faizz.tuprak9.controllers;

import java.sql.ResultSet;

import com.google.common.util.concurrent.ExecutionError;
import java.util.ArrayList;
import java.util.List;
import id.faizz.tuprak9.config.DbConfig;
import id.faizz.tuprak9.models.RegistrationResult;
import id.faizz.tuprak9.models.Produk;

public class ProdukControllers extends DbConfig {
    
    public static List<Produk> getAllProduk(){
        List<Produk> produks = new ArrayList<>();
        query = "SELECT * FROM products";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String namaa = resultSet.getString("nama");
                String fotoo = resultSet.getString("foto");
                String deskripsii = resultSet.getString("deskripsi");
                int harga = resultSet.getInt("harga");
                int userId = resultSet.getInt("userId");
                Produk produk = new Produk(id, namaa, fotoo, deskripsii, harga, deskripsii, userId);
                produks.add(produk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produks;
    }

    public static List<Produk> getAllProduk(int userId){
        List<Produk> produks = new ArrayList<>();
        query = "SELECT * FROM products WHERE userId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String namaa = resultSet.getString("nama");
                String fotoo = resultSet.getString("foto");
                String deskripsii = resultSet.getString("deskripsi");
                int harga = resultSet.getInt("harga");
                Produk produk = new Produk(id, namaa, fotoo, deskripsii, harga, deskripsii, userId);
                produks.add(produk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produks;
    }
    
    public static Produk getProdukById(int id){
        query = "SELECT * FROM products WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            try (ResultSet produkResult = preparedStatement.executeQuery()) {
                while (produkResult.next()){
                    String nama = produkResult.getString("nama");
                    String foto = produkResult.getString("foto");
                    String deskripsi = produkResult.getString("deskripsi");
                    int harga = produkResult.getInt("harga");
                    String kategori = produkResult.getString("kategori");
                    int userId = produkResult.getInt("userId");
                    Produk produk = new Produk(id, nama, foto, deskripsi, harga, kategori, userId);
                    return produk;
                }
            } 
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
   }

   public static boolean tambahProduk(int id, String nama, String foto, String deskripsi, int harga, int userId){
    query = "INSERT INTO products (nama, foto, deskripsi, harga, userId) VALUES (?, ?, ?, ?, ?)";
    try {
        getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nama);
        preparedStatement.setString(2, foto);
        preparedStatement.setString(3, deskripsi);
        preparedStatement.setInt(4, harga);
        preparedStatement.setInt(5, userId);
        preparedStatement.executeUpdate();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
   }
}
