package game.ships;

public class BattleShip {
    private ShipAngle angle;
    private ShipType type;
    private int health;

    public BattleShip(ShipType type, ShipAngle angle) {
        this.type = type;
        this.angle = angle;
        this.health = type.getLength();
    }

    public void takeDamage() {
        if (health > 0) {
            --this.health;
        }
    }

    public ShipAngle getAngle() {
        return angle;
    }

    public ShipType getType() {
        return type;
    }

    public int getHealth() {
        return this.health;
    }

}
