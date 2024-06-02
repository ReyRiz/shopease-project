package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import id.faizz.tuprak9.controllers.KeranjangControllers;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Keranjang;
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
        List<Keranjang> listKeranjang = KeranjangControllers.getProdukbyId(userId);

        // Debugging: Print list size
        System.out.println("Number of products in the cart: " + listKeranjang.size());

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
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background-color: #FFF");

        VBox isi = new VBox();
        isi.setPrefSize(1382, 476);
        isi.setPadding(new Insets(30, 260, 30, 260));

        HBox barAtas = new HBox();
        barAtas.setAlignment(Pos.CENTER);
        barAtas.setPadding(new Insets(0, 10, 0, 10));
        barAtas.setPrefSize(862, 40);
        barAtas.setStyle("-fx-background-color: #D9D9D9");

        CheckBox cekListSemua = new CheckBox();
        cekListSemua.setPrefSize(25, 25);

        Label judulProduk = new Label("PRODUK");
        judulProduk.setPrefSize(90, 25);
        judulProduk.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 15;");

        Label hargaSatuan = new Label("HARGA SATUAN");
        hargaSatuan.setPrefSize(136, 25);
        hargaSatuan.setStyle("-fx-text-fill: #000; -fx-font-family: Calibri; -fx-font-size: 15;");

        Region spasi1 = new Region();
        Region spasi2 = new Region();
        HBox.setHgrow(spasi1, Priority.ALWAYS);
        HBox.setHgrow(spasi2, Priority.ALWAYS);

        barAtas.getChildren().addAll(cekListSemua, spasi1, judulProduk, spasi2, hargaSatuan);

        VBox tampungProduk = new VBox(20);
        tampungProduk.setPrefWidth(1382);






        cekListSemua.setOnAction(e -> {
            totalHarga.clear();
            if (cekListSemua.isSelected()) {
                for (Keranjang i : listKeranjang) {
                    totalHarga.add(i.getHarga());
                }
            }
            int total = totalHarga.stream().mapToInt(Integer::intValue).sum();
            // totalLabel.setText("TOTAL: Rp. " + total);
        });

        isi.getChildren().addAll(barAtas, tampungProduk);
        scrollPane.setContent(isi);

        HBox bottomBar = new HBox();
        bottomBar.setPrefSize(1382, 150);
        bottomBar.setStyle("-fx-background-color: #6345DD;");
        bottomBar.setPadding(new Insets(29, 260, 0, 260));


        Region spasian = new Region();
        HBox.setHgrow(spasian, Priority.ALWAYS);

        Button checkoutBtn = new Button("CHECKOUT");

        bottomBar.getChildren().addAll(spasian, checkoutBtn);

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
