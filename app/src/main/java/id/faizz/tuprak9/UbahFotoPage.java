package id.faizz.tuprak9;

import java.io.File;

import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Users;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UbahFotoPage {

    private Stage stage;
    private String imagePath;

    UbahFotoPage(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) {
        Users users = UsersControllers.getUserById(userId);

        StackPane root = new StackPane();
        root.getStyleClass().add("homePage");

        VBox awal = new VBox();

        HBox navigationBar = new HBox(25);
        navigationBar.setPadding(new Insets(32));
        navigationBar.setPrefSize(1382, 110);
        navigationBar.getStyleClass().add("navigationBar");
        navigationBar.setAlignment(Pos.CENTER_LEFT);
        ImageView logo = new ImageView(new Image("/styles/logobiasa.png"));

        logo.setOnMouseClicked(e -> {
            if (users.getRole().equals("seller")) {
                SellerPage sellerPage = new SellerPage(stage);
                sellerPage.show(userId);
            } else {
                HomePage home = new HomePage(stage);
                home.show(userId);
            }
        });

        HBox logout = new HBox(10);
        logout.setAlignment(Pos.CENTER);
        Label logoutLabel = new Label("Logout");
        logoutLabel.setStyle("-fx-text-fill: #FFF; -fx-font-family: Calibri; -fx-font-size: 30;");
        ImageView logoutImg =new ImageView(new Image("/styles/exit.png"));
        logout.getChildren().addAll(logoutLabel, logoutImg);

        Region spasi = new Region();
        HBox.setHgrow(spasi, Priority.ALWAYS);
        
        logout.setOnMouseClicked(e -> {
            App splash = new App();
            try {
                splash.start(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Line garis = new Line(346, 82, 346, 137);
        garis.setStroke(Color.web("#FFFFFF"));
        garis.setStrokeWidth(2);

        Label profileLabel = new Label("Foto");
        profileLabel.getStyleClass().add("profileLabel");

        VBox bagianBawah = new VBox(18);

        HBox slideBar = new HBox(60);
        slideBar.setPadding(new Insets(7, 10, 7, 10));
        slideBar.setPrefSize(900, 61);
        slideBar.getStyleClass().add("slideBar-bg");

        VBox focusedBox = new VBox();
        Label profilLabel = new Label("Profile");
        profilLabel.getStyleClass().add("labelSlideBar");

        Label alamatLabel = new Label("Alamat");
        alamatLabel.getStyleClass().add("labelSlideBar");
        Label pwLabel = new Label("Ubah Password");
        pwLabel.getStyleClass().add("labelSlideBar");
        Label akunLabel = new Label("Ubah Akun");
        akunLabel.getStyleClass().add("labelSlideBar");
        Label fotoLabel = new Label("Ubah Foto");
        fotoLabel.getStyleClass().add("labelSlideBarFocused");

        focusedBox.getStyleClass().add("focusedBox");
        focusedBox.getChildren().add(fotoLabel);
        focusedBox.setMinSize(150, 47);
        focusedBox.setAlignment(Pos.CENTER);

        slideBar.getChildren().addAll(profilLabel, alamatLabel, pwLabel, akunLabel, focusedBox);
        slideBar.setAlignment(Pos.BASELINE_CENTER);
        navigationBar.getChildren().addAll(logo, garis, profileLabel, spasi, logout);

        profilLabel.setOnMouseClicked(e -> {
            ProfilePage profilePage = new ProfilePage(stage);
            profilePage.show(userId);
        });

        alamatLabel.setOnMouseClicked(e -> {
            AlamatPage alamatPage = new AlamatPage(stage);
            alamatPage.show(userId);
        });

        pwLabel.setOnMouseClicked(e -> {
            UbahPasswordPage ubahPasswordPage = new UbahPasswordPage(stage);
            ubahPasswordPage.show(userId);
        });

        akunLabel.setOnMouseClicked(e -> {
            UbahAkunPage ubahAkunPage = new UbahAkunPage(stage);
            ubahAkunPage.show(userId);
        });

        fotoLabel.setOnMouseClicked(e -> {
            UbahFotoPage ubahFotoPage = new UbahFotoPage(stage);
            ubahFotoPage.show(userId);
        });

        Region space37 = new Region();
        space37.setPrefWidth(37);

        VBox layoutBox2 = new VBox(20);
        layoutBox2.setAlignment(Pos.BASELINE_CENTER);
        layoutBox2.setPrefSize(900, 466);
        layoutBox2.getStyleClass().add("layoutBox2");
        layoutBox2.setPadding(new Insets(26, 58, 26, 58));

        Label judul = new Label("Ubah Foto");
        judul.getStyleClass().add("judulLayoutBox2");

        Line batas = new Line(299, 322, 1087, 322);
        batas.setFill(Color.web("#000"));

        VBox imageBox = new VBox();
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setMaxSize(180, 180);
        imageBox.getStyleClass().add("fotoProfil");
        imageBox.setPrefSize(200, 200); // Set size to be square

        Circle clip = new Circle(90, 90, 90); // Clip for the VBox
        imageBox.setClip(clip); // Set the clip to the VBox

        try {
            ImageView fotoSebelumnya = new ImageView(new Image(users.getfoto()));
            fotoSebelumnya.setFitHeight(200);
            fotoSebelumnya.setFitWidth(200);
            fotoSebelumnya.setPreserveRatio(true);
            imageBox.getChildren().add(fotoSebelumnya);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HBox layout3 = new HBox(20);
        layout3.setAlignment(Pos.CENTER);

        Button simpan = new Button("Simpan");
        simpan.getStyleClass().add("buttonSimpan");
        simpan.setPrefSize(200, 30);

        Button upload = new Button("Upload Gambar");
        upload.getStyleClass().add("buttonSimpan");
        upload.setPrefSize(200, 30);

        Label succesLabel = new Label("Berhasil!");
        succesLabel.setStyle("-fx-font-size: 20;");
        succesLabel.setTextFill(Color.web("#6345DD"));
        succesLabel.setVisible(false);

        upload.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pilih Gambar");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                imagePath = selectedFile.getAbsolutePath(); 
                ImageView imageView = new ImageView(new Image(selectedFile.toURI().toString()));
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);
                imageBox.getChildren().clear();
                imageBox.getChildren().add(imageView);
            }
        });

        stage.getIcons().add(new Image("styles/AppIcon.png"));

        simpan.setOnAction(e -> {
            if (imagePath == null || imagePath.isEmpty()) {
                succesLabel.setText("Pilih gambar terlebih dahulu!");
                succesLabel.setTextFill(Color.RED);
                succesLabel.setVisible(true);
            } else {
                boolean updateUserFoto = UsersControllers.updateUserFoto(userId, imagePath);
                users.setfoto(imagePath);
                if (updateUserFoto) {
                    succesLabel.setText("Berhasil!");
                    succesLabel.setTextFill(Color.web("#6345DD"));
                    succesLabel.setVisible(true);
                } else {
                    succesLabel.setText("Gagal menyimpan gambar.");
                    succesLabel.setTextFill(Color.RED);
                    succesLabel.setVisible(true);
                }
            }
        });

        layout3.getChildren().addAll(simpan, upload);

        layoutBox2.getChildren().addAll(judul, batas, imageBox, layout3, succesLabel);

        Region space65 = new Region();
        space65.setPrefSize(0, 65);

        bagianBawah.getChildren().addAll(slideBar, layoutBox2);
        bagianBawah.setPadding(new Insets(0, 241, 116, 241));
        awal.getChildren().addAll(navigationBar, space65, bagianBawah);
        root.getChildren().add(awal);
        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");
        stage.show();
    }
}
