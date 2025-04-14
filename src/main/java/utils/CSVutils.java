package utils;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVutils {
    public static void writeCSV(List<String[]> data, String fileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeNext(new String[]{"Title", "Price", "Rating", "Reviews"});
            for (String[] row : data) {
                writer.writeNext(row);
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}
