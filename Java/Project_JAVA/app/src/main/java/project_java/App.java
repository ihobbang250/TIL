package project_java;

import project_java.dao.DataDao;
import project_java.data.Data;
import java.util.*;

class Country {
    public Data obj;

    public Country(){}

    public Country(Data obj) {
        this.obj = obj;
    }

    public void info() {
        System.out.println("평균 소비 시간: " + obj.getMeanTime());
        System.out.println("평균 소비 비용: " + obj.getMeanCost());
        System.out.println("TOP1 시간소비 컨텐츠: " + obj.getHotTimeContent());
        System.out.println("TOP1 비용소비 컨텐츠: " + obj.getHotCostContent());
    }
}

class MoreCountry extends Country {
    
    public Data[] list;

    public MoreCountry(Data[] list) {
        this.list = list;
    }

    public void info() {
        int i = 0;
        int sumCost = 0;
        int sumTime = 0;
        int meanTime = 0;
        int meanCost = 0;

        while (list[i] != null) {
            sumCost += list[i].getMeanCost();
            sumTime += list[i].getMeanTime();
            i++;
        }
        meanTime = sumTime / i;
        meanCost = sumCost / i;
        System.out.println(i+"개 국가의 평균 소비 시간: "+ meanTime);
        System.out.println(i+"개 국가의 평균 소비 비용: "+ meanCost);
    }
}

public class App {
    public static void main(String[] args) {
        DataDao dataDao = new DataDao();
        HashMap<String, Data> map = new HashMap<String, Data>();
        map = dataDao.getDataMap();
        String[] inputList = new String[map.size()];
        Data[] conlist = new Data[map.size()];
        
        Data obj = map.get("중국");
        int j = 0;
        
        inputList[0] = "미국";
        inputList[1] = "중국";

        for (String i : inputList) {
            Data objtest = map.get(i);
            conlist[j] = objtest;
            j++; 
        }

        Country country = new Country(obj);
        country.info();
        MoreCountry cont = new MoreCountry(conlist);
        cont.info();

        System.out.println(obj.getMeanCost());
    }
}
