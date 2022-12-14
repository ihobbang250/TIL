package project_java.data;

public class Data {
    // Mean Time, Mean Cost, Hot_time_content, Hot_Cost_content

    private int meanTime; // 평균 이용 시간
    private int meanCost; // 평균 소모 비용
    private String hotTimeContent; // 가장 많은 시간을 사용한 컨텐츠
    private String hotCostContent; // 가장 많은 비용을 사용한 컨텐츠

    public Data() {
    }
    /*
    나라별 데이터를 저장하는 클래스
     */
    public Data(int meanTime, int meanCost, String hotTimeContent, String hotCostContent) {
        this.meanTime = meanTime;
        this.meanCost = meanCost;
        this.hotTimeContent = hotTimeContent;
        this.hotCostContent = hotCostContent;
    }

    public int getMeanTime() { return meanTime; }
    public void setMeanTime(int meanTime) {
        this.meanTime = meanTime;
    }

    public int getMeanCost() { return meanCost; }
    public void setMeanCost(int meanCost) {
        this.meanCost = meanCost;
    }

    public String getHotTimeContent() { return hotTimeContent; }
    public void setHotTimeContent(String hotTimeContent) {
        this.hotTimeContent = hotTimeContent;
    }

    public String getHotCostContent() { return hotCostContent; }
    public void setHotCostContent(String hotCostContent) {
        this.hotCostContent = hotCostContent;
    }

}
