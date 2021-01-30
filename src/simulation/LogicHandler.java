package simulation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

public class LogicHandler {
    private final Canvas canvas;
    private GraphicsContext graphics;
    private final Tile[][] tiles = new Tile[100][100];
    private char rotation = 'u';
    private int currentX = 50;
    private int currentY = 50;

    public LogicHandler(Canvas canvas) {
        this.canvas = canvas;
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
                tiles[y][x].setStartX(x * 10);
                tiles[y][x].setStartY(y * 10);
            }
        }
        tiles[currentY][currentX].setBlack(true);
    }

    public void update() {
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                System.out.println("f");
            }
        }
        tiles[currentY][currentX].setBlack(!tiles[currentY][currentX].isBlack());

        int startX = tiles[currentY][currentX].getStartX();
        int startY = tiles[currentY][currentX].getStartY();
        if (tiles[currentY][currentX].isBlack()) {
            graphics.setFill(BLACK);
        } else {
            graphics.setFill(WHITE);
        }
        graphics.fillRect(startX, startY, 10, 10);

        switch (rotation) {
            case 'u':
                if (tiles[currentY - 1][currentX].isBlack()) rotation = 'l';
                else rotation = 'r';
                currentY--;
                break;
            case 'r':
                if (tiles[currentY][currentX + 1].isBlack()) rotation = 'u';
                else rotation = 'd';
                currentX++;
                break;
            case 'd':
                if (tiles[currentY + 1][currentX].isBlack()) rotation = 'r';
                else rotation = 'l';
                currentY++;
                break;
            case 'l':
                if (tiles[currentY][currentX - 1].isBlack()) rotation = 'd';
                else rotation = 'u';
                currentX--;
                break;
        }
    }
}
