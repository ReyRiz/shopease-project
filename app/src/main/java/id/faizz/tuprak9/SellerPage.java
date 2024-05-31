package id.faizz.tuprak9;

import java.util.List;

import id.faizz.tuprak9.controllers.ProdukControllers;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Produk;
import id.faizz.tuprak9.models.Users;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class SellerPage {
    private Stage stage;

    SellerPage(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) {
        
        List<Produk> produk2 = ProdukControllers.getAllProduk(userId);
        Users users = UsersControllers.getUserById(userId);

        StackPane root = new StackPane();
        root.getStyleClass().add("homePage");

        VBox awal = new VBox();

        HBox navigationBar = new HBox(25);
        navigationBar.setPadding(new Insets(32));
        navigationBar.setPrefSize(1382, 110);
        navigationBar.getStyleClass().add("navigationBar");
        navigationBar.setAlignment(Pos.CENTER);
        ImageView logo = new ImageView(new Image("styles/Seller.png"));

        HBox username = new HBox(10);
        username.setAlignment(Pos.CENTER);
        ImageView photoProfile;
        try {
            System.out.println("Loading user profile image from: file:" + users.getfoto());
            photoProfile = new ImageView(new Image("file:" + users.getfoto()));
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to debug the issue
            System.out.println("Failed to load user profile image, using fallback image.");
            photoProfile = new ImageView(new Image("styles/contohPP.png"));
        }
        photoProfile.setFitHeight(45);
        photoProfile.setFitWidth(45);
        photoProfile.getStyleClass().add("photoProfile");
        Label userLabel = new Label(users.getUsername());
        userLabel.getStyleClass().add("usernameText");

        // saat username di klik
        username.setOnMouseClicked(e -> {
            ProfilePage menuProfil = new ProfilePage(stage);
            menuProfil.show(userId);
        });

        Region spasi = new Region();
        spasi.setPrefSize(700, 0);
        // Bagian Home - Scroll Pane;
        ScrollPane home = new ScrollPane();
        home.setPrefSize(1382, 626);
        home.setFitToWidth(true); // Ensure the content width matches the ScrollPane width
        home.setHbarPolicy(ScrollBarPolicy.NEVER);

        VBox isiHome = new VBox(20); // Added spacing between elements
        isiHome.setFillWidth(true);
        isiHome.setPadding(new Insets(120));

        VBox layout1 = new VBox(12);
        layout1.setPrefSize(800, 200);
        HBox searchBox = new HBox(20);
        layout1.setPadding(new Insets(30));
        TextField searchField = new TextField();
        searchField.getStyleClass().add("searchUntukSeller");
        searchField.setPrefSize(650, 40);

        Button cari = new Button("CARI");
        cari.getStyleClass().add("buttonSimpan");
        cari.setPrefSize(110, 40);

        
        VBox kumpulanProduk = new VBox(20);
        kumpulanProduk.setPadding(new Insets(20));
        kumpulanProduk.getStyleClass().add("searchUntukSeller");
        kumpulanProduk.setMinSize(800, 4000);

        searchBox.getChildren().addAll(searchField, cari);

        cari.setOnAction(e -> {
            kumpulanProduk.getChildren().clear();
            String dicari = searchField.getText();
            for (Produk i : produk2){
                if (i.getNama().equals(dicari)){

                    HBox produk = new HBox(10);
                    produk.setPadding(new Insets(20));
                    produk.setPrefSize(729, 150);
                    produk.getStyleClass().add("produkContainer");
        
                    VBox imageContainer = new VBox();
                    try {
                        System.out.println("Loading product image from: " + i.getFoto());
                        Image x = new Image("file:" + i.getFoto());
                        ImageView image = new ImageView(x);
                        image.setFitWidth(100);
                        image.setFitHeight(100);
                        imageContainer.setPrefSize(100, 100);
                        imageContainer.getChildren().add(image);
                    } catch (Exception error) {
                        error.printStackTrace(); // Print the stack trace to debug the issue
                        System.out.println("Failed to load product image, using fallback image.");
                        ImageView image = new ImageView(new Image("styles/eye-slash.png"));
                        image.setFitWidth(100);
                        image.setFitHeight(100);
                        imageContainer.setPrefSize(100, 100);
                        imageContainer.getChildren().add(image);
                    }
        
                    VBox namaProdukDanHarga = new VBox(40);
                    Label namaProduk = new Label(i.getNama());
                    namaProduk.setStyle("-fx-font-family: Calibri; -fx-font-size: 25; -fx-text-fill: #000;");
        
                    Label hargaProduk = new Label("Rp. " + String.valueOf(i.getHarga()));
                    hargaProduk.setStyle("-fx-font-family: Calibri; -fx-font-size: 30; -fx-text-fill: #6345DD; -fx-font-weight: bold;");
        
                    namaProdukDanHarga.getChildren().addAll(namaProduk, hargaProduk);
        
                    Region spasii = new Region();
                    spasii.setPrefWidth(230);
                    produk.getChildren().addAll(imageContainer, namaProdukDanHarga, spasii);
                    kumpulanProduk.getChildren().add(produk);                    
                }
            }

        });

        Label tambahoredit = new Label("Tambah/edit Produk");
        tambahoredit.getStyleClass().add("tambahoredit");

        Button tambah = new Button("TAMBAH PRODUK");
        tambah.getStyleClass().add("buttonSimpan");
        tambah.setPrefSize(240, 50);

        tambah.setOnAction(e -> {
            // Masuk ke halama tambah Produk
            TambahProduk tambahPage = new TambahProduk(stage);
            tambahPage.show(userId);
        });

        layout1.getChildren().addAll(searchBox, tambahoredit, tambah);
        layout1.getStyleClass().add("layout1Seller");


        for (Produk i : produk2) {
            HBox produk = new HBox(10);
            produk.setPadding(new Insets(20));
            produk.setPrefSize(729, 150);
            produk.getStyleClass().add("produkContainer");

            VBox imageContainer = new VBox();
            try {
                System.out.println("Loading product image from: " + i.getFoto());
                Image x = new Image("file:" + i.getFoto());
                ImageView image = new ImageView(x);
                image.setFitWidth(100);
                image.setFitHeight(100);
                imageContainer.setPrefSize(100, 100);
                imageContainer.getChildren().add(image);
            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace to debug the issue
                System.out.println("Failed to load product image, using fallback image.");
                ImageView image = new ImageView(new Image("styles/eye-slash.png"));
                image.setFitWidth(100);
                image.setFitHeight(100);
                imageContainer.setPrefSize(100, 100);
                imageContainer.getChildren().add(image);
            }

            VBox namaProdukDanHarga = new VBox(40);
            Label namaProduk = new Label(i.getNama());
            namaProduk.setStyle("-fx-font-family: Calibri; -fx-font-size: 25; -fx-text-fill: #000;");

            Label hargaProduk = new Label("Rp. " + String.valueOf(i.getHarga()));
            hargaProduk.setStyle("-fx-font-family: Calibri; -fx-font-size: 30; -fx-text-fill: #6345DD; -fx-font-weight: bold;");

            namaProdukDanHarga.getChildren().addAll(namaProduk, hargaProduk);

            VBox buttonEdit1 = new VBox();
            buttonEdit1.setAlignment(Pos.BOTTOM_RIGHT);

            Button buttonEdit = new Button("EDIT PRODUK");
            buttonEdit.setPrefSize(200, 40);
            buttonEdit.getStyleClass().add("buttonSimpan");

            buttonEdit.setOnAction(e -> {
                // Masuk Ke Halaman edit Produk
                editPage editPage = new editPage(stage);
                editPage.show(userId);
            });

            Region spasii = new Region();
            spasii.setPrefWidth(230);
            buttonEdit1.getChildren().add(buttonEdit);
            produk.getChildren().addAll(imageContainer, namaProdukDanHarga, spasii, buttonEdit1);
            kumpulanProduk.getChildren().add(produk);
        }

        isiHome.getChildren().addAll(layout1, kumpulanProduk);
        home.setPadding(new Insets(0, 150, 0, 150));
        home.setContent(isiHome);
        username.getChildren().addAll(userLabel, photoProfile);
        navigationBar.getChildren().addAll(logo, spasi, username);
        awal.getChildren().addAll(navigationBar, home); // Added home to awal

        root.getChildren().add(awal);

        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle("Shopease");
        stage.setScene(scene);
        stage.show();
    }
}
