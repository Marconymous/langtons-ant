package simulation;

public class Tile {
    private int startX;
    private int startY;
    private boolean isBlack = false;

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

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean alive) {
        isBlack = alive;
    }
}
