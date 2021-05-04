package ui.main;

import domain.Country;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import ui.Main;
import utils.Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainViewModel {

    private Country selectedCountry;
    private ObservableList<Country> countries = FXCollections.observableArrayList();

    public MainViewModel() {
        readCountryFile();
        if (countries.isEmpty()) return;
        selectedCountry = countries.get(0);
    }

    public void writeCountryToFile(Country country) {
        if (isCountryInDatabase(country)) return;
        try {
            FileWriter fileWriter = new FileWriter(Util.COUNTRY_LIST_FILE_NAME, true);
            fileWriter.write(
                    country.getNameEng() + "," +
                            country.getNameRus() + "," +
                            country.getDescriptionEng() + "," +
                            country.getDescriptionRus() + "\n"
            );
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Here is another error:)");
            e.printStackTrace();
        }
    }

    public void writeImageToFile(Country country, Image image) {
        try {
            File file = new File("resources/pictures/" + country.getNameEng() + ".jpg");
//            BufferedImage awtImage = new BufferedImage((int)image.getWidth(), (int)image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readCountryFile() {
        try {
            File file = new File(Util.COUNTRY_LIST_FILE_NAME);
            Scanner reader = new Scanner(file);
            countries.clear();
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] split = data.split(",");
                countries.add(
                        new Country(
                                split[0],
                                split[1],
                                split[2],
                                split[3]
                        )
                );
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCountryFromFile(Country country) {
        countries.remove(country);
        try {
            StringBuilder sb = new StringBuilder();
            countries.forEach(c -> {
                sb.append(c.getNameEng()).append(",").append(c.getNameRus()).append(",").append(c.getDescriptionEng()).append(",").append(c.getDescriptionRus()).append("\n");
            });
            FileWriter fw = new FileWriter(Util.COUNTRY_LIST_FILE_NAME);
            fw.write(sb.toString());
            fw.flush();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public boolean isCountryInDatabase(Country country) {
        boolean isContains = false;
        for (Country value : countries) {
            if (value.getNameEng().equals(country.getNameEng())) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    public Image getImageForCountry(Country country) {
        File f = new File("resources/pictures/" + country.getNameEng() + ".jpg");
        if (f.exists() && !f.isDirectory()){
            return new Image(f.toURI().toString());
        }
        return null;
    }

    public void deleteImageForCountry(Country country) {
        File f = new File("resources/pictures/" + country.getNameEng() + ".jpg");
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
    }

    public ObservableList<Country> getCountries() {
        return countries;
    }

    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
    }
}
