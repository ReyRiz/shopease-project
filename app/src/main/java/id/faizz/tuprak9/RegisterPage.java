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
    private Stage stage;

    // Konstruktor untuk menerima objek Stage
    RegisterPage(Stage stage){
        this.stage = stage;
    }

    // Metode untuk menampilkan halaman pendaftaran
    public void showPage(){
        // Membuat root StackPane sebagai root dari semua node di scene
        StackPane root = new StackPane();
        VBox mainPanel = new VBox(10); // VBox utama dengan jarak antar elemen 10

        // Menampilkan logo
        Image imageLogo = new Image("styles/logobiasa.png");
        ImageView logo = new ImageView(imageLogo);

        // Membuat HBox untuk layout kedua
        HBox layoutKedua = new HBox(10);
        layoutKedua.setAlignment(Pos.CENTER);

        // Menampilkan slogan
        Image imageSlogan = new Image("styles/slogan.png");
        ImageView slogan = new ImageView(imageSlogan);

        Region space177 = new Region();
        space177.setPrefWidth(177);

        // Membuat VBox untuk semua elemen input pendaftaran
        VBox allField = new VBox(7);
        VBox regWindow = new VBox(15); // VBox untuk tata letak pendaftaran
        regWindow.setPadding(new Insets(25, 28, 25, 28)); // Padding VBox pendaftaran
        regWindow.setPrefSize(300, 365); // Ukuran preferensi VBox pendaftaran
        regWindow.getStyleClass().add("loginBox"); // Menambahkan kelas CSS untuk styling
        Label regText = new Label("Register"); // Label "Register"
        regText.getStyleClass().add("regText"); // Menambahkan kelas CSS untuk styling
        Label usrText = new Label("Username"); // Label "Username"
        TextField usrField = new TextField(); // TextField untuk input username
        usrField.getStyleClass().add("fieldni"); // Menambahkan kelas CSS untuk styling
        Label pwLabel = new Label("Password"); // Label "Password"
        PasswordField pwField = new PasswordField(); // PasswordField untuk input password
        pwField.getStyleClass().add("fieldni"); // Menambahkan kelas CSS untuk styling
        Label confirmPwLabel = new Label("Confirm Password"); // Label "Confirm Password"
        PasswordField confirmPwField = new PasswordField(); // PasswordField untuk konfirmasi password
        confirmPwField.getStyleClass().add("fieldni"); // Menambahkan kelas CSS untuk styling

        Button regButton = new Button("Register"); // Tombol "Register"
        regButton.getStyleClass().add("loginbtn"); // Menambahkan kelas CSS untuk styling
        regButton.setPrefSize(244, 40); // Ukuran preferensi tombol pendaftaran
        Label punyaAkun = new Label("Sudah punya akun? Masuk disini."); // Label untuk login

        // Aksi saat label "Masuk disini" diklik
        punyaAkun.setOnMouseClicked(e -> {
            LoginPage balik = new LoginPage(stage);
            balik.showPage();
        });

        // Aksi saat tombol "Register" diklik
        regButton.setOnAction(e -> {
            HomePage home = new HomePage(stage);
            home.show();
        });

        // Menambahkan elemen-elemen ke dalam VBox pendaftaran
        allField.getChildren().addAll(usrText, usrField, pwLabel, pwField, confirmPwLabel, confirmPwField);
        regWindow.setAlignment(Pos.CENTER);
        regWindow.getChildren().addAll(regText, allField, regButton, punyaAkun);
        layoutKedua.getChildren().addAll(slogan, space177, regWindow);
        mainPanel.getChildren().addAll(logo, layoutKedua);

        mainPanel.setAlignment(Pos.BASELINE_CENTER);
        root.getChildren().addAll(mainPanel);

        // Membuat scene dan menambahkan stylesheet untuk styling
        Scene scene = new Scene(root, 1382, 736);
        root.setPadding(new Insets(30));
        root.getStyleClass().add("rootPane");
        scene.getStylesheets().add("styles/styles.css");
        stage.setScene(scene); // Menetapkan scene pada stage
        stage.setTitle("Shopease"); // Menetapkan judul jendela aplikasi
        stage.show(); // Menampilkan stage
    }
}
