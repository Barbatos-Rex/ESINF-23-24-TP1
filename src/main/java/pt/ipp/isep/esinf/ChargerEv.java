package pt.ipp.isep.esinf;

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


    }


    public Map<String, Set<DataBitEVSale>> filterChargersByYearMapToSalesByCountry(int year) {
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
