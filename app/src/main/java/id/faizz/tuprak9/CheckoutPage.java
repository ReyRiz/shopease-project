package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import id.faizz.tuprak9.controllers.KeranjangControllers;
import id.faizz.tuprak9.controllers.ProdukControllers;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Keranjang;
import id.faizz.tuprak9.models.Produk;
import id.faizz.tuprak9.models.Users;
import java.util.ArrayList;

public class CheckoutPage {
    private Stage stage;

    CheckoutPage(Stage stage){
        this.stage = stage;
    }

    public void show(int userId, Label totalHarga, ArrayList<Integer> idTerpilih){
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

        Label profileLabel = new Label("Checkout");
        profileLabel.getStyleClass().add("profileLabel");

        navigationBar.getChildren().addAll(logo, garis, profileLabel);

        VBox bagianBawah = new VBox();
        bagianBawah.setPadding(new Insets(0, 241, 116, 241));

        HBox layout1 = new HBox();
        layout1.setPadding(new Insets(20, 60, 20, 60));
        layout1.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 10;");

        VBox layoutAN = new VBox(10);
        layoutAN.setPrefSize(174, 120);

        Label alamatLabel = new Label("Alamat Pengiriman");
        alamatLabel.setPrefSize(258, 26);
        alamatLabel.setStyle("-fx-font-family: Calibri; -fx-font-size: 20; -fx-font-weight: Bold; -fx-text-fill: #6345DD;");

        Label namaPenerima = new Label(users.getNamaPenerima());
        namaPenerima.setStyle("-fx-font-family: Calibri; -fx-font-size: 15; -fx-font-weight: Bold; -fx-text-fill: #000;");
        namaPenerima.setPrefSize(174, 15);

        Label nomorHpPenerima = new Label(users.getNomorHpPenerima());
        nomorHpPenerima.setStyle("-fx-font-family: Calibri; -fx-font-size: 15; -fx-font-weight: Bold; -fx-text-fill: #000;");
        nomorHpPenerima.setPrefSize(174, 15);

        layoutAN.getChildren().addAll(alamatLabel, namaPenerima, nomorHpPenerima);

        Label alamatPenerima = new Label(users.getAlamat());
        alamatPenerima.setPrefSize(461, 76);
        alamatPenerima.setStyle("-fx-font-family: Calibri; -fx-font-size: 20; -fx-font-weight: Bold; -fx-text-fill: #000;");
        alamatPenerima.setWrapText(true);

        layout1.getChildren().addAll(layoutAN, alamatPenerima);

        HBox layout2 = new HBox();
        layout2.setPrefSize(1302, 318);
        layout2.setStyle("-fx-background-color: #D9D9D9;");

        VBox isisisis = new VBox();
        isisisis.setPrefSize(560, 205);

        Label totalPembayaranLabel = new Label("Total Pembayaran");
        totalPembayaranLabel.setPrefSize(431, 78);
        totalPembayaranLabel.setStyle("-fx-font-family: Calibri; -fx-font-size:50; -fx-font-weight: Bold;");


        totalHarga.setPrefSize(560, 93.33);
        totalHarga.setStyle("-fx-font-family: Calibri; -fx-font-size: 70; -fx-text-fill: #6345DD; -fx-font-weight: Bold;");

        Label berhasil = new Label("Berhasil Bayar!");
        berhasil.setPrefSize(230, 34);
        berhasil.setStyle("-fx-font-family: Calibri; -fx-font-size: 30; -fx-text-fill: #6345DD;");
        berhasil.setVisible(false);

        Button bayarButton = new Button("Bayar");
        bayarButton.setPrefSize(161, 64);
        bayarButton.getStyleClass().add("checkoutButton");

        bayarButton.setOnAction(e -> {
            boolean removing = false;
            for (int i : idTerpilih){
                removing = KeranjangControllers.removeKeranjangByIdAndUserId(i, userId);
            }
            if (removing == true){
                berhasil.setVisible(true);
            } else {
                berhasil.setText("Gagal Bayar");
                berhasil.setVisible(true);
            }

        });
        
        Region spasi20 = new Region();
        spasi20.setPrefSize(20, 20);

        isisisis.getChildren().addAll(totalPembayaranLabel, totalHarga, berhasil);

        layout2.getChildren().addAll(isisisis, bayarButton);

        bagianBawah.getChildren().addAll(layout1, spasi20, layout2);


        awal.getChildren().addAll(navigationBar, spasi20, bagianBawah);
        root.getChildren().add(awal);
        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");
        stage.show();
    }

    
    public void show(int userId, Label totalHarga){
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

        Label profileLabel = new Label("Checkout");
        profileLabel.getStyleClass().add("profileLabel");

        navigationBar.getChildren().addAll(logo, garis, profileLabel);

        VBox bagianBawah = new VBox();
        bagianBawah.setPadding(new Insets(0, 241, 116, 241));

        HBox layout1 = new HBox();
        layout1.setPadding(new Insets(20, 60, 20, 60));
        layout1.setStyle("-fx-background-color: #D9D9D9; -fx-background-radius: 10;");

        VBox layoutAN = new VBox(10);
        layoutAN.setPrefSize(174, 120);

        Label alamatLabel = new Label("Alamat Pengiriman");
        alamatLabel.setPrefSize(258, 26);
        alamatLabel.setStyle("-fx-font-family: Calibri; -fx-font-size: 20; -fx-font-weight: Bold; -fx-text-fill: #6345DD;");

        Label namaPenerima = new Label(users.getNamaPenerima());
        namaPenerima.setStyle("-fx-font-family: Calibri; -fx-font-size: 15; -fx-font-weight: Bold; -fx-text-fill: #000;");
        namaPenerima.setPrefSize(174, 15);

        Label nomorHpPenerima = new Label(users.getNomorHpPenerima());
        nomorHpPenerima.setStyle("-fx-font-family: Calibri; -fx-font-size: 15; -fx-font-weight: Bold; -fx-text-fill: #000;");
        nomorHpPenerima.setPrefSize(174, 15);

        layoutAN.getChildren().addAll(alamatLabel, namaPenerima, nomorHpPenerima);

        Label alamatPenerima = new Label(users.getAlamat());
        alamatPenerima.setPrefSize(461, 76);
        alamatPenerima.setStyle("-fx-font-family: Calibri; -fx-font-size: 20; -fx-font-weight: Bold; -fx-text-fill: #000;");
        alamatPenerima.setWrapText(true);

        layout1.getChildren().addAll(layoutAN, alamatPenerima);

        HBox layout2 = new HBox();
        layout2.setPrefSize(1302, 318);
        layout2.setStyle("-fx-background-color: #D9D9D9;");
        layout2.setPadding(new Insets(userId));

        VBox isisisis = new VBox();
        isisisis.setPrefSize(560, 205);

        Label totalPembayaranLabel = new Label("Total Pembayaran");
        totalPembayaranLabel.setPrefSize(431, 78);
        totalPembayaranLabel.setStyle("-fx-font-family: Calibri; -fx-font-size:50; -fx-font-weight: Bold;");


        totalHarga.setPrefSize(560, 93.33);
        totalHarga.setStyle("-fx-font-family: Calibri; -fx-font-size: 70; -fx-text-fill: #6345DD; -fx-font-weight: Bold;");

        Label berhasil = new Label("Berhasil Bayar!");
        berhasil.setPrefSize(230, 34);
        berhasil.setStyle("-fx-font-family: Calibri; -fx-font-size: 30; -fx-text-fill: #6345DD;");
        berhasil.setVisible(false);

        Button bayarButton = new Button("Bayar");
        bayarButton.setPrefSize(161, 64);
        bayarButton.getStyleClass().add("checkoutButton");

        bayarButton.setOnAction(e -> {
            berhasil.setVisible(true);
        });
        
        Region spasi20 = new Region();
        spasi20.setPrefSize(20, 20);

        isisisis.getChildren().addAll(totalPembayaranLabel, totalHarga, berhasil);

        layout2.getChildren().addAll(isisisis, bayarButton);

        bagianBawah.getChildren().addAll(layout1, spasi20, layout2);


        awal.getChildren().addAll(navigationBar, spasi20, bagianBawah);
        root.getChildren().add(awal);
        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");
        stage.show();
    }
}