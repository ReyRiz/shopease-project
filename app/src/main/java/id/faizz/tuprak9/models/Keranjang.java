package id.faizz.tuprak9.models;

public class Keranjang {
    private int id;
    private String nama;
    private String foto;
    

    private int harga;
    private int userId;
    
    public Keranjang(int id, String nama, String foto, int harga, int userId) {
        this.id = id;
        this.nama = nama;
        this.foto = foto;
        this.harga = harga;
        this.userId = userId;
    }


    public String getFoto() {
        return foto;
    }


    public void setFoto(String foto) {
        this.foto = foto;
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
