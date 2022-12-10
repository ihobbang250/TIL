package project_java.dao;

import project_java.data.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class DataDao {

    private static final String DELIMITER = ",";
    private static final String DATA_PATH = "app\\src\\main\\resources\\";
    private static final String DATA_FILE_NAME = "tt.csv";
    private static HashMap<String, Data> dataMap;
    private List<String> exceptionList;

    public DataDao() {
        if (dataMap == null) readData();
    }

    public HashMap<String, Data> getDataMap() {
        return dataMap;
    }

    public List<String> getExceptionList() {
        return exceptionList;
    }
    
    private void readData() {
        String filePath = System.getProperty("user.dir") + "\\" +DATA_PATH + DATA_FILE_NAME;
        dataMap = new LinkedHashMap<>();
        exceptionList = new ArrayList<String>();
        String[] vars;

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            int increment = 0;
            while ((line = br.readLine()) != null) {
                increment++;
                if (increment < 2) continue;
 
                vars = line.split(DELIMITER);
 
                // Country, Mean Time, Mean Cost, Hot_time_content, Hot_Cost_content
                String country = vars[0].trim();
                int meanTime = Integer.parseInt(vars[4].trim());
                int meanCost = Integer.parseInt(vars[2].trim());
                String hotTimeContent = vars[3].trim();
                String hotCostContent = vars[1].trim();

                dataMap.put(country, new Data(meanTime, meanCost, hotTimeContent, hotCostContent));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
