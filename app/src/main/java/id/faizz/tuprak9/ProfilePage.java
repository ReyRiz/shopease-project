package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

public class ProfilePage{
    private Stage stage;

    ProfilePage(Stage stage){
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

        logo.setOnMouseClicked(e -> {
            if (users.getRole().equals("seller")){
                SellerPage sellerPage = new SellerPage(stage);
                sellerPage.show(userId);
            } else {
                HomePage home = new HomePage(stage);
                home.show(userId);
            }
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
        profilLabel.getStyleClass().add("labelSlideBarFocused");
        focusedBox.getStyleClass().add("focusedBox");
        focusedBox.getChildren().add(profilLabel);
        focusedBox.setMinSize(150, 47);
        focusedBox.setAlignment(Pos.CENTER);

        Label alamatLabel = new Label("Alamat");
        alamatLabel.getStyleClass().add("labelSlideBar");
        Label pwLabel = new Label("Ubah Password");
        pwLabel.getStyleClass().add("labelSlideBar");
        Label akunLabel = new Label("Ubah Akun");
        akunLabel.getStyleClass().add("labelSlideBar");
        Label fotoLabel = new Label("Ubah Foto");
        fotoLabel.getStyleClass().add("labelSlideBar");

        slideBar.getChildren().addAll(focusedBox, alamatLabel, pwLabel, akunLabel, fotoLabel);
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
        layoutBox2.setPrefSize(900, 466);
        layoutBox2.getStyleClass().add("layoutBox2");
        layoutBox2.setPadding(new Insets(26, 58, 26, 58));

        Label judul = new Label("Profil Saya");
        judul.getStyleClass().add("judulLayoutBox2");

        Line batas = new Line(299, 322, 1087, 322);
        batas.setFill(Color.web("#000"));

        HBox nama = new HBox(37);
        Label namaLabel = new Label("Nama                         :");
        namaLabel.getStyleClass().add("allLabelforSetting");
        TextField namaField = new TextField(users.getNama());
        namaField.setPrefSize(350, 30);
        namaField.getStyleClass().add("allFieldforSetting");
        nama.getChildren().addAll(namaLabel, space37, namaField);

        HBox nomorHp = new HBox(37);
        Label nomorLabel = new Label("Nomor Handphone :");
        nomorLabel.getStyleClass().add("allLabelforSetting");
        TextField nomorField = new TextField(users.getNomorHp());
        nomorField.setPrefSize(350, 30);
        nomorField.getStyleClass().add("allFieldforSetting");
        nomorHp.getChildren().addAll(nomorLabel, space37, nomorField);

        HBox tempatLahir = new HBox();
        Label tempatLabel = new Label("Tempat Lahir            :");
        TextField tempatField = new TextField(users.getTempatLahir());
        tempatLabel.getStyleClass().add("allLabelforSetting");
        tempatField.getStyleClass().add("allFieldforSetting");
        tempatField.setPrefSize(350, 30);
        tempatLahir.getChildren().addAll(tempatLabel, space37, tempatField);

        HBox layout3 = new HBox(200);

        Button simpan = new Button("Simpan");
        simpan.getStyleClass().add("buttonSimpan");
        simpan.setPrefSize(120, 30);

        Label succesLabel = new Label("Berhasil!");
        succesLabel.setTextFill(Color.web("#6345DD"));
        succesLabel.setVisible(false);

        simpan.setOnAction(e -> {
            String namaX = namaField.getText();
            String nomorHP = nomorField.getText();
            String tempatLahirr = tempatField.getText();
            boolean updateUser = UsersControllers.updateUser(userId, namaX, nomorHP, tempatLahirr, null , null, null, null,  "regular");
            if (updateUser){
                succesLabel.setVisible(true);
            }
        });

        layout3.getChildren().addAll(simpan, succesLabel);

        stage.getIcons().add(new Image("styles/AppIcon.png"));

        layoutBox2.getChildren().addAll(judul, batas, nama, nomorHp, tempatLahir, layout3);

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
