package com.example.hexgameapp22;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GameController {
    private int size;
    private GameModel model;
    private GridPane hexGrid;
    private HexGameApp view;

    public GameController(int size, GridPane hexGrid, HexGameApp view) {
        this.size = size;
        this.model = new GameModel(size);
        this.hexGrid = hexGrid;
        this.view = view;
        initializeBoard();
    }

    private void initializeBoard() {
        hexGrid.getChildren().clear();
        hexGrid.setHgap(2);
        hexGrid.setVgap(2);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                HexagonTile tile = new HexagonTile(row, col, this);
                int offsetCol = col + row;
                hexGrid.add(tile, offsetCol, row);
                if (col == 0) {
                    tile.setBorder(Color.RED);
                } else if (col == size - 1) {
                    tile.setBorder(Color.RED);
                } else if (row == 0) {
                    tile.setBorder(Color.BLUE);
                } else if (row == size - 1) {
                    tile.setBorder(Color.BLUE);
                }
            }
        }
    }

    public void setBoardSize(int newSize) {
        this.size = newSize;
        this.model = new GameModel(newSize);
        initializeBoard();
    }

    public void startGame() {
        model.reset();
        view.updateStatusLabel("Player 1's turn (Red)");
        initializeBoard();
    }

    public void handleTileClick(HexagonTile tile) {
        int row = tile.getRow();
        int col = tile.getCol();
        if (model.makeMove(row, col)) {
            tile.setPlayerColor(model.getCurrentPlayer());
            view.animateTilePlacement(tile);
            if (model.checkWinner()) {
                String winner = model.getCurrentPlayer() == GameModel.PLAYER_ONE ? "Player 1 (Red)" : "Player 2 (Blue)";
                view.updateStatusLabel(winner + " wins!");
                Line winningLine = new Line(0, 0, 800, 600);
                winningLine.setStroke(Color.GREEN);
                view.animateWinningLine(winningLine);
            } else {
                model.switchPlayer();
                view.updateStatusLabel("Player " + (model.getCurrentPlayer() == GameModel.PLAYER_ONE ? "1 (Red)" : "2 (Blue)") + "'s turn");
            }
        }
    }
}
