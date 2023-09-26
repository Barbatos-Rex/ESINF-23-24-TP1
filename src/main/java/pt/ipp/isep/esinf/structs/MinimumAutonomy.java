package pt.ipp.isep.esinf.structs;

import pt.ipp.isep.esinf.algorithm.MaximumMinimumDistanceBetweenChargersAlgorithm;
import pt.ipp.isep.esinf.data.DataBitChargers;

import java.util.Comparator;
import java.util.Set;

public class MinimumAutonomy {


    public static class MinimumAutonomyComparatorByAutonomyDecreasingCountryAlphabetically
            implements Comparator<MinimumAutonomy> {

        private MaximumMinimumDistanceBetweenChargersAlgorithm algorithm;

        public MinimumAutonomyComparatorByAutonomyDecreasingCountryAlphabetically(
                MaximumMinimumDistanceBetweenChargersAlgorithm algorithm) {
            this.algorithm = algorithm;
        }

        @Override
        public int compare(MinimumAutonomy o1, MinimumAutonomy o2) {
            return Double.compare(o2.findMaximumMinimumDistanceBetweenChargers(algorithm),
                    o1.findMaximumMinimumDistanceBetweenChargers(algorithm));
        }
    }

    private String country;

    private Set<DataBitChargers> countryChargers;

    private Double minimumAutonomy;

    public MinimumAutonomy(String country, Set<DataBitChargers> countryChargers) {
        this.country = country;
        this.countryChargers = countryChargers;
        this.minimumAutonomy = null;
    }


    private double findMaximumMinimumDistanceBetweenChargers(
            MaximumMinimumDistanceBetweenChargersAlgorithm algorithm) {
        if (this.minimumAutonomy == null) {
            this.minimumAutonomy = algorithm.calculate(countryChargers);
        }
        return this.minimumAutonomy;
    }


}
