package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomePage {
    private Stage stage;

    HomePage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        StackPane root = new StackPane();
        root.getStyleClass().add("homePage");

        VBox awal = new VBox();

        HBox navigationBar = new HBox(25);
        navigationBar.setPadding(new Insets(32));
        navigationBar.setPrefSize(1382, 110);
        navigationBar.getStyleClass().add("navigationBar");
        navigationBar.setAlignment(Pos.CENTER);
        ImageView logo = new ImageView(new Image("styles/logobiasa.png"));

        TextField searchField = new TextField();
        searchField.setPrefSize(658, 35);
        searchField.getStyleClass().add("searchBar");
        searchField.setPromptText("Silahkan mencari disini..");

        ImageView logoKeranjang = new ImageView(new Image("styles/Keranjang.png"));

        HBox username = new HBox(10);
        username.setAlignment(Pos.CENTER);
        ImageView photoProfile = new ImageView(new Image("styles/Profile.JPG"));
        photoProfile.setFitHeight(45);
        photoProfile.setFitWidth(45);
        Label userLabel = new Label("Username here");
        userLabel.getStyleClass().add("usernameText");

        // saat username di klik
        username.setOnMouseClicked(e -> {
            ProfilePage menuProfil = new ProfilePage(stage);
        });
        // Bagian Home - Scroll Pane;
        ScrollPane home = new ScrollPane();
        home.setPrefSize(1382, 626);
        home.setPadding(new Insets(25, 260, 0, 260));
        home.setFitToWidth(true); // Ensure the content width matches the ScrollPane width
        home.setHbarPolicy(ScrollBarPolicy.NEVER);

        VBox isiHome = new VBox(20); // Added spacing between elements
        isiHome.setFillWidth(true);

        HBox bagianBanner = new HBox(20);

        ImageView banner1 = new ImageView(new Image("styles/banner1.png"));
        banner1.setFitWidth(531);
        banner1.setFitHeight(200);
        ImageView banner2 = new ImageView(new Image("styles/banner2.png"));
        banner2.setFitWidth(311); // Changed to setFitWidth
        banner2.setFitHeight(200);
        bagianBanner.setAlignment(Pos.CENTER);

        bagianBanner.getChildren().addAll(banner1, banner2);

        Label produkCategories = new Label("KATEGORI PRODUK");
        produkCategories.getStyleClass().add("userLabel");
        VBox textProdukCat = new VBox(produkCategories);
        textProdukCat.setAlignment(Pos.CENTER);

        HBox kontenKategoriUtama = new HBox(18);

        VBox kontenElektronik = new VBox(6);
        kontenElektronik.getStyleClass().add("konten");
        kontenElektronik.setPrefSize(90, 106);
        ImageView elektronik = new ImageView(new Image("styles/Kategori/1.png"));
        elektronik.setFitWidth(70);
        elektronik.setFitHeight(70);
        Label textElektronik = new Label("Elektronik");
        textElektronik.getStyleClass().add("katText");
        textElektronik.setPrefSize(90, 30);
        kontenElektronik.setAlignment(Pos.CENTER);
        kontenElektronik.setPadding(new Insets(10));
        kontenElektronik.getChildren().addAll(elektronik, textElektronik);

        VBox kontenKecantikan = new VBox(6);
        kontenKecantikan.getStyleClass().add("konten");
        kontenKecantikan.setPrefSize(90, 106);
        ImageView kecantikan = new ImageView(new Image("styles/Kategori/2.png"));
        kecantikan.setFitWidth(70);
        kecantikan.setFitHeight(70);
        Label textKecantikan = new Label("Kecantikan");
        textKecantikan.getStyleClass().add("katText");
        textKecantikan.setPrefSize(90, 30);
        kontenKecantikan.setAlignment(Pos.CENTER);
        kontenKecantikan.setPadding(new Insets(10));
        kontenKecantikan.getChildren().addAll(kecantikan, textKecantikan);

        VBox kontenPerlengkapanRumah = new VBox(6);
        kontenPerlengkapanRumah.getStyleClass().add("konten");
        kontenPerlengkapanRumah.setPrefSize(90, 106);
        ImageView perlengkapanRumah = new ImageView(new Image("styles/Kategori/3.png"));
        perlengkapanRumah.setFitWidth(70);
        perlengkapanRumah.setFitHeight(70);
        Label textPerlengkapanRumah = new Label("Perlengkapan Rumah");
        textPerlengkapanRumah.getStyleClass().add("katText");
        textPerlengkapanRumah.setPrefSize(90, 30);
        kontenPerlengkapanRumah.setAlignment(Pos.CENTER);
        kontenPerlengkapanRumah.setPadding(new Insets(10));
        kontenPerlengkapanRumah.getChildren().addAll(perlengkapanRumah, textPerlengkapanRumah);

        VBox kontenOtomotif = new VBox(6);
        kontenOtomotif.getStyleClass().add("konten");
        kontenOtomotif.setPrefSize(90, 106);
        ImageView otomotif = new ImageView(new Image("styles/Kategori/4.png"));
        otomotif.setFitWidth(70);
        otomotif.setFitHeight(70);
        Label textOtomotif = new Label("Otomotif");
        textOtomotif.getStyleClass().add("katText");
        textOtomotif.setPrefSize(90, 30);
        kontenOtomotif.setAlignment(Pos.CENTER);
        kontenOtomotif.setPadding(new Insets(10));
        kontenOtomotif.getChildren().addAll(otomotif, textOtomotif);
        
        VBox kontenHobiKoleksi = new VBox(6);
        kontenHobiKoleksi.getStyleClass().add("konten");
        kontenHobiKoleksi.setPrefSize(90, 106);
        ImageView hobiKoleksi = new ImageView(new Image("styles/Kategori/5.png"));
        hobiKoleksi.setFitWidth(70);
        hobiKoleksi.setFitHeight(70);
        Label textHobiKoleksi = new Label("Hobi & Koleksi");
        textHobiKoleksi.getStyleClass().add("katText");
        textHobiKoleksi.setPrefSize(90, 30);
        kontenHobiKoleksi.setAlignment(Pos.CENTER);
        kontenHobiKoleksi.setPadding(new Insets(10));
        kontenHobiKoleksi.getChildren().addAll(hobiKoleksi, textHobiKoleksi);
        
        VBox kontenHandphone = new VBox(6);
        kontenHandphone.getStyleClass().add("konten");
        kontenHandphone.setPrefSize(90, 106);
        ImageView handphone = new ImageView(new Image("styles/Kategori/6.png"));
        handphone.setFitWidth(70);
        handphone.setFitHeight(70);
        Label textHandphone = new Label("Handphone");
        textHandphone.getStyleClass().add("katText");
        textHandphone.setPrefSize(90, 30);
        kontenHandphone.setAlignment(Pos.CENTER);
        kontenHandphone.setPadding(new Insets(10));
        kontenHandphone.getChildren().addAll(handphone, textHandphone);
        
        VBox kontenKesehatan = new VBox(6);
        kontenKesehatan.getStyleClass().add("konten");
        kontenKesehatan.setPrefSize(90, 106);
        ImageView kesehatan = new ImageView(new Image("styles/Kategori/7.png"));
        kesehatan.setFitWidth(70);
        kesehatan.setFitHeight(70);
        Label textKesehatan = new Label("Kesehatan");
        textKesehatan.getStyleClass().add("katText");
        textKesehatan.setPrefSize(90, 30);
        kontenKesehatan.setAlignment(Pos.CENTER);
        kontenKesehatan.setPadding(new Insets(10));
        kontenKesehatan.getChildren().addAll(kesehatan, textKesehatan);
        
        VBox kontenPakaian = new VBox(6);
        kontenPakaian.getStyleClass().add("konten");
        kontenPakaian.setPrefSize(90, 106);
        ImageView pakaian = new ImageView(new Image("styles/Kategori/8.png"));
        pakaian.setFitWidth(70);
        pakaian.setFitHeight(70);
        Label textPakaian = new Label("Pakaian Pria dan Wanita");
        textPakaian.getStyleClass().add("katText");
        textPakaian.setPrefSize(90, 30);
        kontenPakaian.setAlignment(Pos.CENTER);
        kontenPakaian.setPadding(new Insets(10));
        kontenPakaian.getChildren().addAll(pakaian, textPakaian);
        
        // kontenKategoriUtama.setAlignment(Pos.BASELINE_CENTER);
        kontenKategoriUtama.getChildren().addAll(kontenElektronik, kontenKecantikan, kontenPerlengkapanRumah, kontenOtomotif, kontenHobiKoleksi, kontenHandphone, kontenKesehatan, kontenPakaian);

        HBox altBannerBox = new HBox(20);
        ImageView banner3 = new ImageView(new Image("styles/banner3.png"));
        banner3.setFitHeight(100);
        banner3.setFitWidth(421);
        ImageView banner4 = new ImageView(new Image("styles/banner4.png"));
        banner4.setFitWidth(421);
        banner4.setFitHeight(100);
        altBannerBox.getChildren().addAll(banner3, banner4);
        altBannerBox.setAlignment(Pos.CENTER);
        
        Line garis = new Line(260, 646, 1122, 646);
        garis.setStrokeWidth(3);
        garis.setStroke(Color.web("#A4A4A4"));

        VBox produkBox = new VBox();
        produkBox.setPadding(new Insets(15));
        produkBox.getStyleClass().add("produkBox");
        produkBox.setMaxSize(130, 186);
        ImageView gambarProduk = new ImageView(new Image("styles/Gambar Produk.png"));
        gambarProduk.setFitWidth(130);
        gambarProduk.setFitHeight(130);
        Label judulProduk = new Label("ASUS ROG Zephyrus G14 GA402NJ Ryzen 7 7735HS RTX3050 512GB SSD 16GB");
        judulProduk.setPrefSize(120,20);
        judulProduk.getStyleClass().add("judulProduk");
        Label hargaProduk = new Label("Rp999.999.999");
        hargaProduk.setPrefSize(120, 14);
        hargaProduk.getStyleClass().add("hargaProduk");
        produkBox.getChildren().addAll(gambarProduk, judulProduk, hargaProduk);




        isiHome.getChildren().addAll(bagianBanner, textProdukCat, kontenKategoriUtama, altBannerBox, garis, produkBox);
        home.setContent(isiHome);
        username.getChildren().addAll(userLabel, photoProfile);
        navigationBar.getChildren().addAll(logo, searchField, logoKeranjang, username);
        awal.getChildren().addAll(navigationBar, home); // Added home to awal

        root.getChildren().add(awal);

        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle("Shopease");
        stage.setScene(scene);
        stage.show();
    }
}
