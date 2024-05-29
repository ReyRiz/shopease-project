package id.faizz.tuprak9;

import javafx.application.Application;
import javafx.animation.*;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;

public class HomePage {
    private Stage stage;


    HomePage(Stage stage){
        this.stage = stage;
    }

    public void show(){
        StackPane root = new StackPane();
        root.getStyleClass().add("homePage");

        VBox awal = new VBox();
        awal.setPrefSize(1382, 736);

        HBox navigationBox = new HBox(15);
        navigationBox.setPrefSize(1382, 110);
        navigationBox.getStyleClass().add("navigationBar");
        ImageView logo = new ImageView(new Image("styles/logobiasa.png"));
        navigationBox.setPadding(new Insets(34,20,20,130));
        TextField searchField = new TextField();
        searchField.getStyleClass().add("searchBar");           
        searchField.setPromptText("Search here");
        searchField.setPrefSize(681, 45);
        searchField.setPadding(new Insets(10));
        
        Image krnjg = new Image("styles/Keranjang.png");
        ImageView keranjang = new ImageView(krnjg);

        Region space42 = new Region();
        space42.setPrefWidth(27);
        navigationBox.getChildren().addAll(logo,space42 ,searchField, keranjang); 
        awal.setAlignment(Pos.TOP_CENTER);
        awal.getChildren().addAll(navigationBox);
        root.getChildren().add(awal);
        Scene scene = new Scene(root, 1382, 736);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle("Shopease");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();


    }

}
