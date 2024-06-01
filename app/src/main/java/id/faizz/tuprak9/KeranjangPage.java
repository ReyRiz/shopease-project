package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import id.faizz.tuprak9.controllers.ProdukControllers;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Produk;
import id.faizz.tuprak9.models.Users;

import java.util.List;
import java.util.ArrayList;

public class KeranjangPage {
    private Stage stage;

    KeranjangPage(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) {
        ArrayList<Integer> totalHarga = new ArrayList<>();
        List<Produk> listProduk = ProdukControllers.getAllProduk(userId);
        Users users = UsersControllers.getUserById(userId);
        StackPane root = new StackPane();

        VBox awal = new VBox();

        HBox navigationBar = new HBox(25);
        navigationBar.setPadding(new Insets(32));
        navigationBar.setPrefSize(1382, 110);
        navigationBar.getStyleClass().add("navigationBar");
        navigationBar.setAlignment(Pos.CENTER_LEFT);
        ImageView logo = new ImageView(new Image("styles/logobiasa.png"));

        logo.setOnMouseClicked(e -> {
            if (users.getRole().equals("seller")) {
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

        Label keranjangLabel = new Label("Keranjang");
        keranjangLabel.setStyle("-fx-text-fill: #FFF; -fx-font-family: Calibri; -fx-font-size: 30");

        Region spasi = new Region();
        HBox.setHgrow(spasi, Priority.ALWAYS);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1382, 476);
        scrollPane.setStyle("-fx-background-color: #FFF");
        scrollPane.setPadding(new Insets(30, 260, 30, 260));

        VBox tampungProduk = new VBox(30);

        HBox layout2 = new HBox(); // Ini sebagai bar pertama yang berisi checkbox, dan nama, dan harga
        layout2.setPrefSize(862, 40);
        layout2.setStyle("-fx-background-color: #5C5C5C");
        layout2.setPadding(new Insets(3, 16, 3, 16));

        CheckBox ceklist = new CheckBox(); // checkBox untuk centang seluruh produk di keranjang
        ceklist.setPrefSize(25, 25);
        ceklist.setOnAction(e -> {
            if (ceklist.isSelected()) {
                for (Produk i : listProduk) {
                    totalHarga.add(i.getHarga());
                }
            } else {
                totalHarga.clear();
            }
        });

        Label judulProduk = new Label("NAMA PRODUK");
        judulProduk.setStyle("-fx-font-family: Calibri; -fx-text-fill: #000; -fx-font-size: 15; -fx-text-alignment: center;");
        judulProduk.setPrefSize(90, 25);

        Label satuanLabel = new Label("HARGA SATUAN");
        satuanLabel.setStyle("-fx-font-family: Calibri; -fx-text-fill: #000; -fx-font-size: 15; -fx-text-alignment: center;");
        satuanLabel.setPrefSize(136, 25);

        layout2.getChildren().addAll(ceklist, judulProduk, spasi, satuanLabel);
        tampungProduk.getChildren().add(layout2);

        for (Produk i : listProduk) {
            HBox produkBox = new HBox();
            produkBox.setPrefSize(862, 137);
            produkBox.setPadding(new Insets(30, 58, 30, 26));
            produkBox.setStyle("-fx-background-color: #5C5C5C;");

            CheckBox ceklistPerProduk = new CheckBox(); // checkBox untuk centang seluruh produk di keranjang
            ceklistPerProduk.setPrefSize(25, 25);

            Region spasi48 = new Region();
            spasi48.setPrefSize(48, 0);

            ImageView fotoProduk = new ImageView(new Image("file:" + i.getFoto()));
            fotoProduk.setFitHeight(76);
            fotoProduk.setFitWidth(100);
            fotoProduk.setPreserveRatio(true);

            Region spasi22 = new Region();
            spasi22.setPrefSize(22, 0);

            Label namaProduk = new Label(i.getNama());
            namaProduk.setPrefSize(181, 75);
            namaProduk.setStyle("-fx-font-size: 15; -fx-font-family: Calibri; -fx-text-fill: #000;");

            Region spasi182 = new Region();
            HBox.setHgrow(spasi182, Priority.ALWAYS);

            Label hargaPerProduk = new Label(String.valueOf(i.getHarga()));
            hargaPerProduk.setStyle("-fx-text-fill: #6345DD; -fx-font-family: Calibri; -fx-font-size: 30; -fx-font-weight: bold;");
            hargaPerProduk.setPrefSize(220, 30);

            produkBox.getChildren().addAll(ceklistPerProduk, spasi48, fotoProduk, spasi22, namaProduk, spasi182, hargaPerProduk);

            ceklistPerProduk.setOnAction(e -> {
                if (ceklistPerProduk.isSelected()) {
                    totalHarga.add(i.getHarga());
                } else {
                    totalHarga.remove(Integer.valueOf(i.getHarga()));
                }
            });

            tampungProduk.getChildren().add(produkBox);
        }

        scrollPane.setContent(tampungProduk);

        HBox bottomBar = new HBox();
        bottomBar.setPrefSize(1382, 150);

        navigationBar.getChildren().addAll(logo, garis, keranjangLabel, spasi);
        awal.getChildren().addAll(navigationBar, scrollPane, bottomBar);
        root.getChildren().addAll(awal);
        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");
        stage.getIcons().add(new Image("styles/AppIcon.png"));
        stage.show();
    }
}
