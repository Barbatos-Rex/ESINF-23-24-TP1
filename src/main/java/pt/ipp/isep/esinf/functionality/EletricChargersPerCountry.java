package pt.ipp.isep.esinf.functionality;

import pt.ipp.isep.esinf.algorithm.TableMaximumMinimumDistanceBetweenChargersAlgorithm;
import pt.ipp.isep.esinf.data.DataBitChargers;
import pt.ipp.isep.esinf.structs.ChargingCapacity;
import pt.ipp.isep.esinf.structs.EletricStationsByKw;
import pt.ipp.isep.esinf.structs.MinimumAutonomy;

import java.util.*;

public class EletricChargersPerCountry {

    private Set<DataBitChargers> data;

    public EletricChargersPerCountry(Set<DataBitChargers> data) {
        this.data = data;
    }


    public Map<String, Integer> numberOfChargersPerCity() {
        Map<String, Integer> result = new HashMap<>();
        for (DataBitChargers bit : data) {
            String countryToOperate = bit.getCountry();
            if (!result.containsKey(countryToOperate)) {
                result.put(countryToOperate, 0);
            }
            result.replace(countryToOperate, result.get(countryToOperate) + 1);
        }
        return result;
    }


    public EletricStationsByKw chargerDistributionByCountryKilloWattageLimit(int limit) {
        EletricStationsByKw result = new EletricStationsByKw(limit);
        for (DataBitChargers bit : data) {
            result.registerStation(bit.getCountry(), Integer.parseInt(bit.getkW()));
        }
        return result;
    }

    public Set<MinimumAutonomy> maximumMinimumDistanceBetweenChargingStationsByCountry() {
        Map<String, Set<DataBitChargers>> chargersByCountry = mapToChargersByCountry();
        Set<MinimumAutonomy> result = new TreeSet<>(new MinimumAutonomy.MinimumAutonomyComparatorByAutonomyDecreasingCountryAlphabetically(new TableMaximumMinimumDistanceBetweenChargersAlgorithm()));
        return result;
    }

    private Map<String, Set<DataBitChargers>> mapToChargersByCountry() {
        Map<String, Set<DataBitChargers>> result = new HashMap<>();
        for (DataBitChargers bit : data) {
            if (!result.containsKey(bit.getCountry())) {
                result.put(bit.getCountry(), new HashSet<>());
            }
            result.get(bit.getCountry()).add(bit);
        }
        return result;
    }


    public enum Option {
        CITY,
        STATE;
    }

    public Set<ChargingCapacity> getNStatesWithHighestCapacity(Collection<String> cityStates, Option option, int n) {

        switch (option) {
            case CITY -> {
                return getNStatesWithHighestCapacity(citiesToStates(cityStates), Option.STATE, n);
            }
            case STATE -> {
                Set<DataBitChargers> stateData = filterChargersByStates(cityStates);
                Map<String, ChargingCapacity> capacity = new HashMap<>();
                for (DataBitChargers sData : stateData) {
                    if (capacity.containsKey(sData.getState())) {
                        capacity.put(sData.getState(), new ChargingCapacity(sData.getState(), new HashSet<>(), 0));
                    }
                    capacity.get(sData.getState()).addEntry(sData);
                }
                return new TreeSet<>(capacity.values());
            }
            default -> throw new UnsupportedOperationException("That option is not implemented yet!");
        }
    }


    private Set<DataBitChargers> filterChargersByStates(Collection<String> states) {
        Set<DataBitChargers> result = new HashSet<>();
        for (DataBitChargers bit : data) {
            if (states.contains(bit.getState())) {
                result.add(bit);
            }
        }
        return result;
    }


    private Set<String> citiesToStates(Collection<String> cities) {

        Set<String> result = new HashSet<>();
        for (DataBitChargers bit : data) {
            if (cities.contains(bit.getCity())) {
                result.add(bit.getState());
            }
        }
        return result;
    }


}
