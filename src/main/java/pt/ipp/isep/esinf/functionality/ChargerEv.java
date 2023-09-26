package pt.ipp.isep.esinf.functionality;

import pt.ipp.isep.esinf.data.DataBitChargers;
import pt.ipp.isep.esinf.data.DataBitEVSale;
import pt.ipp.isep.esinf.structs.Quota;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChargerEv {
    private Set<DataBitChargers> chargers;
    private Set<DataBitEVSale> evs;


    public ChargerEv(Set<DataBitChargers> chargers, Set<DataBitEVSale> evs) {
        this.chargers = chargers;
        this.evs = evs;
    }


    public Map<String, Quota> obtainQuotasOfYearUsingRatio(int year, double ratio) {
        Map<String, Set<DataBitEVSale>> sales = filterSalesByYearMapToSalesByCountry(year);
        Map<String, Set<DataBitChargers>> chargers = mapChargersToChargersByCountry();

        Map<String, Quota> result = new HashMap<>();


        Set<String> countries = new HashSet<>();
        countries.addAll(sales.keySet());
        countries.addAll(chargers.keySet());

        for (String country : countries) {
            if (!sales.containsKey(country) || !chargers.containsKey(country)) {
                continue;
            }

            Set<DataBitEVSale> countrySales = sales.get(country);
            Set<DataBitChargers> countryChargers = chargers.get(country);


            int stalls = 0;

            for (DataBitChargers countryCharger : countryChargers) {
                stalls += Integer.parseInt(countryCharger.getStalls());
            }

            int eletricVehicles = 0;

            for (DataBitEVSale countrySale : countrySales) {
                eletricVehicles += countrySale.getNumberOfVehicles();
            }

            Quota countryQuota = new Quota(stalls, eletricVehicles, ratio, country);
            result.put(country, countryQuota);
        }
        return result;
    }


    public Map<String, Set<DataBitEVSale>> filterSalesByYearMapToSalesByCountry(int year) {
        Map<String, Set<DataBitEVSale>> result = new HashMap<>();
        for (DataBitEVSale ev : evs) {
            if (Integer.parseInt(ev.getYear()) != year) {
                continue;
            }
            if (!result.containsKey(ev.getCountry())) {
                result.put(ev.getCountry(), new HashSet<>());
            }
            result.get(ev.getCountry()).add(ev);
        }
        return result;
    }

    public Map<String, Set<DataBitChargers>> mapChargersToChargersByCountry() {
        Map<String, Set<DataBitChargers>> result = new HashMap<>();
        for (DataBitChargers charger : chargers) {
            if (!result.containsKey(charger.getCountry())) {
                result.put(charger.getCountry(), new HashSet<>());
            }
            result.get(charger.getCountry()).add(charger);
        }
        return result;
    }


}
