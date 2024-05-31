package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Users;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class AlamatPage {
    private Stage stage;

    AlamatPage(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) {
        Users users = UsersControllers.getUserById(userId);

        // Ensure users is not null before proceeding
        if (users == null) {
            System.err.println("User not found with id: " + userId);
            return;
        }

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
            HomePage home = new HomePage(stage);
            home.show(userId);
        });

        Line garis = new Line(346, 82, 346, 137);
        garis.setStroke(Color.web("#FFFFFF"));
        garis.setStrokeWidth(2);

        Label profileLabel = new Label("Profil");
        profileLabel.getStyleClass().add("profileLabel");

        VBox bagianBawah = new VBox(18);

        HBox slideBar = new HBox(60);
        slideBar.setPadding(new Insets(7, 10, 7, 10));
        slideBar.setPrefSize(900, 61);
        slideBar.getStyleClass().add("slideBar-bg");

        Label profilLabel = new Label("Profile");
        profilLabel.getStyleClass().add("labelSlideBar");

        VBox focusedBox = new VBox();
        Label alamatLabel = new Label("Alamat");
        alamatLabel.getStyleClass().add("labelSlideBarFocused");
        focusedBox.getChildren().add(alamatLabel);
        focusedBox.setMinSize(150, 47);
        focusedBox.setAlignment(Pos.CENTER);
        focusedBox.getStyleClass().add("focusedBox");

        Label pwLabel = new Label("Ubah Password");
        pwLabel.getStyleClass().add("labelSlideBar");
        Label akunLabel = new Label("Ubah Akun");
        akunLabel.getStyleClass().add("labelSlideBar");
        Label fotoLabel = new Label("Ubah Foto");
        fotoLabel.getStyleClass().add("labelSlideBar");

        slideBar.getChildren().addAll(profilLabel, focusedBox, pwLabel, akunLabel, fotoLabel);
        slideBar.setAlignment(Pos.BASELINE_CENTER);
        navigationBar.getChildren().addAll(logo, garis, profileLabel);

        // Kumpulan Action Button
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

        // Layout Kedua
        VBox layoutBox2 = new VBox(20);
        layoutBox2.setPrefSize(900, 366);
        layoutBox2.getStyleClass().add("layoutBox2");
        layoutBox2.setPadding(new Insets(26, 58, 26, 58));

        Label judul = new Label("Alamat Saya");
        judul.getStyleClass().add("judulLayoutBox2");

        Line batas = new Line(299, 322, 1087, 322);
        batas.setFill(Color.web("#000"));

        // Text Field
        HBox Alamat = new HBox(10);
        Label AlamatLabel = new Label("Alamat                       :");
        AlamatLabel.getStyleClass().add("allLabelforSetting");
        TextField AlamatField = new TextField(users.getAlamat());
        AlamatField.setPrefSize(350, 30);
        AlamatField.getStyleClass().add("allFieldforSetting");
        Alamat.getChildren().addAll(AlamatLabel, AlamatField);

        HBox NamaPenerima = new HBox(10);
        Label NPLabel = new Label("Nama Penerima       :");
        NPLabel.getStyleClass().add("allLabelforSetting");
        TextField namaPenerimaField = new TextField(users.getNamaPenerima());
        namaPenerimaField.setPrefSize(350, 30);
        namaPenerimaField.getStyleClass().add("allFieldforSetting");
        NamaPenerima.getChildren().addAll(NPLabel, namaPenerimaField);

        HBox nomorHp = new HBox(10);
        Label nomorLabel = new Label("Nomor Handphone :");
        nomorLabel.getStyleClass().add("allLabelforSetting");
        TextField nomorField = new TextField(users.getNomorHpPenerima());
        nomorField.setPrefSize(350, 30);
        nomorField.getStyleClass().add("allFieldforSetting");
        nomorHp.getChildren().addAll(nomorLabel, nomorField);

        HBox layout3 = new HBox(200);

        Button simpan = new Button("Simpan");
        simpan.getStyleClass().add("buttonSimpan");
        simpan.setPrefSize(120, 30);

        Label succesLabel = new Label("Berhasil!");
        succesLabel.setTextFill(Color.web("#6345DD"));
        succesLabel.setVisible(false);

        simpan.setOnAction(e -> {
            String alamatX = AlamatField.getText();
            String namaPenerima = namaPenerimaField.getText();
            String nomorHpStr = nomorField.getText();
            boolean updateUser = UsersControllers.updateUser(userId, users.getNama(), users.getNomorHp(), users.getTempatLahir(), alamatX, namaPenerima, nomorHpStr , null, "regular");
            if (updateUser) {
                succesLabel.setVisible(true);
            }
        });

        layout3.getChildren().addAll(simpan, succesLabel);

        layoutBox2.getChildren().addAll(judul, batas, Alamat, NamaPenerima, nomorHp, layout3);

        Region space65 = new Region();
        space65.setPrefSize(0, 65);

        bagianBawah.getChildren().addAll(slideBar, layoutBox2);
        bagianBawah.setPadding(new Insets(0, 241, 116, 241));
        awal.getChildren().addAll(navigationBar, space65, bagianBawah);
        root.getChildren().add(awal);
        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
        stage.getIcons().add(new Image("styles/AppIcon.png"));
        stage.setTitle("Shopease");
        stage.show();
        
    }
}
