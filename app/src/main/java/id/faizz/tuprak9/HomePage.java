package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Label;
import id.faizz.tuprak9.controllers.ProdukControllers;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Produk;
import id.faizz.tuprak9.models.Users;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class HomePage {
    private Stage stage;

    HomePage(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) {
        List<Produk> produks = ProdukControllers.getAllProduk();
        Users users = UsersControllers.getUserById(userId);

        StackPane root = new StackPane();
        root.getStyleClass().add("homePage");

        VBox awal = new VBox();

        HBox navigationBar = new HBox(25);
        navigationBar.setPadding(new Insets(32));
        navigationBar.setPrefSize(1382, 110);
        navigationBar.getStyleClass().add("navigationBar");
        navigationBar.setAlignment(Pos.CENTER);
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

        TextField searchField = new TextField();
        searchField.setPrefSize(658, 35);
        searchField.getStyleClass().add("searchBar");
        searchField.setPromptText("Silahkan mencari disini..");

        ImageView logoKeranjang = new ImageView(new Image("styles/Keranjang.png"));

        HBox username = new HBox(10);
        username.setAlignment(Pos.CENTER);
        ImageView photoProfile;
        try {
            photoProfile = new ImageView(new Image("file:" + users.getfoto()));
        } catch (Exception e) {
            photoProfile = new ImageView(new Image("styles/contohPP.png"));
        }
        photoProfile.setFitHeight(45);
        photoProfile.setFitWidth(45);
        photoProfile.getStyleClass().add("photoProfile");
        Label userLabel = new Label(users.getUsername());
        userLabel.getStyleClass().add("usernameText");

        username.setOnMouseClicked(e -> {
            ProfilePage menuProfil = new ProfilePage(stage);
            menuProfil.show(userId);
        });

        ScrollPane home = new ScrollPane();
        home.setPrefSize(1382, 626);
        home.setPadding(new Insets(25, 260, 0, 260));
        home.setFitToWidth(true);
        home.setHbarPolicy(ScrollBarPolicy.NEVER);

        VBox isiHome = new VBox(20);
        isiHome.setFillWidth(true);

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

        FlowPane produkContainer = new FlowPane();
        produkContainer.setPadding(new Insets(20));
        produkContainer.setHgap(20);
        produkContainer.setVgap(20);
        produkContainer.setAlignment(Pos.TOP_LEFT);

        isiHome.getChildren().addAll(altBannerBox, garis);
        int counter = 0;

        for (Produk i : produks) {
            VBox produkBox = new VBox();
            produkBox.setPadding(new Insets(15));
            produkBox.getStyleClass().add("produkBox");
            produkBox.setMaxSize(130, 186);
            try {
                System.out.println("Loading product image from: " + i.getFoto());
                Image x = new Image("file:" + i.getFoto());
                
                ImageView image = new ImageView(x);
                image.setFitWidth(130);
                image.setFitHeight(130);
                Label judulProduk = new Label(i.getNama());
                judulProduk.setPrefSize(120, 20);
                judulProduk.getStyleClass().add("judulProduk");
                Label hargaProduk = new Label(String.valueOf(i.getHarga()));
                hargaProduk.setPrefSize(120, 14);
                hargaProduk.getStyleClass().add("hargaProduk");
                produkBox.getChildren().addAll(image, judulProduk, hargaProduk);
                produkContainer.getChildren().add(produkBox);

                counter++;
                if (counter == 4) {
                    counter = 0;
                    isiHome.getChildren().add(produkContainer);
                    produkContainer = new FlowPane();
                    produkContainer.setPadding(new Insets(20));
                    produkContainer.setHgap(20);
                    produkContainer.setVgap(20);
                    produkContainer.setAlignment(Pos.TOP_LEFT);
                }
            } catch (Exception e) {
                System.out.println("Error memuat Gambar");
            }
        }

        if (counter > 0) {
            isiHome.getChildren().add(produkContainer);
        }

        home.setContent(isiHome);
        username.getChildren().addAll(userLabel, photoProfile);
        navigationBar.getChildren().addAll(logo, searchField, logoKeranjang, username);
        awal.getChildren().addAll(navigationBar, home);

        root.getChildren().add(awal);

        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle("Shopease");
        stage.setScene(scene);
        stage.show();
    }
}
