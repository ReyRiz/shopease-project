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

        HBox username = new HBox(10);
        username.setAlignment(Pos.CENTER);
        ImageView photoProfile;
        try {
            System.out.println("Loading user profile image from: file:" + produk.getFoto());
            photoProfile = new ImageView(new Image("file:" + produk.getFoto()));
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to debug the issue
            System.out.println("Failed to load user profile image, using fallback image.");
            photoProfile = new ImageView(new Image("styles/contohPP.png"));
        }
        photoProfile.setFitHeight(45);
        photoProfile.setFitWidth(45);
        photoProfile.getStyleClass().add("photoProfile");
        Label userLabel = new Label(produk.getNama());
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
        fotoSection.setAlignment(Pos.CENTER_LEFT);
        Label fotoLabel = new Label("Foto Produk          :");
        fotoLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        ImageView fotoProdukSebelumnya = new ImageView(new Image("file:" + produk.getFoto()));
        VBox imageBox = new VBox();
        imageBox.setMaxSize(100, 100);
        Button fotoButton = new Button("Upload Foto");
        fotoButton.getStyleClass().add("buttonSimpan");
        imageBox.getChildren().add(fotoProdukSebelumnya);
        try {
            ImageView fotoProduk = new ImageView(new Image("file:" + produk.getFoto()));
            imageBox.getChildren().clear();
            imageBox.getChildren().add(fotoProduk);
        } catch (Exception err) {
            ImageView fotoProduk = new ImageView(new Image("styles/eye-slash.png"));
            imageBox.getChildren().add(fotoProduk);
        }

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
        Label hargaLabel = new Label("Harga Produk        : ");
        hargaLabel.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 25;");
        TextField hargaField = new TextField(String.valueOf(produk.getHarga()));
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
            boolean updateProduk;
            if (foto == null || foto.isEmpty()) {
                updateProduk = ProdukControllers.updateProduk(userId, namaProduk, produk.getFoto(), deskripsiProduk, hargaProduk, userId);
            } else {
                updateProduk = ProdukControllers.updateProduk(userId, namaProduk, foto, deskripsiProduk, hargaProduk, userId);
                users.setfoto(foto);
            }
            if (updateProduk) {
                System.out.println("Berhasil mengupdate produk");
            } else {
                System.out.println("Gagal mengupdate produk");
            }
        });

        editWindow.getChildren().addAll(fotoSection, namaSection, deskripsiSection, hargaSection, simpan);
        layout2.getChildren().add(editWindow);
        awal.getChildren().addAll(navigationBar, layout2);

        root.getChildren().addAll(awal);
        Scene scene = new Scene(root, 1382, 736);
        stage.setScene(scene);
        scene.getStylesheets().add("styles/styles.css");
        stage.show();
    }
}
