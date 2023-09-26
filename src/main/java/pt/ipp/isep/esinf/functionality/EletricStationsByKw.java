package pt.ipp.isep.esinf.functionality;

import java.util.*;

public class EletricStationsByKw {
    private int limit;

    private Map<String, StationEntry> countryStations;

    public static class StationEntry {
        private int superiorCounter;
        private int inferiorCounter;

        private String country;

        public StationEntry(String country) {
            this.superiorCounter = 0;
            this.inferiorCounter = 0;
            this.country = country;
        }

        public int getSuperiorCounter() {
            return superiorCounter;
        }

        public String getCountry() {
            return country;
        }

        public int getInferiorCounter() {
            return inferiorCounter;
        }

        public void incrementSuperior() {
            superiorCounter++;
        }

        public void incrementInferior() {
            inferiorCounter++;
        }

        public int getTotal() {
            return superiorCounter + inferiorCounter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StationEntry that = (StationEntry) o;
            return Objects.equals(country, that.country);
        }

        @Override
        public int hashCode() {
            return Objects.hash(country);
        }
    }

    public EletricStationsByKw(int limit) {
        this.limit = limit;
        countryStations = new HashMap<>();
    }


    public void registerStation(String country, int killoWattage) {
        if (countryStations.containsKey(country)) {
            countryStations.put(country, new StationEntry(country));
        }
        if (killoWattage <= limit) {
            countryStations.get(country).incrementInferior();
            return;
        }
        countryStations.get(country).incrementSuperior();
    }


    private static class EntryComparator implements Comparator<StationEntry> {

        @Override
        public int compare(StationEntry o1, StationEntry o2) {
            if (o1.getTotal() == o2.getTotal()) {
                return o1.getCountry().compareToIgnoreCase(o2.getCountry());
            }
            return o1.getTotal() - o2.getTotal();
        }
    }

    public Set<StationEntry> processStations() {
        Set<StationEntry> result = new TreeSet<>(new EntryComparator());
        for (Map.Entry<String, StationEntry> stationsEntry : countryStations.entrySet()) {
            result.add(stationsEntry.getValue());
        }
        return result;
    }


}
