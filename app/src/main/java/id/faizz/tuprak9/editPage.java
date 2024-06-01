package id.faizz.tuprak9;
import java.io.File;

import id.faizz.tuprak9.controllers.ProdukControllers;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Produk;
import id.faizz.tuprak9.models.Users;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class editPage {
    private Stage stage;
    private String foto;
    private Produk produk;

    editPage(Stage stage, Produk produk) {
        this.stage = stage;
        this.produk = produk;
    }

    public void show(int userId) {
        Users users = UsersControllers.getUserById(userId);

        StackPane root = new StackPane();

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
            System.out.println("Loading user profile image from: file:" + users.getfoto());
            photoProfile = new ImageView(new Image("file:" + users.getfoto()));
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to debug the issue
            System.out.println("Failed to load user profile image, using fallback image.");
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

        
        Region spasi = new Region();
        spasi.setPrefSize(700, 0);

        username.getChildren().addAll(photoProfile, userLabel);
        navigationBar.getChildren().addAll(logo, spasi, username);



        VBox layout2 = new VBox();
        layout2.setPrefSize(1382, 626);
        layout2.setAlignment(Pos.CENTER);
        layout2.setPadding(new Insets(48, 277, 48, 277));
        layout2.getStyleClass().add("homePage");

        VBox editWindow = new VBox(20);
        editWindow.setPadding(new Insets(40));
        editWindow.setMaxSize(862, 489);
        editWindow.getStyleClass().add("editWindow");

        Label editLabel = new Label("Edit Produk");
        editLabel.setStyle("-fx-text-fill: #000; -fx-font-size: 25; -fx-font-weight: bold; -fx-font-family: Calibri;");
        editWindow.getChildren().add(editLabel);

        HBox fotoSection = new HBox(70);
        VBox imageBox = new VBox();
        fotoSection.setAlignment(Pos.CENTER_LEFT);
        Label fotoLabel = new Label("Foto Produk          :");
        fotoLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        ImageView fotoProdukSebelumnya = new ImageView(new Image("file:" + produk.getFoto()));
        fotoProdukSebelumnya.setFitHeight(100);
        fotoProdukSebelumnya.setFitWidth(100);
        fotoProdukSebelumnya.setPreserveRatio(true); // Ensure the ratio is preserved
        imageBox.setMaxSize(100, 100);
        imageBox.getChildren().add(fotoProdukSebelumnya);
        
        Button fotoButton = new Button("Upload Foto");
        fotoButton.getStyleClass().add("buttonSimpan");
        
        fotoButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pilih Gambar");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
            );
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                foto = selectedFile.getAbsolutePath();
                ImageView image = new ImageView(new Image(selectedFile.toURI().toString()));
                image.setFitWidth(100);
                image.setFitHeight(100);
                image.setPreserveRatio(true); // Ensure the ratio is preserved
                imageBox.getChildren().setAll(image);
            }
        });

        fotoSection.getChildren().addAll(fotoLabel, imageBox, fotoButton);

        HBox namaSection = new HBox();
        namaSection.setAlignment(Pos.CENTER_LEFT);
        Label namaLabel = new Label("Nama Produk       : ");
        namaLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        TextField namaField = new TextField(produk.getNama());
        namaField.setPrefSize(300, 30);
        namaField.getStyleClass().add("textFieldforProduk");
        namaSection.getChildren().addAll(namaLabel, namaField);

        HBox deskripsiSection = new HBox();
        deskripsiSection.setAlignment(Pos.CENTER_LEFT);
        Label deskripsiLabel = new Label("Deskripsi Produk : ");
        deskripsiLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        TextField deskripsiField = new TextField(produk.getDeskripsi());
        deskripsiField.setPrefSize(300, 100);
        deskripsiField.getStyleClass().add("textFieldforProduk");
        deskripsiSection.getChildren().addAll(deskripsiLabel, deskripsiField);

        HBox hargaSection = new HBox();
        hargaSection.setAlignment(Pos.CENTER_LEFT);
        Label hargaLabel = new Label("Harga Produk       : ");
        hargaLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        TextField hargaField = new TextField(String.valueOf(produk.getHarga()));
        hargaField.setPrefSize(300, 30);
        hargaField.getStyleClass().add("textFieldforProduk");
        hargaSection.getChildren().addAll(hargaLabel, hargaField);

        Button simpan = new Button("KONFIRMASI EDIT");
        simpan.setPrefSize(200, 50);
        simpan.getStyleClass().add("buttonSimpan");

        Button kembali = new Button("KEMBALI");
        kembali.setPrefSize(150, 50);
        kembali.getStyleClass().add("buttonSimpan");

        kembali.setOnAction(e -> {
            SellerPage sellerPage = new SellerPage(stage);
            sellerPage.show(userId);
        });

        simpan.setOnAction(e -> {
            try {
                String namaProduk = namaField.getText();
                String deskripsiProduk = deskripsiField.getText();
                int hargaProduk = Integer.parseInt(hargaField.getText());
                
                System.out.println("Nama Produk: " + namaProduk);
                System.out.println("Deskripsi Produk: " + deskripsiProduk);
                System.out.println("Harga Produk: " + hargaProduk);
                System.out.println("Foto Produk: " + (foto == null ? produk.getFoto() : foto));
        
                boolean updateProduk;
                if (foto == null || foto.isEmpty()) {
                    updateProduk = ProdukControllers.updateProduk(produk.getId(), namaProduk, produk.getFoto(), deskripsiProduk, hargaProduk, userId);
                } else {
                    updateProduk = ProdukControllers.updateProduk(produk.getId(), namaProduk, foto, deskripsiProduk, hargaProduk, userId);
                    produk.setFoto(foto);
                }
        
                if (updateProduk) {
                    System.out.println("Berhasil mengupdate produk");
                } else {
                    System.out.println("Gagal mengupdate produk");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Terjadi kesalahan saat mengupdate produk");
            }
        });
        

        editWindow.getChildren().addAll(fotoSection, namaSection, deskripsiSection, hargaSection, simpan, kembali);
        layout2.getChildren().add(editWindow);
        awal.getChildren().addAll(navigationBar,layout2);

        root.getChildren().addAll(awal);
        Scene scene = new Scene(root, 1382, 736);
        stage.setScene(scene);
        scene.getStylesheets().add("styles/styles.css");
        stage.show();
    }
}
