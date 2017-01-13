package game.board;

import game.ships.BattleShip;

class GridElement {
    private boolean marked = false;
    private BattleShip ship;

    GridElement() {
        this.ship = null;
    }

    GridElement(BattleShip ship) {
        this.ship = ship;
    }

    void mark() {
        this.marked = true;

        if (this.ship != null) {
            ship.takeDamage();
        }
    }

    BattleShip getShip() {
        return ship;
    }

    void setShip(BattleShip ship) {
        this.ship = ship;
    }

    boolean isMarked() {
        return this.marked;
    }

    private void damageShip() {
        if (this.ship != null) {
            this.ship.takeDamage();
        }
    }
}
