package project_java.dao;

import project_java.data.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class DataDao {
    /*
    데이터셋을 불러와 처리하는 클래스
     */
    private static final String DELIMITER = ","; // csv는 콤마로 구분되어 있음
    private static final String DATA_PATH = "app\\src\\main\\resources\\";
    private static final String DATA_FILE_NAME = "tt.csv"; // 로우데이터 파일 경로 조회
    private static HashMap<String, Data> dataMap; //로우데이터를 key:국가 value:데이터 형태로 저장
    private List<String> exceptionList;

    public DataDao() {
        if (dataMap == null) readData();
    } // 데이터가 없을 시 로우데이터를 불러옴

    public HashMap<String, Data> getDataMap() {
        return dataMap;
    } // 접근자

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
                if (increment < 2) continue; // 맨위 행에 대한 헤더부분은 저장 안함
 
                vars = line.split(DELIMITER);
 
                // Country, Mean Time, Mean Cost, Hot_time_content, Hot_Cost_content
                String country = vars[0].trim(); // 구분자 기준 데이터 필터링하여 변수에 저장
                int meanTime = Integer.parseInt(vars[4].trim());
                int meanCost = Integer.parseInt(vars[2].trim());
                String hotTimeContent = vars[3].trim();
                String hotCostContent = vars[1].trim();
                //데이터 클래스 객체에 묶어서 데이터들을 저장한다
                dataMap.put(country, new Data(meanTime, meanCost, hotTimeContent, hotCostContent));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
