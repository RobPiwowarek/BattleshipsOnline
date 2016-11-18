package game.ships;

public class Size {
    private int length;
    private boolean isHorizontal;

    Size(int length, boolean isHorizontal) {
        this.length = length;
        this.isHorizontal = isHorizontal;
    }

    public int getLength() {
        return this.length;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

}
