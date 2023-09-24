package pt.ipp.isep.esinf;

import java.util.Objects;

public class InvariabilityOfSales {
    private String year;
    private int ammount;
    private String powertrain;

    private String country;

    public InvariabilityOfSales(String year, int ammount, String powertrain, String country) {
        this.year = year;
        this.ammount = ammount;
        this.powertrain = powertrain;
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public int getAmmount() {
        return ammount;
    }

    public String getPowertrain() {
        return powertrain;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvariabilityOfSales that = (InvariabilityOfSales) o;
        return year == that.year && Objects.equals(powertrain, that.powertrain) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, powertrain, country);
    }
}
