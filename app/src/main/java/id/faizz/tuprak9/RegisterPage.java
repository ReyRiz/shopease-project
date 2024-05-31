package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.RegistrationResult;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegisterPage {
    private Stage stage;

    public RegisterPage(Stage stage) {
        this.stage = stage;
    }

    public void showPage() {
        StackPane root = new StackPane();
        VBox mainPanel = new VBox(10);

        Image imageLogo = new Image("styles/logobiasa.png");
        ImageView logo = new ImageView(imageLogo);

        HBox layoutKedua = new HBox();
        layoutKedua.setAlignment(Pos.CENTER);

        Image imageSlogan = new Image("styles/imgbg.png");
        ImageView slogan = new ImageView(imageSlogan);
        slogan.getStyleClass().add("imgBg");

        VBox allField = new VBox(7);
        VBox regWindow = new VBox(15);
        regWindow.setPadding(new Insets(25, 28, 25, 28));
        regWindow.setPrefSize(300, 365);
        regWindow.getStyleClass().add("registerBox");

        Label regText = new Label("Register");
        regText.getStyleClass().add("regText");

        Label usrText = new Label("Username");
        TextField usrField = new TextField();
        usrField.getStyleClass().add("fieldni");

        Label pwLabel = new Label("Password");
        PasswordField pwField = new PasswordField();
        pwField.getStyleClass().add("fieldni");

        Label confirmPwLabel = new Label("Confirm Password");
        PasswordField confirmPwField = new PasswordField();
        confirmPwField.getStyleClass().add("fieldni");

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("messageLabel");
        messageLabel.setVisible(false);

        Button regButton = new Button("Register");
        regButton.getStyleClass().add("regBtn");
        regButton.setPrefSize(244, 40);

        Label punyaAkun = new Label("Sudah punya akun? Masuk disini.");

        punyaAkun.setOnMouseClicked(e -> {
            LoginPage balik = new LoginPage(stage);
            balik.showPage();
        });

        regButton.setOnAction(e -> {
    String username = usrField.getText();
    String password = pwField.getText();
    String konfirPassword = confirmPwField.getText();

    if (!password.equals(konfirPassword) || username.isEmpty()) {
        messageLabel.setText("Gagal Membuat Akun");
        messageLabel.setTextFill(Color.web("#F65353"));
    } else {
        RegistrationResult result = UsersControllers.register(username, password);

        if (result == RegistrationResult.SUCCESS) {
            messageLabel.setText("Berhasil Membuat Akun");
            messageLabel.setTextFill(Color.web("#6345DD"));
            PauseTransition pause = new PauseTransition(Duration.seconds(4));
            pause.setOnFinished(event -> {
                LoginPage loginPage = new LoginPage(stage);
                loginPage.showPage();
            });
            pause.play();
        } else if (result == RegistrationResult.USERNAME_TAKEN) {
            messageLabel.setText("Username telah dipakai");
            messageLabel.setTextFill(Color.web("#F65353"));
        } else {
            messageLabel.setText("Gagal Membuat Akun");
            messageLabel.setTextFill(Color.web("#F65353"));
        }
    }
    messageLabel.setVisible(true);
    PauseTransition pause = new PauseTransition(Duration.seconds(3));
    pause.setOnFinished(event -> messageLabel.setVisible(false));
    pause.play();
});



        Region space46 = new Region();
        space46.setPrefHeight(46);

        allField.getChildren().addAll(usrText, usrField, pwLabel, pwField, confirmPwLabel, confirmPwField, messageLabel);
        regWindow.setAlignment(Pos.CENTER);
        regWindow.getChildren().addAll(regText, allField, regButton, punyaAkun);
        layoutKedua.getChildren().addAll(slogan, regWindow);
        mainPanel.getChildren().addAll(logo, space46, layoutKedua);

        mainPanel.setAlignment(Pos.BASELINE_CENTER);
        root.getChildren().addAll(mainPanel);

        Scene scene = new Scene(root, 1382, 736);
        root.setPadding(new Insets(30));
        root.getStyleClass().add("rootPane");
        scene.getStylesheets().add("styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");
        
        stage.getIcons().add(new Image("styles/AppIcon.png"));
        stage.show();
    }
}
