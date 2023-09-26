package pt.ipp.isep.esinf.functionality;

import pt.ipp.isep.esinf.data.DataBitEVSale;
import pt.ipp.isep.esinf.structs.DoublyYearRate;
import pt.ipp.isep.esinf.structs.InvariabilityOfSales;

import java.util.*;

public class SalesEvolution {
    private Set<DataBitEVSale> data;

    public SalesEvolution(Set<DataBitEVSale> data) {
        this.data = data;
    }


    public Map<String, DoublyYearRate> evolutionBetween2yearsByContry(int first, int second) {
        if (first == second) {
            throw new RuntimeException("Evolution rate is obligatory nill");
        }

        if (second < first) {
            return evolutionBetween2yearsByContry(second, first);
        }

        Set<DataBitEVSale> filtered = filterByYears(Integer.toString(first), Integer.toString(second));

        Map<String, DoublyYearRate> result = new HashMap<>();

        for (DataBitEVSale bit : filtered) {
            if (!result.containsKey(bit.getCountry())) {
                result.put(bit.getCountry(), new DoublyYearRate(Integer.toString(first), Integer.toString(second)));
            }

            DoublyYearRate rate = result.get(bit.getCountry());

            if (bit.getYear().equalsIgnoreCase(Integer.toString(first))) {
                rate.setAmmount1(bit.getNumberOfVehicles());
            } else {
                rate.setAmmount2(bit.getNumberOfVehicles());
            }
        }

        return result;
    }

    private Set<DataBitEVSale> filterByYears(String... years) {
        Set<DataBitEVSale> result = new HashSet<>();
        Set<String> yearsSet = Set.of(years);
        for (DataBitEVSale bit : data) {
            if (yearsSet.contains(bit.getYear())) {
                result.add(bit);
            }
        }
        return result;
    }

    public Set<InvariabilityOfSales> countriesWithoutSalesChangesByPowertrain() {
        Set<InvariabilityOfSales> result = new HashSet<>();
        Map<String, Map<String, Map<String, DataBitEVSale>>> mapOfSalesByCountryYearPowertrain = mapToCountryYearPowertrainMap();
        for (Map.Entry<String, Map<String, Map<String, DataBitEVSale>>> countryEntry : mapOfSalesByCountryYearPowertrain.entrySet()) {
            Map.Entry<String, Map<String, DataBitEVSale>> oldYearEntry = null;
            for (Map.Entry<String, Map<String, DataBitEVSale>> yearEntry : countryEntry.getValue().entrySet()) {
                if (oldYearEntry==null){
                    oldYearEntry=yearEntry;
                    continue;
                }
                for (Map.Entry<String, DataBitEVSale> powertrainEntry : yearEntry.getValue().entrySet()) {
                    if (!oldYearEntry.getValue().containsKey(powertrainEntry.getKey())){
                        continue;
                    }
                    int lastYearSales = oldYearEntry.getValue().get(powertrainEntry.getKey()).getNumberOfVehicles();
                    int currentYearSales = powertrainEntry.getValue().getNumberOfVehicles();
                    if(currentYearSales<=lastYearSales){
                        result.add(new InvariabilityOfSales(yearEntry.getKey(),currentYearSales-lastYearSales, powertrainEntry.getKey(), countryEntry.getKey()));
                    }
                }
            }
        }
        return result;
    }

    private Map<String, Map<String, Map<String, DataBitEVSale>>> mapToCountryYearPowertrainMap() {
        Map<String, Map<String, Map<String, DataBitEVSale>>> result = new HashMap<>();
        for (DataBitEVSale bit : data) {
            if (!result.containsKey(bit.getCountry())) {
                result.put(bit.getCountry(), new TreeMap<>());
            }
            Map<String, Map<String, DataBitEVSale>> countryEntry = result.get(bit.getCountry());
            if (!countryEntry.containsKey(bit.getYear())) {
                countryEntry.put(bit.getYear(), new HashMap<>());
            }
            Map<String, DataBitEVSale> yearEntry = countryEntry.get(bit.getYear());
            yearEntry.put(bit.getPowertrain(), bit);
        }
        return result;
    }





}
