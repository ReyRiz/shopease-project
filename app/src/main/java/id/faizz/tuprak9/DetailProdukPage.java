package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import id.faizz.tuprak9.controllers.KeranjangControllers;
import id.faizz.tuprak9.controllers.ProdukControllers;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Keranjang;
import id.faizz.tuprak9.models.Produk;
import id.faizz.tuprak9.models.Users;

public class DetailProdukPage {
    private Stage stage;

    DetailProdukPage(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId, Produk produk) {
        Users users = UsersControllers.getUserById(userId);
        StackPane root = new StackPane();
        root.getStyleClass().add("detailProdukPage");

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

        
        navigationBar.getChildren().addAll(logo);


        //Layout Kedua
        VBox layout2 = new VBox();  //Ini untuk layout dibawahnya
        layout2.setPrefSize(1382, 626);
        layout2.setStyle("-fx-background-color: #D9D9D9");
        layout2.setPadding(new Insets(73, 65, 73, 65));
        
        HBox windowProduk = new HBox();
        windowProduk.setPrefSize(1252, 480);
        windowProduk.getStyleClass().add("homePage");

        ImageView imageProduk = new ImageView(new Image("file:" + produk.getFoto()));
        imageProduk.setFitWidth(480);
        imageProduk.setFitHeight(480);
        imageProduk.setPreserveRatio(true);

        VBox bagianProduk = new VBox();
        bagianProduk.setPrefSize(772, 480);
        bagianProduk.setPadding(new Insets(40, 40, 60, 40));
        bagianProduk.getStyleClass().add("homePage");

        Label judulProduk = new Label(produk.getNama());
        judulProduk.setAlignment(Pos.CENTER_LEFT);
        judulProduk.setPrefSize(705, 35);;
        judulProduk.setStyle("-fx-font-family: Calibri; -fx-text-fill: #000; -fx-font-weight: bold; -fx-font-size: 40;");

        Label deskripsiProduk = new Label("Deskripsi Produk");
        deskripsiProduk.setAlignment(Pos.CENTER_LEFT);
        deskripsiProduk.setPrefSize(705, 50);
        deskripsiProduk.setStyle("-fx-font-family: Calibri; -fx-text-fill: #000; -fx-font-size: 20;");


        Label deskripsi = new Label();
        deskripsi.setAlignment(Pos.CENTER_LEFT);
        deskripsi.setPrefSize(500, 183);
        deskripsi.setText(produk.getDeskripsi());
        deskripsi.setStyle("-fx-font-family: Calibri; -fx-text-fill: #000; -fx-font-size: 20;");
        
        Label harga = new Label();
        harga.setAlignment(Pos.CENTER_LEFT);
        harga.setPrefSize(400, 70);
        harga.setText("Rp." + String.valueOf(produk.getHarga()));
        harga.setStyle("-fx-font-family: Calibri; -fx-font-size: 40; -fx-font-weight: bold; -fx-text-fill: #6345DD;");

        HBox buttonDua = new HBox(36);
        buttonDua.setPrefSize(400, 45);

        Button keranjang = new Button("TAMBAH KERANJANG");
        keranjang.setPrefSize(182, 45);
        keranjang.getStyleClass().add("buttonFordetail");

        keranjang.setOnAction(e -> {
            boolean berhasilTambah = KeranjangControllers.tambahKeranjang(userId, produk.getNama(), produk.getFoto(), produk.getHarga(), userId);
            if (berhasilTambah) {
                StackPane dimmedBackground = new StackPane();
                dimmedBackground.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
                dimmedBackground.setPrefSize(1382, 736);
                dimmedBackground.setVisible(false);

                Label floatingMessage = new Label("Berhasil Disimpan di Keranjang");
                floatingMessage.setStyle("-fx-background-color: #6345DD; -fx-background-radius: 5; -fx-text-fill: #FFF; -fx-font-size: 20; -fx-padding: 10px; -fx-font-family: Calibri; -fx-font-weight:Bold;");
                StackPane.setAlignment(floatingMessage, Pos.CENTER);
                floatingMessage.setVisible(false);

                root.getChildren().add(dimmedBackground);
                root.getChildren().add(floatingMessage);

                dimmedBackground.setVisible(true);
                floatingMessage.setVisible(true);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), dimmedBackground);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);

                PauseTransition pause = new PauseTransition(Duration.seconds(2));

                FadeTransition fadeOut = new FadeTransition(Duration.millis(200), dimmedBackground);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);

                fadeIn.setOnFinished(event -> pause.play());
                pause.setOnFinished(event -> fadeOut.play());
                fadeOut.setOnFinished(event -> {
                    floatingMessage.setVisible(false);
                    dimmedBackground.setVisible(false);
                    root.getChildren().removeAll(dimmedBackground, floatingMessage);
                });

                fadeIn.play();
            } else {
                System.out.println("Gagal tambah keranjang");
            }
        });

        Button bayar = new Button("BAYAR LANGSUNG");
        bayar.setPrefSize(182, 45);
        bayar.getStyleClass().add("buttonFordetail");

        buttonDua.getChildren().addAll(keranjang, bayar);
        bagianProduk.getChildren().addAll(judulProduk, deskripsiProduk, deskripsi, harga, buttonDua);
        windowProduk.getChildren().addAll(imageProduk, bagianProduk);
        layout2.getChildren().addAll(windowProduk);
        awal.getChildren().addAll(navigationBar, layout2);
        root.getChildren().addAll(awal);

        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle("Detail Produk");
        stage.setScene(scene);
        stage.show();
    }
}
