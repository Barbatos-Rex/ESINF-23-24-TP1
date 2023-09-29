package pt.ipp.isep.esinf.structs;

import pt.ipp.isep.esinf.data.DataBitChargers;
import pt.ipp.isep.esinf.data.GPS;

import java.util.Objects;
import java.util.Set;

public class ChargersNearPOI implements Comparable<ChargersNearPOI> {
    private GPS coordenates;
    private Set<DataBitChargers> chargers;


    public ChargersNearPOI(GPS coordenates, Set<DataBitChargers> chargers) {
        this.coordenates = coordenates;
        this.chargers = chargers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargersNearPOI that = (ChargersNearPOI) o;
        return Objects.equals(coordenates, that.coordenates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordenates);
    }

    public GPS getCoordenates() {
        return coordenates;
    }

    public Set<DataBitChargers> getChargers() {
        return chargers;
    }


    @Override
    public int compareTo(ChargersNearPOI o) {
        return -Integer.compare(chargers.size(), o.chargers.size());
    }
}
