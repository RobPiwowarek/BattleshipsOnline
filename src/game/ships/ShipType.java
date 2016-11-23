package game.ships;

public enum ShipType {
    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    DESTROYER(2);

    private final int length;

    ShipType(int size){
        this.length = size;
    }

    public int getLength() {
        return length;
    }
}
