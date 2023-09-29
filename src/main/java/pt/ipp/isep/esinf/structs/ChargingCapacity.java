package pt.ipp.isep.esinf.structs;

import pt.ipp.isep.esinf.data.DataBitChargers;

import java.util.Objects;
import java.util.Set;

public class ChargingCapacity implements Comparable<ChargingCapacity> {
    private String state;
    private Set<String> cities;
    private int chargingCapacity;

    public ChargingCapacity(String state, Set<String> cities, int chargingCapacity) {
        this.cities = cities;
        this.chargingCapacity = chargingCapacity;
        this.state = state;
    }

    public Set<String> getCities() {
        return cities;
    }

    public int getChargingCapacity() {
        return chargingCapacity;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingCapacity that = (ChargingCapacity) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }


    public void addEntry(DataBitChargers bit){
        if (!bit.getState().equals(state) || !bit.getStatus().equalsIgnoreCase("open")){
            return;
        }
        chargingCapacity+=Integer.parseInt(bit.getkW())*Integer.getInteger(bit.getStalls());
        cities.add(bit.getCity());
    }


    @Override
    public int compareTo(ChargingCapacity o) {
        return -Integer.compare(chargingCapacity,o.chargingCapacity);
    }
}
