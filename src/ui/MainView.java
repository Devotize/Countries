package ui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.*;

import domain.Country;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ui.dialog.DialogView;
import ui.main.MainViewModel;

public class MainView {

    private MainViewModel viewModel = new MainViewModel();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Country> country_lst;

    @FXML
    private Button add_button;

    @FXML
    private Label country_name;

    @FXML
    private Label country_description;

    @FXML
    private ImageView country_image_view;

    @FXML
    private Button delete_button;

//    @FXML
//    void onAddCountryClick(MouseEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Main.class.getResource("layout/dialog_layout.fxml"));
//            DialogPane dialogPane = loader.load();
//            DialogView dialogView = loader.getController();
//            Dialog<ButtonType> dialog = new Dialog<>();
//            dialog.setDialogPane(dialogPane);
//            dialog.setTitle("Add Country");
//            Optional<ButtonType> result = dialog.showAndWait();
//            if (result.orElse(null) == ButtonType.OK) {
//                System.out.println(dialogView.getEnglishName());
//                if (dialogView.getEnglishName().isEmpty() || dialogView.getRussianName().isEmpty() || dialogView.getEnglishDescription().isEmpty() || dialogView.getRussianDescription().isEmpty()){
//                  System.out.println("Please, fill all fields");
//                  return;
//                }
//                if (dialogView.getFlagImage() == null) {
//                    System.out.println("image is null");
//                    return;
//                }
//                Country country = new Country(
//                        dialogView.getEnglishName(),
//                        dialogView.getRussianName(),
//                        dialogView.getEnglishDescription(),
//                        dialogView.getRussianDescription()
//                );
//                viewModel.writeCountryToFile(
//                        country
//                );
//                viewModel.writeImageToFile(country, dialogView.getFlagImage());
//                viewModel.readCountryFile();
//            }
//
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @FXML
//    void onDeleteClick(MouseEvent event) {
//        try {
//            viewModel.deleteCountryFromFile(viewModel.getSelectedCountry());
//            viewModel.readCountryFile();
//            if (!viewModel.getCountries().isEmpty()) {
//                viewModel.setSelectedCountry(viewModel.getCountries().get(0));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    @FXML
//    void initialize() {
//        if (viewModel.getSelectedCountry() != null) {
//            bind();
//        }
//        country_lst.setCellFactory(param -> new ListCell<Country>(){
//            @Override
//            protected void updateItem(Country country, boolean b) {
//                super.updateItem(country, b);
//                if (b || country == null) {
//                    setText(null);
//                }else {
//                    setText(country.getNameEng());
//                }
//            }
//        });
//        country_lst.setItems(viewModel.getCountries());
//        country_lst.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Country>() {
//            @Override
//            public void changed(ObservableValue<? extends Country> observableValue, Country country, Country t1) {
//                viewModel.setSelectedCountry(t1);
//                bind();
//            }
//        });
//    }
//
//    private void bind() {
//        Country country = viewModel.getSelectedCountry();
//        country_name.setText(country.getNameEng());
//        country_description.setText(country.getDescriptionEng());
//    }

}


