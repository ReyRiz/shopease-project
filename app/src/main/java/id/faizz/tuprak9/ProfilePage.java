package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class ProfilePage{
    private Stage stage;

    ProfilePage(Stage stage){
        this.stage = stage;
        show();
    }

    public void show(){

        StackPane root = new StackPane();
        root.getStyleClass().add("homePage");

        VBox awal = new VBox();

        HBox navigationBar = new HBox(25);
        navigationBar.setPadding(new Insets(32));
        navigationBar.setPrefSize(1382, 110);
        navigationBar.getStyleClass().add("navigationBar");
        navigationBar.setAlignment(Pos.CENTER);
        ImageView logo = new ImageView(new Image("/styles/logobiasa.png"));

        logo.setOnMouseClicked(e -> {
            HomePage home = new HomePage(stage);
            home.show();
        });

        Line garis = new Line(346, 82, 346, 137);
        garis.setStroke(Color.web("#FFFFFF"));
        garis.setStrokeWidth(2);

        Label profileLabel = new Label("Profile");
        profileLabel.getStyleClass().add("profileLabel");

        navigationBar.getChildren().addAll(logo, garis, profileLabel);

        awal.getChildren().addAll(navigationBar);
        root.getChildren().add(awal);
        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");
        stage.show();
    }
}
