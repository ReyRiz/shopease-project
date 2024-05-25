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


public class LoginPage {
    private Stage stage;
    LoginPage(Stage stage){
        this.stage = stage;
    }

    public void showPage(){
        StackPane root = new StackPane();
        stage.setMaximized(true);
        VBox mainPanel = new VBox();
        mainPanel.setAlignment(Pos.TOP_CENTER);

        Image logox = new Image("styles/logobiasa.png");
        ImageView logo = new ImageView(logox);

        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(26, 28, 45, 29));
        loginBox.setPrefSize(300, 365);
        loginBox.getStyleClass().add("loginBox");
        Label textLogin = new Label("Log-in");
        textLogin.getStyleClass().add("loginText");
        Label usernamText = new Label("Username");
        TextField usernamField = new TextField();
        usernamField.setMinSize(244, 30);
        usernamField.getStyleClass().add("fieldni");
        Label pwText = new Label("Password");
        PasswordField pwField = new PasswordField();
        pwField.setMinSize(244, 30);
        pwField.getStyleClass().add("fieldni");

        Button loginBtn = new Button("Login");
        loginBtn.setMinSize(250, 35);
        loginBtn.getStyleClass().add("loginbtn");
        // loginBtn.setAlignment(Pos.CENTER);


        HBox garisPenggunaBaru = new HBox(10);
        Line garis1 = new Line(0, 0, 71, 0);
        garis1.setStrokeWidth(1);
        Text textPengguna = new Text("Pengguna Baru?");
        Line garis2 = new Line(0, 0, 71, 0);
        garis2.setStrokeWidth(1);
        garisPenggunaBaru.getChildren().addAll(garis1, textPengguna, garis2);
        garisPenggunaBaru.setAlignment(Pos.CENTER);


        Button regBtn = new Button("Register");
        regBtn.setMinSize(250, 35);
        regBtn.getStyleClass().add("regBtn");


        loginBox.getChildren().addAll(textLogin, usernamText, usernamField, pwText, pwField, loginBtn, garisPenggunaBaru, regBtn);
        Region space45 = new Region();
        space45.setPrefSize(0, 45);

        mainPanel.getChildren().addAll(logo,space45, loginBox);

        root.getChildren().addAll(mainPanel);
        mainPanel.setPadding(new Insets(30, 0, 0, 0));
        Scene scene = new Scene(root, 1382, 736);
        root.setPadding(new Insets(30, 540, 30, 542));
        root.getStyleClass().add("rootPane");
        scene.getStylesheets().add("styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");

        loginBtn.setOnAction(e -> {
            String username = usernamField.getText();
            String password = pwField.getText();

        });

        regBtn.setOnAction(e -> {
            RegisterPage regPage = new RegisterPage(stage);
            regPage.showPage();
        });

        // stage.setFullScreen(true);
        stage.show();
    }
}
