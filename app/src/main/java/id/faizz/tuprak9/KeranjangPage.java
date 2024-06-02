package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Node;
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
import javafx.scene.control.Alert;
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
        int hargaSEMUA = 0;
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

        VBox isi = new VBox(30);
        isi.setPrefSize(1382, 476);
        isi.setPadding(new Insets(30, 260, 30, 260));

        HBox barAtas = new HBox();
        barAtas.setAlignment(Pos.CENTER);
        barAtas.setPadding(new Insets(0, 20, 0,20));
        barAtas.setPrefSize(862, 60);
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

        
        Label hargaTotal = new Label();


        for (Keranjang i : listKeranjang){
            HBox produkBox = new HBox();
            produkBox.setPrefSize(862, 137);
            produkBox.setStyle("-fx-background-color: #D9D9D9;");
            produkBox.setPadding(new Insets(30, 26, 30, 26));

            CheckBox cekListPerProduk = new CheckBox();
            cekListPerProduk.setPrefSize(25, 25);

            Region spasi48 = new Region();
            spasi48.setPrefSize(48, 0);

            cekListPerProduk.setOnAction(e -> {
                if (cekListPerProduk.isSelected()){
                    totalHarga.add(i.getHarga());
                } else {
                    totalHarga.remove((Integer) i.getHarga()); // Hapus harga produk dari totalHarga
                }
                // Perbarui label hargaTotal
                int total = totalHarga.stream().mapToInt(Integer::intValue).sum();
                hargaTotal.setText("Rp." + String.valueOf(total));
            });
            

            ImageView fotoProduk = new ImageView(new Image("file:" + i.getFoto()));
            fotoProduk.setPreserveRatio(true);
            fotoProduk.setFitHeight(76);
            fotoProduk.setFitWidth(100);

            Region spasi22 = new Region();
            spasi22.setPrefSize(22, 0);

            Label namaProduk = new Label(i.getNama());
            namaProduk.setPrefSize(181, 75);
            namaProduk.setWrapText(true);

            Region spasiANJAY = new Region();
            HBox.setHgrow(spasiANJAY, Priority.ALWAYS);

            Label hargaProduk = new Label( "Rp." + String.valueOf(i.getHarga()));
            hargaProduk.setPrefSize(220, 30);
            hargaProduk.setStyle("-fx-text-fill: #6345DD; -fx-font-family: Calibri; -fx-font-size: 30; -fx-font-weight: Bold;");

            produkBox.getChildren().addAll(cekListPerProduk, spasi48, fotoProduk, spasi22, namaProduk,spasiANJAY, hargaProduk);
            produkBox.setAlignment(Pos.CENTER);
            tampungProduk.getChildren().add(produkBox);
        }


        cekListSemua.setOnAction(e -> {
            if (cekListSemua.isSelected()) {
                // Tandai semua CheckBox produk
                for (Node node : tampungProduk.getChildren()) {
                    HBox produkBox = (HBox) node;
                    CheckBox cekListPerProduk = (CheckBox) produkBox.getChildren().get(0);
                    cekListPerProduk.setSelected(true);
                }
                // Tambahkan semua harga produk ke totalHarga
                totalHarga.clear();
                for (Keranjang i : listKeranjang) {
                    totalHarga.add(i.getHarga());
                }
            } else {
                // Batalkan penandaan semua CheckBox produk
                for (Node node : tampungProduk.getChildren()) {
                    HBox produkBox = (HBox) node;
                    CheckBox cekListPerProduk = (CheckBox) produkBox.getChildren().get(0);
                    cekListPerProduk.setSelected(false);
                }
                // Hapus semua harga produk dari totalHarga
                totalHarga.clear();
            }
            // Perbarui label hargaTotal
            int total = totalHarga.stream().mapToInt(Integer::intValue).sum();
            hargaTotal.setText("Rp." + String.valueOf(total));
        });
        
        


        Region spasi20 = new Region();
        spasi20.setPrefSize(0, 20);

        isi.getChildren().addAll(barAtas, tampungProduk);
        scrollPane.setContent(isi);

        HBox bottomBar = new HBox();
        bottomBar.setPrefSize(1382, 150);
        bottomBar.setStyle("-fx-background-color: #6345DD;");
        bottomBar.setPadding(new Insets(29, 260, 0, 260));

        VBox hargaBox = new VBox(5);

        Label hargaLabel = new Label("TOTAL: ");        
        hargaLabel.setStyle("-fx-font-family: Calibri; -fx-text-fill: #FFF; -fx-font-weight: Bold; -fx-font-size: 35");

        for (int i : totalHarga){
            hargaSEMUA += i;
        }
        hargaTotal.setText("Rp." + String.valueOf(hargaSEMUA));
        hargaTotal.setStyle("-fx-font-family: Calibri; -fx-text-fill: #FFF; -fx-font-weight: Bold; -fx-font-size: 35");


        hargaBox.getChildren().addAll(hargaLabel ,hargaTotal);


        Region spasian = new Region();
        HBox.setHgrow(spasian, Priority.ALWAYS);

        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setPrefSize(200, 70);
        checkoutBtn.getStyleClass().add("checkoutButton");

        checkoutBtn.setOnAction(e -> { 
            if (hargaTotal.getText().equals("Rp.0")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(null);
                alert.setContentText("TIDAK ADA YANG DIPILIH");
                alert.showAndWait();
            } else {
                // Tampilkan halaman checkout
                CheckoutPage checkoutPage = new CheckoutPage(stage);
                checkoutPage.show(userId, hargaTotal);
            }
        });

        bottomBar.getChildren().addAll(hargaBox, spasian, checkoutBtn);

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
