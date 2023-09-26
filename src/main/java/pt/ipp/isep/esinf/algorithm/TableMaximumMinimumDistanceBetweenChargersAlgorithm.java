package pt.ipp.isep.esinf.algorithm;

import pt.ipp.isep.esinf.data.DataBitChargers;

import java.util.*;

public class TableMaximumMinimumDistanceBetweenChargersAlgorithm
        implements MaximumMinimumDistanceBetweenChargersAlgorithm {
    @Override
    public double calculate(Set<DataBitChargers> elements) {
        double max=Double.MIN_VALUE;
        Queue<DataBitChargers> queue = new ArrayDeque<>(elements);
        while (!queue.isEmpty()){
            DataBitChargers head = queue.poll();
            double minimum=Double.MAX_VALUE;
            for (DataBitChargers dataBitChargers : queue) {
                double distance = head.getGpsCoords().distance(dataBitChargers.getGpsCoords());
                if (distance<minimum){
                    minimum=distance;
                }
            }
            if (max<minimum){
                max=minimum;
            }
        }
        return max;
    }
}
