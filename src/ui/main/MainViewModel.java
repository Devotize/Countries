package ui.main;

import domain.Country;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import utils.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainViewModel {

    private Country selectedCountry;
    private ObservableList<Country> countries = FXCollections.observableArrayList();

    public MainViewModel() {
        readCountryFile();
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
        String str = country.getNameEng() + "," + Util.byteArrayToString(Util.imageToByteArray(image)) + "\n";
        try {
            FileWriter writer = new FileWriter(Util.COUNTRY_IMAGE_FILE_NAME, true);
            writer.write(str);
            writer.close();
        }catch (IOException e){
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
        try {
            Scanner fileReader = new Scanner(new File(Util.COUNTRY_IMAGE_FILE_NAME));
            while (fileReader.hasNextLine()) {
                String str = fileReader.nextLine();
                String[] stringArray = str.split(",");
                if (country.getNameEng().equals(stringArray[0])) {
                    String imageString = stringArray[1];
                    byte[] buffer = Util.stringToByteArray(imageString);
                    return Util.byteArrayToImage(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
