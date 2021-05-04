package ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import domain.Country;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ui.Main;
import ui.dialog.DialogView;
import utils.Util;

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

    @FXML
    void onAddCountryClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("layout/dialog_layout.fxml"));
            DialogPane dialogPane = loader.load();
            DialogView dialogView = loader.getController();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Add Country");
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.orElse(null) == ButtonType.OK) {
                if (dialogView.getEnglishName().isEmpty() || dialogView.getRussianName().isEmpty() || dialogView.getEnglishDescription().isEmpty() || dialogView.getRussianDescription().isEmpty()){
                  System.out.println("Please, fill all fields");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Exception");
                    alert.setContentText("Please fill all fields");
                    alert.showAndWait();
                  return;
                }
                if (dialogView.getFlagImage() == null) {
                    System.out.println("image is null");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Exception");
                    alert.setContentText("Please provide flag.png");
                    alert.showAndWait();
                    return;
                }
                System.out.println(dialogView.getEnglishName());
                Country country = new Country(
                        dialogView.getEnglishName(),
                        dialogView.getRussianName(),
                        dialogView.getEnglishDescription(),
                        dialogView.getRussianDescription()
                );
                viewModel.writeCountryToFile(
                        country
                );
                viewModel.writeImageToFile(country, dialogView.getFlagImage());
                viewModel.readCountryFile();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onDeleteCountryClick(ActionEvent event) {
        try {
            Country c = viewModel.getSelectedCountry();
            viewModel.deleteCountryFromFile(c);
            viewModel.deleteImageForCountry(c);
            if (!viewModel.getCountries().isEmpty()) {
                viewModel.setSelectedCountry(viewModel.getCountries().get(0));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void onUpdateClicked(ActionEvent event) {
        viewModel.readCountryFile();
    }

    @FXML
    void onEnglishClick(ActionEvent event) {
        Util.isEnglishLanguage = true;
        initialize();
    }

    @FXML
    void onRussianClick(ActionEvent event) {
        Util.isEnglishLanguage = false;
        initialize();
    }

    @FXML
    void onCloseClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void initialize() {
        if (viewModel.getSelectedCountry() != null) {
            bind();
        }
        country_lst.setCellFactory(param -> new ListCell<Country>(){
            @Override
            protected void updateItem(Country country, boolean b) {
                super.updateItem(country, b);
                if (b || country == null) {
                    setText(null);
                }else {
                    if (Util.isEnglishLanguage) {
                        setText(country.getNameEng());
                    }
                    if (!Util.isEnglishLanguage) {
                        setText(country.getNameRus());
                    }
                }
            }
        });
        country_lst.setItems(viewModel.getCountries());
        country_lst.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Country>() {
            @Override
            public void changed(ObservableValue<? extends Country> observableValue, Country country, Country t1) {
                viewModel.setSelectedCountry(t1);
                bind();
            }
        });
    }

    private void bind() {
        Country country = viewModel.getSelectedCountry();
        if (Util.isEnglishLanguage) {
            country_name.setText(country.getNameEng());
            country_description.setText(country.getDescriptionEng());
        }
        if (!Util.isEnglishLanguage){
            country_name.setText(country.getNameRus());
            country_description.setText(country.getDescriptionRus());
        }
        Image img = viewModel.getImageForCountry(viewModel.getSelectedCountry());
        country_image_view.setImage(img);
    }

}


