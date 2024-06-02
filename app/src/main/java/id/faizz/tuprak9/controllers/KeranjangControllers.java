package id.faizz.tuprak9.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import id.faizz.tuprak9.config.DbConfig;
import id.faizz.tuprak9.models.Keranjang;
import id.faizz.tuprak9.models.Produk;

public class KeranjangControllers extends DbConfig {

    public static boolean tambahKeranjang(int id, String nama, String foto, int harga, int userId) {
        query = "INSERT INTO keranjang (nama, foto, harga, userId) VALUES (?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, foto);
            preparedStatement.setInt(3, harga);
            preparedStatement.setInt(4, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeKeranjangByIdAndUserId(int id, int userId) {
        query = "DELETE FROM keranjang WHERE id=? AND userId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Keranjang> getProdukbyId(int userId){
        List<Keranjang> produks = new ArrayList<>();
        query = "SELECT * FROM keranjang WHERE userId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String foto = resultSet.getString("foto");
                int harga = resultSet.getInt("harga");
                Keranjang keranjang = new Keranjang(id, nama, foto, harga, userId);
                produks.add(keranjang);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return produks;
    }

}
