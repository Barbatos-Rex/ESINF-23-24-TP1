package pt.ipp.isep.esinf;

import pt.ipp.isep.esinf.data.DataBitChargers;
import pt.ipp.isep.esinf.data.DataBitEVSale;
import pt.ipp.isep.esinf.functionality.Importer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        Importer importer = new Importer();
        System.out.println();
        Set<DataBitChargers> chargers = importer.importChargers();
        Set<DataBitEVSale> sales = importer.importEVSales();
        chargers.forEach(System.out::println);
        System.out.println();
        sales.forEach(System.out::println);
        System.out.println();
        PrintWriter pw = new PrintWriter(new FileOutputStream("log.txt"),true);
        chargers.forEach(pw::println);
        pw.println("");
        sales.forEach(pw::println);

    }
}