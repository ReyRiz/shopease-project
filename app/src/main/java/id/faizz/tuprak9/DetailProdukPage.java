package id.faizz.tuprak9;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import id.faizz.tuprak9.models.Produk;

public class DetailProdukPage {
    private Stage stage;

    DetailProdukPage(Stage stage) {
        this.stage = stage;
    }

    public void show(Produk produk) {
        StackPane root = new StackPane();
        root.getStyleClass().add("detailProdukPage");

        VBox detailBox = new VBox(20);
        detailBox.setPadding(new Insets(50));
        detailBox.setAlignment(Pos.CENTER);
        detailBox.getStyleClass().add("detailBox");

        ImageView imageView = new ImageView(new Image("file:" + produk.getFoto()));
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);

        Label namaLabel = new Label(produk.getNama());
        namaLabel.getStyleClass().add("namaProdukDetail");

        Label hargaLabel = new Label(String.valueOf(produk.getHarga()));
        hargaLabel.getStyleClass().add("hargaProdukDetail");

        Label deskripsiLabel = new Label(produk.getDeskripsi());
        deskripsiLabel.getStyleClass().add("deskripsiProdukDetail");
        deskripsiLabel.setWrapText(true);

        detailBox.getChildren().addAll(imageView, namaLabel, hargaLabel, deskripsiLabel);
        root.getChildren().add(detailBox);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle("Detail Produk");
        stage.setScene(scene);
        stage.show();
    }
}
