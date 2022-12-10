package project;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CsvToBeanBuilder;

public class App {
  
    public static void main(String[] args) throws IOException {
      String path = System.getProperty("user.dir");
      path += "\\" + "POPULAR.csv";

		  CSVReader csvReader = new CSVReader(new FileReader(path));
      String[] line;
      while ((line = csvReader.readNext()) != null) {
        System.out.println(String.join(",",line));
      }

    }
}