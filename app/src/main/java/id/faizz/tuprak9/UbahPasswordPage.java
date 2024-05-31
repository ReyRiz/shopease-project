package id.faizz.tuprak9;

import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Users;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class UbahPasswordPage {

    private Stage stage;

    UbahPasswordPage(Stage stage){
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

        VBox focusedBox = new VBox();
        Label profilLabel = new Label("Profile");
        profilLabel.getStyleClass().add("labelSlideBar");

        Label alamatLabel = new Label("Alamat");
        alamatLabel.getStyleClass().add("labelSlideBar");
        Label pwLabel = new Label("Ubah Password");
        pwLabel.getStyleClass().add("labelSlideBarFocused");
        Label akunLabel = new Label("Ubah Akun");
        akunLabel.getStyleClass().add("labelSlideBar");
        Label fotoLabel = new Label("Ubah Foto");
        fotoLabel.getStyleClass().add("labelSlideBar");

        focusedBox.getStyleClass().add("focusedBox");
        focusedBox.getChildren().add(pwLabel);
        focusedBox.setMinSize(150, 47);
        focusedBox.setAlignment(Pos.CENTER);
        slideBar.getChildren().addAll(profilLabel, alamatLabel, focusedBox, akunLabel, fotoLabel);
        slideBar.setAlignment(Pos.BASELINE_CENTER);
        navigationBar.getChildren().addAll(logo, garis, profileLabel);

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
        layoutBox2.setPrefSize(900, 366);
        layoutBox2.getStyleClass().add("layoutBox2");
        layoutBox2.setPadding(new Insets(26, 58, 26, 58));

        Label judul = new Label("Password Saya");
        judul.getStyleClass().add("judulLayoutBox2");

        Line batas = new Line(299, 322, 1087, 322);
        batas.setFill(Color.web("#000"));

        HBox passwordLama = new HBox(37);
        Label passwordLamaLabel = new Label("Password Lama                           :");
        passwordLamaLabel.getStyleClass().add("allLabelforSetting");
        PasswordField passwordLamaField = new PasswordField();
        passwordLamaField.setPrefSize(350, 30);
        passwordLamaField.getStyleClass().add("allFieldforSetting");
        passwordLama.getChildren().addAll(passwordLamaLabel, space37, passwordLamaField);

        HBox passwordBaru = new HBox(37);
        Label passwordBaruLabel = new Label("Password Baru                            :");
        passwordBaruLabel.getStyleClass().add("allLabelforSetting");
        PasswordField passwordBaruField = new PasswordField();
        passwordBaruField.setPrefSize(350, 30);
        passwordBaruField.getStyleClass().add("allFieldforSetting");
        passwordBaru.getChildren().addAll(passwordBaruLabel, space37, passwordBaruField);

        HBox konfirmasiPasswordBaru = new HBox();
        Label konfirmasiPasswordBaruLabel = new Label("Konfirmasi Password Baru        :");
        konfirmasiPasswordBaruLabel.getStyleClass().add("allLabelforSetting");
        PasswordField konfirmasiPasswordBaruField = new PasswordField();
        konfirmasiPasswordBaruField.getStyleClass().add("allFieldforSetting");
        konfirmasiPasswordBaruField.setPrefSize(350, 30);
        konfirmasiPasswordBaru.getChildren().addAll(konfirmasiPasswordBaruLabel, space37, konfirmasiPasswordBaruField);

        HBox layout3 = new HBox(20);

        Button simpan = new Button("Simpan");
        simpan.getStyleClass().add("buttonSimpan");
        simpan.setPrefSize(250, 30);

        Label successLabel = new Label("Berhasil!");
        successLabel.setTextFill(Color.web("#6345DD"));
        successLabel.setVisible(false);
        successLabel.setPrefSize(250, 30);

        Label errorLabel = new Label("Gagal! Periksa kembali input Anda.");
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
        errorLabel.setPrefSize(500, 20);

        stage.getIcons().add(new Image("styles/AppIcon.png"));
        simpan.setOnAction(e -> {
            String passwordLamaa = passwordLamaField.getText();
            String passwordBaruu = passwordBaruField.getText();
            String konfirmasiPasswordBaruu = konfirmasiPasswordBaruField.getText();
            boolean updateUser = false;

            if (passwordLamaa.equals(users.getPassword())) {
                if (passwordBaruu.equals(konfirmasiPasswordBaruu)) {
                    updateUser = UsersControllers.updateUserPassword(userId, passwordBaruu);
                } else {
                    errorLabel.setText("Password baru dan konfirmasi tidak cocok.");
                    errorLabel.setVisible(true);
                }
            } else {
                errorLabel.setText("Password lama tidak cocok.");
                errorLabel.setVisible(true);
            }

            if (updateUser) {
                successLabel.setVisible(true);
                errorLabel.setVisible(false);
            }
        });

        layout3.getChildren().addAll(simpan, successLabel, errorLabel);

        layoutBox2.getChildren().addAll(judul, batas, passwordLama, passwordBaru, konfirmasiPasswordBaru, layout3);

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
