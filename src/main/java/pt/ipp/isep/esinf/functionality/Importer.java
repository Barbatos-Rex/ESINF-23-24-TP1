package pt.ipp.isep.esinf.functionality;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pt.ipp.isep.esinf.data.DataBitChargers;
import pt.ipp.isep.esinf.data.DataBitEVSale;
import pt.ipp.isep.esinf.data.GPS;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Importer {


    public Set<DataBitEVSale> importEVSales() {
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("ev_sales.csv"));
        sc.nextLine();
        Set<DataBitEVSale> result = new HashSet<>();
        while (sc.hasNext()) {
            String rawLine = sc.nextLine();
            if (rawLine.trim().isEmpty()) {
                continue;
            }
            String[] line = rawLine.split(",");

            DataBitEVSale entry = new DataBitEVSale(line[0], line[1], line[2], Integer.parseInt(line[3]));
            result.add(entry);

        }
        return result;
    }

    public Set<DataBitChargers> importChargers() throws IOException {
        Set<DataBitChargers> result = new HashSet<>();
        XSSFWorkbook workBook = new XSSFWorkbook(getClass().getClassLoader().getResourceAsStream("carregadores_europa.xlsx"));
        XSSFSheet sheet = workBook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String superCharger = row.getCell(0).getStringCellValue();
            String street = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String state = row.getCell(3).getStringCellValue();
            String zip = row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING ? row.getCell(4).getStringCellValue() : String.valueOf(row.getCell(4).getNumericCellValue());
            String country = row.getCell(5).getStringCellValue();
            String stalls = String.valueOf(row.getCell(6).getNumericCellValue());
            String kW = String.valueOf(row.getCell(7).getNumericCellValue());
            String gps = row.getCell(8).getStringCellValue();
            String elevm = String.valueOf(row.getCell(9).getNumericCellValue());
            String status = row.getCell(10).getStringCellValue();


            DataBitChargers entry = new DataBitChargers(superCharger, street, city, state, zip, country, stalls, kW, GPS.fromStringPair(gps), elevm, status);
            result.add(entry);
        }
        return result;
    }


}
