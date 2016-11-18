package game.ships;

public class BattleShip {
    private Size size;
    private Position position;
    private int health;

    public BattleShip(Size size, Position position) {
        this.size = size;
        this.position = position;
    }

    public Size getSize() {
        return this.size;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getHealth() {
        return this.health;
    }

    public void takeDamage() {
        if (health > 0) {
            --this.health;
        }
    }


}
