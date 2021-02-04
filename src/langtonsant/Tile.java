package langtonsant;

public class Tile {
    private int x;
    private int y;
    private boolean isOccupied = false;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean alive) {
        isOccupied = alive;
    }
}
