package id.faizz.tuprak9;

import java.io.File;

import id.faizz.tuprak9.controllers.ProdukControllers;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Users;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TambahProduk {
    private Stage stage;
    private String foto;

    TambahProduk(Stage stage){
        this.stage = stage;
    }
    
    public void show(int userId){
        Users users = UsersControllers.getUserById(userId);

        StackPane root = new StackPane();
        root.getStyleClass().add("homePage"); 

        VBox awal = new VBox();

        HBox navigationBar = new HBox(25);
        navigationBar.setPadding(new Insets(32));
        navigationBar.setPrefSize(1382, 110);
        navigationBar.getStyleClass().add("navigationBar");
        navigationBar.setAlignment(Pos.CENTER);
        ImageView logo = new ImageView(new Image("styles/Seller.png"));

        logo.setOnMouseClicked(e -> {
            if (users.getRole().equals("seller")){
                SellerPage sellerPage = new SellerPage(stage);
                sellerPage.show(userId);
            } else {
                HomePage home = new HomePage(stage);
                home.show(userId);
            }
        });

        HBox username = new HBox(10);
        username.setAlignment(Pos.CENTER);
        ImageView photoProfile;
        try {
            photoProfile = new ImageView(new Image("file:" + users.getfoto()));
        } catch (Exception e) {
            photoProfile = new ImageView(new Image("styles/contohPP.png"));  
        }
        photoProfile.setFitHeight(45);
        photoProfile.setFitWidth(45);
        photoProfile.getStyleClass().add("photoProfile");
        Label userLabel = new Label(users.getUsername());
        userLabel.getStyleClass().add("usernameText");

        // saat username di klik
        username.setOnMouseClicked(e -> {
            ProfilePage menuProfil = new ProfilePage(stage);
            menuProfil.show(userId);
        });

        // Layout kedua
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);  // Set alignment to center
        layout.setPadding(new Insets(48, 277, 48 ,277));
        
        VBox tambahWindow = new VBox(20);
        tambahWindow.setPadding(new Insets(40));
        tambahWindow.setPrefSize(862, 489);
        tambahWindow.getStyleClass().add("tambahWindow");

        VBox tambahx = new VBox();
        tambahx.setAlignment(Pos.CENTER);
        Label tambahLabel = new Label("Edit Produk");
        tambahLabel.setStyle("-fx-text-fill: #000; -fx-font-size: 25; -fx-font-weight: bold; -fx-font-family: Calibri;");
        tambahx.getChildren().add(tambahLabel);

        HBox fotoSection = new HBox(70);
        fotoSection.setAlignment(Pos.CENTER_LEFT);  // Set alignment to center
        Label fotoLabel = new Label("Foto Produk          :");
        fotoLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        VBox imageBox = new VBox();
        imageBox.setPrefSize(100, 100);
        Button fotoButton = new Button("Upload Foto");
        fotoButton.getStyleClass().add("buttonSimpan");

        fotoButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pilih Gambar");
            fileChooser.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                foto = selectedFile.getAbsolutePath(); // Menyimpan path gambar yang dipilih
                // Menggunakan ImageView untuk menampilkan gambar yang dipilih
                ImageView image = new ImageView(new Image(selectedFile.toURI().toString()));
                image.setFitWidth(100);
                image.setFitHeight(100);
                imageBox.getChildren().addAll(image);
            }
        });

        fotoSection.getChildren().addAll(fotoLabel, imageBox, fotoButton);

        HBox namaSection = new HBox();
        namaSection.setAlignment(Pos.CENTER_LEFT);  // Set alignment to center
        Label namaLabel = new Label("Nama Produk       : ");
        namaLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        TextField namaField = new TextField();
        namaField.setPrefSize(300, 30);
        namaField.getStyleClass().add("textFieldforProduk");
        namaSection.getChildren().addAll(namaLabel, namaField);

        HBox deskripsiSection = new HBox();
        deskripsiSection.setAlignment(Pos.CENTER_LEFT);
        Label deskripsiLabel = new Label("Deskripsi Produk : ");
        deskripsiLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        TextField deskripsiField = new TextField();
        deskripsiField.setPrefSize(300, 100);
        deskripsiField.getStyleClass().add("textFieldforProduk");
        deskripsiSection.getChildren().addAll(deskripsiLabel, deskripsiField);

        HBox hargaSection = new HBox();
        hargaSection.setAlignment(Pos.CENTER_LEFT);
        Label hargaLabel = new Label("Harga Produk        : ");
        hargaLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        TextField hargaField = new TextField();
        hargaField.setPrefSize(300, 30);
        hargaField.getStyleClass().add("textFieldforProduk");
        hargaSection.getChildren().addAll(hargaLabel, hargaField);

        Button simpan = new Button("KONFIRMASI EDIT");
        simpan.setPrefSize(150, 50);
        simpan.getStyleClass().add("buttonSimpan");

        simpan.setOnAction(e -> {
            String namaProduk = namaField.getText();
            String deskripsiProduk = deskripsiField.getText();
            int hargaProduk = Integer.parseInt(hargaField.getText());
            if (foto == null || foto.isEmpty()) {
                // Tampilkan pesan jika tidak ada gambar yang dipilih
            } else {
                boolean updateProdukFoto = ProdukControllers.tambahProduk(userId, namaProduk, foto, deskripsiProduk, hargaProduk, userId);
                users.setfoto(foto);
                if (updateProdukFoto) {
                    System.out.println("Berhasil");
                    // Tampilkan pesan berhasil jika penyimpanan berhasil
                } else {
                    // Tampilkan pesan gagal jika terjadi masalah dalam penyimpanan
                    System.out.println("Gagal");
                }
            }
        });

        tambahWindow.getChildren().addAll(tambahx, fotoSection, namaSection, deskripsiSection, hargaSection, simpan);
        layout.getChildren().addAll(tambahWindow);

        Region spasi = new Region();
        spasi.setPrefSize(700, 0);
        
        username.getChildren().addAll(userLabel, photoProfile );
        navigationBar.getChildren().addAll(logo, spasi, username);
        awal.getChildren().addAll(navigationBar, layout);

        root.getChildren().add(awal);

        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle("Shopease");
        stage.setScene(scene);
        stage.show();
    }
}
