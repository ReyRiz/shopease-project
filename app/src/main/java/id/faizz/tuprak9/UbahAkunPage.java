package id.faizz.tuprak9;

import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Users;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class UbahAkunPage {
    
    private Stage stage;

    UbahAkunPage(Stage stage){
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

        Label profileLabel = new Label("Akun");
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
        alamatLabel.getStyleClass().add("labelSlideBar");

        Label pwLabel = new Label("Ubah Password");
        pwLabel.getStyleClass().add("labelSlideBar");
        Label akunLabel = new Label("Ubah Akun");
        akunLabel.getStyleClass().add("labelSlideBarFocused");
        Label fotoLabel = new Label("Ubah Foto");
        fotoLabel.getStyleClass().add("labelSlideBar");

        
        focusedBox.getChildren().add(akunLabel);
        focusedBox.setMinSize(150, 47);
        focusedBox.setAlignment(Pos.CENTER);
        focusedBox.getStyleClass().add("focusedBox");
        slideBar.getChildren().addAll(profilLabel, alamatLabel, pwLabel, focusedBox, fotoLabel);
        // slideBar.setAlignment(Pos.CENTER);
        slideBar.setAlignment(Pos.BASELINE_CENTER);
        navigationBar.getChildren().addAll(logo, garis, profileLabel);


        //Kumpulan Action Button
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

        //Layout Kedua
        Region space37 = new Region();
        space37.setPrefWidth(37);

        VBox layoutBox2 = new VBox(20);
        layoutBox2.setPrefSize(900, 366);
        layoutBox2.getStyleClass().add("layoutBox2");
        layoutBox2.setPadding(new Insets(26, 58, 26, 58));
        layoutBox2.setAlignment(Pos.CENTER);

        Label judul = new Label("Ubah Akun Regular ke Akun Seller");
        judul.getStyleClass().add("judulLayoutBox2");

        Line batas = new Line(299, 322, 1087, 322);
        batas.setFill(Color.web("#000"));

        Text deskripsi = new Text();
        deskripsi.setText("Jika Anda Mengubah ke Akun Seller, Anda akan Mendapatkan\n" + //
                        "akses untuk menjual produk dan mengelola toko anda");

        deskripsi.setTextAlignment(TextAlignment.CENTER);
        deskripsi.setStyle("-fx-font-size: 20;");
        VBox layout3 = new VBox(20);
        
        
        Button ubahAkun = new Button("Ubah Akun");
        ubahAkun.getStyleClass().add("buttonSimpan");
        ubahAkun.setPrefSize(120, 30);

        
        Label succesLabel = new Label("Berhasil!");
        succesLabel.setTextFill(Color.web("#6345DD"));
        succesLabel.setVisible(false);


        ubahAkun.setOnAction(e -> {
            UsersControllers.updateUser(userId, users.getNama(), users.getNomorHp(), users.getTempatLahir(), users.getAlamat(), users.getNamaPenerima(), users.getNomorHpPenerima(), users.getfoto(), "seller");
            succesLabel.setVisible(true);

            if (users.getRole().equals("seller")){
                SellerPage sellerPage = new SellerPage(stage);
                sellerPage.show(userId);
            }
        });
        


        layout3.getChildren().addAll(ubahAkun, succesLabel);
        layout3.setAlignment(Pos.CENTER);


        stage.getIcons().add(new Image("styles/AppIcon.png"));

        layoutBox2.getChildren().addAll(judul, batas, deskripsi, layout3);

        Region space65 = new Region();
        space65.setPrefSize(0, 65);
        
        bagianBawah.getChildren().addAll(slideBar, layoutBox2);
        bagianBawah.setPadding(new Insets(0, 241, 116, 241));
        // awal.setPadding(new Insets(0, 241, 116, 241));
        awal.getChildren().addAll(navigationBar, space65, bagianBawah);
        root.getChildren().add(awal);
        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");
        stage.show();
    }
    
}
