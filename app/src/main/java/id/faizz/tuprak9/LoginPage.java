package id.faizz.tuprak9;

import javafx.application.Application;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Users;
import javafx.animation.*;
import javafx.stage.Stage;
import javafx.util.Duration;
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

// Kelas untuk halaman login
public class LoginPage {
    private Stage stage;

    // Konstruktor untuk menerima objek Stage
    LoginPage(Stage stage) {
        this.stage = stage;
    }

    // Metode untuk menampilkan halaman login
    public void showPage() {
        StackPane root = new StackPane();

        // Membuat tata letak VBox utama
        VBox mainPanel = new VBox();
        mainPanel.setAlignment(Pos.TOP_CENTER);

        // Menambahkan logo
        Image logox = new Image("styles/logobiasa.png");
        ImageView logo = new ImageView(logox);

        // Membuat VBox untuk elemen-elemen login
        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(26, 28, 30, 29));
        loginBox.setPrefSize(300, 365);
        loginBox.getStyleClass().add("loginBox");

        // Membuat elemen-elemen login
        Label textLogin = new Label("Log-in");
        textLogin.getStyleClass().add("loginText");
        Label usernameText = new Label("Username");
        TextField usernameField = new TextField();
        usernameField.setMinSize(244, 30);
        usernameField.getStyleClass().add("fieldni");
        Label pwText = new Label("Password");
        PasswordField pwField = new PasswordField();
        pwField.setMinSize(244, 30);
        pwField.getStyleClass().add("fieldni");

        // Tombol login
        Button loginBtn = new Button("Login");
        loginBtn.setMinSize(250, 35);
        loginBtn.getStyleClass().add("loginbtn");

        // Garis dan teks untuk pengguna baru
        HBox newUserLine = new HBox(10);
        Line line1 = new Line(0, 0, 71, 0);
        line1.setStrokeWidth(1);
        Text textNewUser = new Text("New User?");
        Line line2 = new Line(0, 0, 71, 0);
        line2.setStrokeWidth(1);
        newUserLine.getChildren().addAll(line1, textNewUser, line2);
        newUserLine.setAlignment(Pos.CENTER);

        // Tombol untuk pendaftaran
        Button regBtn = new Button("Register");
        regBtn.setMinSize(250, 35);
        regBtn.getStyleClass().add("regBtn");


        VBox gagal = new VBox();
        gagal.setPrefSize(20, 10);
        Label gagal1 = new Label("Gagal Login");
        gagal1.setVisible(true);
        gagal1.getStyleClass().add("gagal");
        gagal1.setAlignment(Pos.CENTER);
        // Menambahkan elemen-elemen ke dalam VBox loginBox
        loginBox.getChildren().addAll(textLogin, usernameText, usernameField, pwText, pwField, loginBtn, newUserLine,
                regBtn, gagal);
        Region space45 = new Region();
        space45.setPrefSize(0, 45);

        // Menambahkan elemen-elemen ke dalam VBox utama
        mainPanel.getChildren().addAll(logo, space45, loginBox);

        root.getChildren().addAll(mainPanel);
        mainPanel.setPadding(new Insets(30, 0, 0, 0));
        Scene scene = new Scene(root, 1382, 736);
        root.setPadding(new Insets(30, 540, 30, 542));
        root.getStyleClass().add("rootPane");
        scene.getStylesheets().add("styles/styles.css");
        stage.setScene(scene);
        stage.setTitle("Shopease");

        // Aksi ketika tombol login ditekan
        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = pwField.getText();

            Users userLogin = UsersControllers.login(username, password);

            if (userLogin != null){
                HomePage home = new HomePage(stage);
                home.show(userLogin.getId());
            } else {
                gagal.setVisible(true);
            }

        });

        // Aksi ketika tombol register ditekan
        regBtn.setOnAction(e -> {
            RegisterPage regPage = new RegisterPage(stage);
            regPage.showPage();
        });

        // Menampilkan stage
        stage.show();
    }
}
