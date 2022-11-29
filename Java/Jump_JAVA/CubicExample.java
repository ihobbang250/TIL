class Cubic {
    int line;
    int result;
    
    public void setLine(int line) {
        this.line = line;
    }

    public int getLine() {
        result = line * line * 6;
        return result;
    }
}


public class CubicExample{
    public static void main(String[] args) {
        
        Cubic[] c = new Cubic[6];

        for (int i = 0; i < c.length; i++) {
            c[i] = new Cubic();
            c[i].setLine(i);
            System.out.println("Cubic"+"["+i+"]"+" "+"면적은 "+c[i].getLine());
            }
    }
}