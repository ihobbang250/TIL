package project_java;

import project_java.dao.DataDao;
import project_java.data.Data;
import java.util.*;

class showMenu {
    public showMenu() {
        System.out.println("======================");
        System.out.println("[메뉴선택]");
        System.out.println("0. 조회 가능 국가");
        System.out.println("1. 개별 국가 Data 조회");
        System.out.println("2. 여러 국가 Data 조회");
        System.out.println("3. 글로벌 인기 컨텐츠");
        System.out.println("4. 종료");
        System.out.println("======================");
    }
}
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

    public void combinationInfo() {
        int i = 0;
        int sumCost = 0;
        int sumTime = 0;
        int meanTime = 0;
        int meanCost = 0;
        int maxCount = 0;
        int idx = 0;

        String[] contents = {"영화", "예능", "패션", "드라마", "뷰티", "게임"};
        int[] count = {0, 0, 0, 0, 0, 0};

        while (list[i] != null) {
            sumCost += list[i].getMeanCost();
            sumTime += list[i].getMeanTime();
            for(int j = 0; j < contents.length; j++) {
                if (list[i].getHotCostContent().equals(contents[j]) == true) {
                    count[j]++;
                }
                else if (list[i].getHotTimeContent().equals(contents[j]) == true) {
                    count[j]++;
                }
            }
            i++;
        }

        for (int j: count) {
            maxCount = Math.max(j, maxCount);
        }
        for (int k: count) {
            if (count[idx] == maxCount)
                break;
            idx++;
        }

        meanTime = sumTime / i;
        meanCost = sumCost / i;
        System.out.println(i+"개 국가의 평균 소비 시간: "+ meanTime);
        System.out.println(i+"개 국가의 평균 소비 비용: "+ meanCost);
        System.out.println(i+"개 국가의 인기 컨텐츠: "+ contents[idx]);
    }
}

public class App {
    public static void main(String[] args) {
        DataDao dataDao = new DataDao();
        HashMap<String, Data> map = new HashMap<String, Data>();
        map = dataDao.getDataMap();
        final int SIZE = map.size();
        String[] inputList = new String[SIZE];
        Data[] countryList = new Data[SIZE];
        Data[] globalList = new Data[SIZE + 1];

        while (true) {
            int choice = 0;
            showMenu show = new showMenu();
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            sc.nextLine();
            try {
                switch(choice) {
                    case 0:
                        System.out.println(map.keySet());
                        break;
                    case 1:
                        System.out.print("국가를 입력하세요: ");
                        String s = sc.next();
                        System.out.println("국가: " + s);
                        Data temp = map.get(s);
                        Country country1 = new Country(temp);
                        country1.info();
                        break;
                    case 2:
                        int j = 0;
                        System.out.print("국가를 입력하세요: ");
                        String s2 = sc.nextLine();
                        inputList = s2.split(" ");
                        try {
                            for (String i : inputList) {
                                Data moreObj = map.get(i);
                                moreObj.getMeanCost();
                                countryList[j] = moreObj;
                                j++;
                            }
                            MoreCountry countries = new MoreCountry(countryList);
                            countries.combinationInfo();
                            countryList = new Data[SIZE];
                            break;
                        } catch (Exception e) {
                            System.out.println("잘못된 국가 입력");
                            countryList = new Data[SIZE];
                            break;
                        }
                    case 3:
                        int k = 0;
                        for (String i : map.keySet()) {
                            Data totalObj = map.get(i);
                            globalList[k] = totalObj;
                            k++;
                        }
                        System.out.println("글로벌");
                        MoreCountry all_countries = new MoreCountry(globalList);
                        all_countries.combinationInfo();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("잘못된 번호입니다");
                        break;
            }
            } catch (RuntimeException e) {
                System.out.println("없는 국가 Data 조회");
            }
        }

    }
}
