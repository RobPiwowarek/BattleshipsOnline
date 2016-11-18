package game.board;

import exception.AlreadyMarkedException;
import game.ships.BattleShip;

public class GridElement {
    private boolean marked = false;
    private BattleShip ship;

    public GridElement() {
        this.ship = null;
    }

    public GridElement(BattleShip ship) {
        this.ship = ship;
    }

    public void mark() throws AlreadyMarkedException {
        if (this.marked) throw new AlreadyMarkedException("");

        this.marked = true;

        if (this.ship != null){
            ship.takeDamage();
        }
    }

    public boolean isMarked() {
        return this.marked;
    }

    private void damageShip() {
        if (this.ship != null) {
            this.ship.takeDamage();
        }
    }
}
