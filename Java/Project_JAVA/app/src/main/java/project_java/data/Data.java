package project_java.data;

public class Data {
    // Mean Time, Mean Cost, Hot_time_content, Hot_Cost_content

    private int meanTime;
    private int meanCost;
    private String hotTimeContent;
    private String hotCostContent;

    public Data() {
    }

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
