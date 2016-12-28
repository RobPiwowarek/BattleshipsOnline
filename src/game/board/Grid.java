package game.board;

public class Grid {
    private int x;
    private int y;
    private GridElement[][] elements;

    public Grid() {
        elements = new GridElement[this.x][this.y];
    }

    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
        this.elements = new GridElement[x][y];
    }

    public Grid(int size) {
        this.x = size;
        this.y = size;
        elements = new GridElement[size][size];
    }

    public Grid(GridElement[][] e) {
        this.elements = e;

        // TODO: check for GridElement[0][X]

        this.x = e[0].length;
        this.y = e.length;


        // set x, y of grid;
    }

    public Grid(Grid g) {
        this.x = g.x;
        this.y = g.y;
        this.elements = g.elements;
    }

    public GridElement getElement(int x, int y) {
        return elements[x][y];
    }

    public void setElement(int x, int y, GridElement g) {
        this.elements[x][y] = g;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


}
