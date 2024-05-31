package id.faizz.tuprak9;

import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Users;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class SellerPage {
    private Stage stage;

    SellerPage(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) {
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

        TextField searchField = new TextField();
        searchField.setPrefSize(658, 35);
        searchField.getStyleClass().add("searchBar");
        searchField.setPromptText("Silahkan mencari disini..");

        HBox username = new HBox(10);
        username.setAlignment(Pos.CENTER);
        ImageView photoProfile;
        try {
            photoProfile = new ImageView(new Image("file:" + users.getfoto()));
        } catch (Exception e) {
            photoProfile = new ImageView(new Image("styles/Profile.JPG"));  
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
        // Bagian Home - Scroll Pane;
        ScrollPane home = new ScrollPane();
        home.setPrefSize(1382, 626);
        home.setPadding(new Insets(25, 260, 0, 260));
        home.setFitToWidth(true); // Ensure the content width matches the ScrollPane width
        home.setHbarPolicy(ScrollBarPolicy.NEVER);

        VBox isiHome = new VBox(20); // Added spacing between elements
        isiHome.setFillWidth(true);
        
        home.setContent(isiHome);
        username.getChildren().addAll(userLabel, photoProfile);
        navigationBar.getChildren().addAll(logo, searchField, username);
        awal.getChildren().addAll(navigationBar, home); // Added home to awal

        root.getChildren().add(awal);

        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle("Shopease");
        stage.setScene(scene);
        stage.show();
    }
}
