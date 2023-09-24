package pt.ipp.isep.esinf;

import java.util.Objects;

public class DataBitEVSale {
    private String country;
    private String powertrain;

    private String year;

    private int numberOfVehicles;

    public DataBitEVSale(String country, String powertrain, String year, int numberOfVehicles) {
        this.country = country;
        this.powertrain = powertrain;
        this.year = year;
        this.numberOfVehicles = numberOfVehicles;
    }

    @Override
    public String toString() {
        return "DataBitEVSale{" +
                "country='" + country + '\'' +
                ", powertrain='" + powertrain + '\'' +
                ", year='" + year + '\'' +
                ", numberOfVehicles=" + numberOfVehicles +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public String getPowertrain() {
        return powertrain;
    }

    public String getYear() {
        return year;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBitEVSale that = (DataBitEVSale) o;
        return Objects.equals(country, that.country) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, year);
    }
}
