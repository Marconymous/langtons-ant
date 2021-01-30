package simulation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import static javafx.scene.paint.Color.*;

public class LogicHandler {
    private final GraphicsContext graphics;
    private final Tile[][] tiles = new Tile[100][100];
    private char rotation = 'u';
    private int currentX = tiles[0].length / 2;
    private int currentY = tiles.length / 2;
    private int lastX = tiles[0].length / 2;
    private int lastY = tiles.length / 2;
    private final int squareSize = 10;

    public LogicHandler(Canvas canvas) {
        graphics = canvas.getGraphicsContext2D();

        // Declare all Tiles
        for (Tile[] line : tiles) {
            for (int currrent = 0; currrent < line.length; currrent++) {
                line[currrent] = new Tile();
            }
        }

        // Initialize Tiles
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x].setStartX(x * squareSize);
                tiles[y][x].setStartY(y * squareSize);
            }
        }
        tiles[currentY][currentX].setOccupied(true);
    }

    public void move() {
        if (currentX < 0 || currentY < 0 || currentX >= tiles[0].length || currentY >= tiles.length) return;

        tiles[currentY][currentX].setOccupied(!tiles[currentY][currentX].isOccupied());

        int startX = tiles[currentY][currentX].getStartX();
        int startY = tiles[currentY][currentX].getStartY();

        if (tiles[lastY][lastX].isOccupied()) {
            graphics.setFill(BLACK);
        } else {
            graphics.setFill(WHITE);
        }
        graphics.fillRect(tiles[lastY][lastX].getStartX(), tiles[lastY][lastX].getStartY(), squareSize, squareSize);
        graphics.setFill(RED);
        graphics.fillRect(startX, startY, squareSize, squareSize);

        lastX = currentX;
        lastY = currentY;

        switch (rotation) {
            case 'u':
                if (tiles[currentY - 1][currentX].isOccupied()) rotation = 'l';
                else rotation = 'r';
                currentY--;
                break;
            case 'r':
                if (tiles[currentY][currentX + 1].isOccupied()) rotation = 'u';
                else rotation = 'd';
                currentX++;
                break;
            case 'd':
                if (tiles[currentY + 1][currentX].isOccupied()) rotation = 'r';
                else rotation = 'l';
                currentY++;
                break;
            case 'l':
                if (tiles[currentY][currentX - 1].isOccupied()) rotation = 'd';
                else rotation = 'u';
                currentX--;
                break;
        }
    }
}
