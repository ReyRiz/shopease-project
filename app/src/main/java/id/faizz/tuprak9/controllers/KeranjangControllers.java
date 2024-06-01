package id.faizz.tuprak9.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import id.faizz.tuprak9.config.DbConfig;
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


}
