interface Mineral {
    int getValue();
}

class Gold implements Mineral{
    public int getValue() {
        return 100;
    }
}

class Silver implements Mineral{
    public int getValue() {
        return 90;
    }
}

class MineralCalculator{
    int value = 0;
    
    public void add(Mineral mineral) {
        this.value += mineral.getValue();
    }

    public int getValue() {
        return this.value;
    }
}

public class MineralCalculatorExample {
    public static void main(String[] args) {
        MineralCalculator cal = new MineralCalculator();
        cal.add(new Gold());
        cal.add(new Silver());
        System.out.println(cal.getValue());
    }
}
