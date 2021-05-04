package ui.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.FileChooser;
import utils.Util;

import java.io.File;

public class DialogView {

    private Image flagImage = null;

    @FXML
    private TextArea english_name_text_area;

    @FXML
    private TextArea russian_name_text_area;

    @FXML
    private TextArea english_description_text_area;

    @FXML
    private TextArea russian_description_text_area;

    @FXML
    private Button find_flag_button;

    @FXML
    private Label path_to_flag;

    @FXML
    void chooseFileClick(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG"),
               new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG")
        );
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            flagImage = new Image(file.toURI().toString());
            String[] splitString = file.toURI().toString().split("/");
            path_to_flag.setText(".../" + splitString[splitString.length - 2] + "/" +splitString[splitString.length - 1]);
        }
    }

    public String getEnglishName() {
        return english_name_text_area.getText();
    }

    public String getRussianName() {
        return russian_name_text_area.getText();
    }

    public String getEnglishDescription() {
        return english_description_text_area.getText();
    }

    public String getRussianDescription() {
        return russian_description_text_area.getText();
    }

    public Image getFlagImage() {
        return flagImage;
    }

}

