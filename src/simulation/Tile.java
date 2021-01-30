package simulation;

public class Tile {
    private int startX;
    private int startY;
    private boolean isOccupied = false;

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean alive) {
        isOccupied = alive;
    }
}
