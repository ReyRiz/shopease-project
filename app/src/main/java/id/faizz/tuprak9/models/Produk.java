package id.faizz.tuprak9.models;

import java.time.LocalDate;

abstract class template {
    protected LocalDate createdDate;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}

public class Produk extends template {
    private int id;
    private String nama;
    private String foto;
    private String deskripsi;
    private int harga;
    private String kategori;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Produk(int id, String nama, String foto, String deskripsi, int harga, String kategori, int userId) {
        this.id = id;
        this.nama = nama;
        this.foto = foto;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.kategori = kategori;
        this.userId = userId;
    }

}
