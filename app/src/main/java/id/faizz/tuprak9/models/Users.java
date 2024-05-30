package id.faizz.tuprak9.models;

public class Users {

    private int id;
    private String username;
    private String password;
    private String nama;
    private String nomorHp;
    private String tempatLahir;
    private String alamat;
    private String role;

    public Users(int id, String role){
        this.id = id;
        this.role = role;
    }

    public Users(int id){
        this.id = id;
    }

    public Users(int id, String username, String password, String nama, String nomorHp, String tempatLahir,
            String alamat, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.nomorHp = nomorHp;
        this.tempatLahir = tempatLahir;
        this.alamat = alamat;
        this.role = role;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNomorHp() {
        return nomorHp;
    }
    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }
    public String getTempatLahir() {
        return tempatLahir;
    }
    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
