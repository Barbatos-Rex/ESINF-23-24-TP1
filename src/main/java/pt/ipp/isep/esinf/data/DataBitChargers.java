package pt.ipp.isep.esinf.data;

import java.util.Objects;

/**
 * @author André
 * @version 1.0.0
 *
 * <b>Class DataBitChargers</b>
 * <p>This class is the single unit for the representation of each entry in the
 * "carregadores_europa.xlsx" file.</p>
 * <p>Each entry is distinguished by their supercharger name</p>
 */
public class DataBitChargers {
    /**
     * Name of a Supercharger entry
     */
    private String superCharger;
    /**
     * Address of a Supercharger entry
     */
    private String streetAdress;
    /**
     * Operational City of a Supercharger entry
     */
    private String city;
    /**
     * Operational state of a Supercharger entry
     */
    private String state;
    /**
     * Zip address of a Supercharger entry
     */
    private String zip;
    /**
     * Operational contry of a Supercharger entry
     */
    private String country;
    /**
     * Number of stalls of a given Supercharger entry
     */
    private String stalls;

    /**
     * The killo Wattage of a Supercharger entry
     */
    private String kW;
    /**
     * The GPS coordinates of a Supercharger entry (pair latitude - longitude)
     */
    private GPS gpsCoords;

    /**
     *
     */
    private String elevm;

    /**
     * Status of a give Supercharger entry
     */
    private String status;

    public DataBitChargers(String superCharger, String streetAdress, String city, String state, String zip, String country, String stalls, String kW, GPS gpsCoords, String elevm, String status) {
        this.superCharger = superCharger;
        this.streetAdress = streetAdress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.stalls = stalls;
        this.kW = kW;
        this.gpsCoords = gpsCoords;
        this.elevm = elevm;
        this.status = status;
    }

    public String getSuperCharger() {
        return superCharger;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public String getStalls() {
        return stalls;
    }

    public String getkW() {
        return kW;
    }

    public GPS getGpsCoords() {
        return gpsCoords;
    }

    public String getElevm() {
        return elevm;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBitChargers that = (DataBitChargers) o;
        return Objects.equals(superCharger, that.superCharger);
    }

    @Override
    public String toString() {
        return "DataBitChargers{" +
                "superCharger='" + superCharger + '\'' +
                ", streetAdress='" + streetAdress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", stalls='" + stalls + '\'' +
                ", kW='" + kW + '\'' +
                ", gpsCoords=" + gpsCoords +
                ", elevm='" + elevm + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(superCharger);
    }
}
