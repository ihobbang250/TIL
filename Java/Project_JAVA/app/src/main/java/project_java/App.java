package project_java;

import project_java.dao.DataDao;
import project_java.data.Data;
import java.util.*;

//권역별 인터페이스 처리
interface Continent {
    void combinationInfo();
}
// 앱 실행시 메뉴화면 출력클래스
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

// 개별 국가 Data 클래스
// 나라 클래스 데이터 클래스 타입으로 객체를 받아온다
// 데이터클래스를 통해 인스턴스 접근
class Country {
    public Data obj;
    String name;

    public Country(){}

    public Country(Data obj) {
        this.obj = obj;
    }

    // 받은 나라 객체의 인스턴스(정보)에 접근한다. 권역별 처리
    public void info() {
        System.out.println("평균 소비 시간: " + obj.getMeanTime());
        System.out.println("평균 소비 비용: " + obj.getMeanCost());
        System.out.println("TOP1 시간소비 컨텐츠: " + obj.getHotTimeContent());
        System.out.println("TOP1 비용소비 컨텐츠: " + obj.getHotCostContent());
    }
}

// 여러 국가 Data 클래스
// 나라 객체를 배열 형태로 받아서 처리한다
// 여러 나라의 정보를 여러 연산 과정을 통해 평균비용등을 산출함
class MoreCountry extends Country implements Continent{
    
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

        String[] contents = {"영화", "예능", "패션", "드라마", "뷰티", "게임"}; //컨텐츠 목록
        int[] count = {0, 0, 0, 0, 0, 0}; // 각 컨텐츠가 몇 번 카운트가 되었는지 기록

        while (list[i] != null) { //배열 안에 객체가 없을 때까지 진행
            sumCost += list[i].getMeanCost(); // 평균 비용을 계산하기 위해 전체 합을 구함
            sumTime += list[i].getMeanTime();
            // 객체의 컨텐츠가 컨텐츠 목록과 같다면 카운트를 증가시킴
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
        // 컨텐츠 카운트 중 최댓값을 구한다
        for (int j: count) {
            maxCount = Math.max(j, maxCount);
        }
        // 최댓값이 있는 인덱스를 구한다
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
        /* 데이터 다오 객체를 통해 데이터셋을 읽음과 동시에
        해쉬 맵에 저장, key:나라 value:나라의 데이터
        */
        DataDao dataDao = new DataDao();
        HashMap<String, Data> map = new HashMap<String, Data>();
        map = dataDao.getDataMap();
        final int SIZE = map.size(); // 데이터의 사이즈 18개 나라
        String[] inputList = new String[SIZE];
        Data[] countryList = new Data[SIZE];
        Data[] globalList = new Data[SIZE + 1];

        /*
        실행부
        숫자를 입력을 받아 실행할 메뉴를 고르게 한 후
        해당 동작 수행
         */
        while (true) {
            int choice = 0;
            showMenu show = new showMenu();
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            sc.nextLine();
            try { //없는 국가를 조회할 시 예외처리
                switch(choice) { // 실행 선택 반복문
                    case 0:
                        System.out.println(map.keySet());
                        break;
                    case 1:
                        System.out.print("국가를 입력하세요: ");
                        String s = sc.next();
                        System.out.println("국가: " + s);
                        Data temp = map.get(s); //문자열로 받은 국가의 키값을 통해 데이터를 조회
                        Country country1 = new Country(temp); //국가 클래스
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
                                moreObj.getMeanCost(); // 잘못된 국가를 입력시 오류가 발생
                                countryList[j] = moreObj; // 리스트 형태로 객체를 저장
                                j++;
                            }
                            MoreCountry countries = new MoreCountry(countryList);
                            countries.combinationInfo();
                            countryList = new Data[SIZE]; // 리스트 초기화
                            break;
                        } catch (Exception e) { //여러 국가 합산시 잘못된 국가 예외처리
                            System.out.println("잘못된 국가 입력");
                            countryList = new Data[SIZE];
                            break;
                        }
                    case 3:
                        int k = 0;
                        for (String i : map.keySet()) { //모든 키값을 순회한다 -> 모든 국가 순회
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
                    default: //메뉴 외의 번호 처리
                        System.out.println("잘못된 번호입니다");
                        break;
            }
            } catch (RuntimeException e) {
                System.out.println("없는 국가 Data 조회");
            }
        }

    }
}
