package langtonsant;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.*;

public class LogicHandler {
    private final GraphicsContext graphics;
    private final Tile[][] tiles = new Tile[200][200];
    private char rotation = 'u';
    private int currentX = tiles[0].length / 2;
    private int currentY = tiles.length / 2;
    private int lastX = tiles[0].length / 2;
    private int lastY = tiles.length / 2;
    private final int squareSize = 5;

    public LogicHandler(Canvas canvas) {
        graphics = canvas.getGraphicsContext2D();

        // Initialize Tiles
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = new Tile();
                tiles[y][x].setX(x * squareSize);
                tiles[y][x].setY(y * squareSize);
            }
        }
        tiles[currentY][currentX].setOccupied(true);

        graphics.setFill(WHITE);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public boolean move() {
        if (currentX < 1 || currentY < 1 || currentX >= tiles[0].length - 1 || currentY >= tiles.length - 1)
            return false;

        tiles[currentY][currentX].setOccupied(!tiles[currentY][currentX].isOccupied());

        int startX = tiles[currentY][currentX].getX();
        int startY = tiles[currentY][currentX].getY();

        if (tiles[lastY][lastX].isOccupied()) {
            graphics.setFill(getRandomGrayScaleColor());
        } else {
            graphics.setFill(WHITE);
        }
        graphics.fillRect(tiles[lastY][lastX].getX(), tiles[lastY][lastX].getY(), squareSize, squareSize);
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
        return true;
    }

    private Color getRandomGrayScaleColor() {
        float g = (float) (Math.random() + 1) / 4;
        return new Color(g, g, g, 1);
    }
}
