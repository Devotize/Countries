package domain;

import java.util.Objects;

public class Country {

    private final String nameEng;
    private final String nameRus;
    private final String descriptionEng;
    private final String descriptionRus;

    public Country(String nameEng, String nameRus, String descriptionEng, String descriptionRus) {
        this.nameEng = nameEng;
        this.nameRus = nameRus;
        this.descriptionEng = descriptionEng;
        this.descriptionRus = descriptionRus;
    }

    public String getNameEng() {
        return nameEng;
    }

    public String getNameRus() {
        return nameRus;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public String getDescriptionRus() {
        return descriptionRus;
    }

    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return nameEng.equals(country.nameEng) &&
                nameRus.equals(country.nameRus) &&
                descriptionEng.equals(country.descriptionEng) &&
                descriptionRus.equals(country.descriptionRus);

    }



    @Override

    public int hashCode() {
        return Objects.hash(nameEng, nameRus, descriptionEng, descriptionRus);
    }



    @Override

    public String toString() {
        return "Country{" +
                "nameEng=" + nameEng +
                ", nameRus=" + nameRus +
                ", descriptionEng=" + descriptionEng +
                ", descriptionRus=" + descriptionRus +
                "}";
    }

}






