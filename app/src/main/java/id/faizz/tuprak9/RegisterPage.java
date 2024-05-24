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


public class RegisterPage {
    private Stage Stage;

    RegisterPage(Stage stage){
        this.Stage = stage;
    }

    public void showPage(){
        StackPane root = new StackPane();
        VBox mainPanel = new VBox(10);

        Image images = new Image("styles/logobiasa.png");
        ImageView logo = new ImageView(images);

        HBox layoutKedua = new HBox(10);
        layoutKedua.setAlignment(Pos.CENTER);
        Image imagex = new Image("styles/slogan.png");
        ImageView slogan = new ImageView(imagex);

        Region space177 = new Region();
        space177.setPrefWidth(177);


        VBox allField = new VBox(7);
        VBox regWindow = new VBox(15);
        regWindow.setPadding(new Insets(25, 28, 25, 28));
        regWindow.setPrefSize(300, 365);
        regWindow.getStyleClass().add("loginBox");
        Label regText = new Label("Register");
        regText.getStyleClass().add("regText");
        Label usrText = new Label("Username");
        TextField usrField = new TextField();
        usrField.getStyleClass().add("fieldni");
        Label pwLabel = new Label("Password");
        TextField pwField = new TextField();
        pwField.getStyleClass().add("fieldni");
        Label confirmPwLabel = new Label("Confirm Password");
        TextField confirmPwField = new TextField();
        confirmPwField.getStyleClass().add("fieldni");

        Button regButton = new Button("Register");
        regButton.getStyleClass().add("loginbtn");
        regButton.setPrefSize(244, 40);
        Label punyaAkun = new Label("Sudah punya akun? masuk disini.");

        punyaAkun.setOnMouseClicked(e -> {
            LoginPage balik = new LoginPage(Stage);
            balik.showPage();
        });

        allField.getChildren().addAll(usrText, usrField, pwLabel, pwField, confirmPwLabel, confirmPwField);
        regWindow.setAlignment(Pos.CENTER);
        regWindow.getChildren().addAll(regText, allField, regButton, punyaAkun);
        layoutKedua.getChildren().addAll(slogan, space177, regWindow);
        mainPanel.getChildren().addAll(logo, layoutKedua);

        mainPanel.setAlignment(Pos.BASELINE_CENTER);
        root.getChildren().addAll(mainPanel);
        Scene scene = new Scene(root, 1382, 736);
        root.setPadding(new Insets(30));
        root.getStyleClass().add("rootPane");
        scene.getStylesheets().add("styles/styles.css");
        Stage.setScene(scene);
        Stage.setTitle("Shopease");
        Stage.show();
    }


}
