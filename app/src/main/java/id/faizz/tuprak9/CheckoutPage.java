package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import id.faizz.tuprak9.controllers.KeranjangControllers;
import id.faizz.tuprak9.controllers.ProdukControllers;
import id.faizz.tuprak9.controllers.UsersControllers;
import id.faizz.tuprak9.models.Keranjang;
import id.faizz.tuprak9.models.Produk;
import id.faizz.tuprak9.models.Users;

public class CheckoutPage {
    private Stage stage;

    CheckoutPage(Stage stage){
        this.stage = stage;
    }

    public void show(int userId, Label totalHarga){
        StackPane root = new StackPane();
        

        Scene scene = new Scene(root, 1382, 736);
        stage.setScene(scene);
        stage.setTitle("Shopease");
        stage.getIcons().add(new Image("styles/AppIcon.png"));
        stage.show();
    }
}