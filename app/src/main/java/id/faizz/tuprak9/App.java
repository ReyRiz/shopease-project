package id.faizz.tuprak9;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("styles/oregano/Oregano-Regular.ttf"), 12);

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1382, 736);

        VBox awal = new VBox();
        awal.setAlignment(Pos.CENTER);

        Image logoAwal = new Image("styles/logoLoading.png");
        ImageView nampilin = new ImageView(logoAwal);
        awal.getChildren().add(nampilin);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), nampilin);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        LoginPage loginPage = new LoginPage(stage);
        fadeTransition.setOnFinished(event -> {
            loginPage.showPage();
        });

        root.getChildren().addAll(awal);
        root.getStyleClass().add("rootPane");
        scene.getStylesheets().add("styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");
        stage.getIcons().add(new Image("styles/AppIcon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
