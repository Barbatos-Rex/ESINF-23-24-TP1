package pt.ipp.isep.esinf.functionality;

import pt.ipp.isep.esinf.data.DataBitChargers;
import pt.ipp.isep.esinf.data.GPS;
import pt.ipp.isep.esinf.structs.ChargersNearPOI;

import java.util.*;

public class FindTheClosestPOIToCharger {

    private Set<DataBitChargers> chargers;

    public FindTheClosestPOIToCharger(Set<DataBitChargers> chargers) {
        this.chargers = chargers;
    }


    public Set<ChargersNearPOI> closestChargerToPOI(Set<GPS> poi) {
        Map<GPS, Set<DataBitChargers>> result = new HashMap<>();
        for (GPS gps : poi) {
            result.put(gps, new HashSet<>());
        }

        for (DataBitChargers bit : chargers) {
            GPS closest = null;
            double minDistance = Double.MAX_VALUE;
            for (GPS gps : result.keySet()) {
                if (bit.getGpsCoords().distance(gps) < minDistance) {
                    closest = gps;
                }
            }
            result.get(closest).add(bit);
        }

        Set<ChargersNearPOI> resultSorted = new TreeSet<>();

        for (Map.Entry<GPS, Set<DataBitChargers>> gpsSetEntry : result.entrySet()) {
            resultSorted.add(new ChargersNearPOI(gpsSetEntry.getKey(), gpsSetEntry.getValue()));
        }

        return resultSorted;
    }


}
