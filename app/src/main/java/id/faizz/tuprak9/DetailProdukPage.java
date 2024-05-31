package id.faizz.tuprak9;


import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Users;
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

public class DetailProdukPage {
    private Stage stage;

    DetailProdukPage(Stage stage){
        this.stage = stage;
    }

    public void show(int userId){
        Users users = UsersControllers.getUserById(userId);
        StackPane root = new StackPane();
        root.getStyleClass().add("homePage");


        VBox awal = new VBox();

        HBox navigationBar = new HBox(25);
        navigationBar.setPadding(new Insets(32));
        navigationBar.setPrefSize(1382, 110);
        navigationBar.getStyleClass().add("navigationBar");
        navigationBar.setAlignment(Pos.CENTER_LEFT);
        ImageView logo = new ImageView(new Image("/styles/logobiasa.png"));
    }
}
