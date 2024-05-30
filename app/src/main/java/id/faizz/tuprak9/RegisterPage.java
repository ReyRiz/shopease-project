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
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegisterPage {
    private Stage stage;

    // Constructor to receive the Stage object
    public RegisterPage(Stage stage) {
        this.stage = stage;
    }

    // Method to display the registration page
    public void showPage() {
        // Create a root StackPane as the root of all nodes in the scene
        StackPane root = new StackPane();
        VBox mainPanel = new VBox(10); // Main VBox with spacing of 10

        // Display the logo
        Image imageLogo = new Image("styles/logobiasa.png");
        ImageView logo = new ImageView(imageLogo);

        // Create HBox for the second layout
        HBox layoutKedua = new HBox();
        layoutKedua.setAlignment(Pos.CENTER);

        // Display the slogan
        Image imageSlogan = new Image("styles/imgbg.png");
        ImageView slogan = new ImageView(imageSlogan);
        slogan.getStyleClass().add("imgBg");

        // Create VBox for all registration input elements
        VBox allField = new VBox(7);
        VBox regWindow = new VBox(15); // VBox for registration layout
        regWindow.setPadding(new Insets(25, 28, 25, 28)); // Padding for VBox registration
        regWindow.setPrefSize(300, 365); // Preferred size for VBox registration
        regWindow.getStyleClass().add("registerBox"); // Add CSS class for styling

        Label regText = new Label("Register"); // "Register" label
        regText.getStyleClass().add("regText"); // Add CSS class for styling

        Label usrText = new Label("Username"); // "Username" label
        TextField usrField = new TextField(); // TextField for username input
        usrField.getStyleClass().add("fieldni"); // Add CSS class for styling

        Label pwLabel = new Label("Password"); // "Password" label
        PasswordField pwField = new PasswordField(); // PasswordField for password input
        pwField.getStyleClass().add("fieldni"); // Add CSS class for styling

        Label confirmPwLabel = new Label("Confirm Password"); // "Confirm Password" label
        PasswordField confirmPwField = new PasswordField(); // PasswordField for confirm password
        confirmPwField.getStyleClass().add("fieldni"); // Add CSS class for styling

        Label gagal = new Label("Gagal Register");
        gagal.getStyleClass().add("gagal");
        gagal.setVisible(false);

        Button regButton = new Button("Register"); // "Register" button
        regButton.getStyleClass().add("regBtn"); // Add CSS class for styling
        regButton.setPrefSize(244, 40); // Preferred size for registration button

        Label punyaAkun = new Label("Sudah punya akun? Masuk disini."); // Label for login

        // Action when "Masuk disini" label is clicked
        punyaAkun.setOnMouseClicked(e -> {
            LoginPage balik = new LoginPage(stage);
            balik.showPage();
        });

        // Action when "Register" button is clicked
        regButton.setOnAction(e -> {
            String username = usrField.getText();
            String password = pwField.getText();
            String konfirPassword = confirmPwField.getText();

            // Debugging messages to track input values
            // System.out.println("Username: " + username);
            // System.out.println("Password: " + password);
            // System.out.println("Confirm Password: " + konfirPassword);

            if (!password.equals(konfirPassword) || username.isEmpty()) {
                gagal.setTextFill(Color.web("#F65353"));
                gagal.setText("Gagal Membuat Akun");
            } else {
                boolean success = UsersControllers.register(username, password);

                if (success) {
                    LoginPage loginPage = new LoginPage(stage);
                    loginPage.showPage();
                }   
            }

                //     PauseTransition pause = new PauseTransition(Duration.seconds(4));
                //     pause.setOnFinished(event -> {
                //         LoginPage loginPage = new LoginPage(stage);
                //         loginPage.showPage();
                //     });
                //     pause.play();
                // } else {
                //     gagal.setText("Gagal membuat Akun");
                //     gagal.setTextFill(Color.web("#F65353"));
                // }
                // gagal.setVisible(true);
                // PauseTransition pause = new PauseTransition(Duration.seconds(3));
                // pause.setOnFinished(event -> gagal.setVisible(false));
                // pause.play();

            // if (!password.equals(konfirPassword) || username.isEmpty()) {
            //     gagal.setTextFill(Color.web("#F65353"));
            //     gagal.setText("Gagal Membuat Akun");
            //     gagal.setVisible(true);
            //     PauseTransition pause = new PauseTransition(Duration.seconds(3));
            //     pause.setOnFinished(event -> gagal.setVisible(false));
            //     pause.play();
            // } else {
            //     boolean success = false;
            //     try {
            //         success = UsersControllers.register(username, password);
            //     } catch (Exception ex) {
            //         ex.printStackTrace();
            //         System.err.println("Error during registration: " + ex.getMessage());
            //     }

            //     if (success) {
            //         gagal.setText("Berhasil membuat Akun");
            //         gagal.setTextFill(Color.web("#6345DD"));

            //         PauseTransition pause = new PauseTransition(Duration.seconds(4));
            //         pause.setOnFinished(event -> {
            //             LoginPage loginPage = new LoginPage(stage);
            //             loginPage.showPage();
            //         });
            //         pause.play();
            //     } else {
            //         gagal.setText("Gagal membuat Akun");
            //         gagal.setTextFill(Color.web("#F65353"));
            //     }
            //     gagal.setVisible(true);
            //     PauseTransition pause = new PauseTransition(Duration.seconds(3));
            //     pause.setOnFinished(event -> gagal.setVisible(false));
            //     pause.play();
            // }
        });

        Region space46 = new Region();
        space46.setPrefHeight(46);

        // Add elements to the registration VBox
        allField.getChildren().addAll(usrText, usrField, pwLabel, pwField, confirmPwLabel, confirmPwField, gagal);
        regWindow.setAlignment(Pos.CENTER);
        regWindow.getChildren().addAll(regText, allField, regButton, punyaAkun);
        layoutKedua.getChildren().addAll(slogan, regWindow);
        mainPanel.getChildren().addAll(logo, space46, layoutKedua);

        mainPanel.setAlignment(Pos.BASELINE_CENTER);
        root.getChildren().addAll(mainPanel);

        // Create scene and add stylesheet for styling
        Scene scene = new Scene(root, 1382, 736);
        root.setPadding(new Insets(30));
        root.getStyleClass().add("rootPane");
        scene.getStylesheets().add("styles/styles.css");
        stage.setScene(scene); // Set scene on the stage
        stage.setTitle("Shopease"); // Set application window title
        stage.show(); // Display the stage
    }
}
