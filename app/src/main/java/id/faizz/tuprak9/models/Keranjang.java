package id.faizz.tuprak9.models;

public class Keranjang {
    private int id;
    private String nama;
    private int harga;
    private int userId;
    
    public Keranjang(int id, String nama, int harga, int userId) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.userId = userId;
    }


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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    

}
