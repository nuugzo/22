package com.example.hexgameapp22;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

public class HexagonTile extends Polygon {
    private int row;
    private int col;
    private GameController controller;

    public HexagonTile(int row, int col, GameController controller) {
        this.row = row;
        this.col = col;
        this.controller = controller;
        double radius = 15;
        for (int i = 0; i < 6; i++) {
            getPoints().addAll(
                    radius * Math.cos(Math.toRadians(i * 60)),
                    radius * Math.sin(Math.toRadians(i * 60))
            );
        }
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        setStrokeWidth(1);
        setStrokeType(StrokeType.INSIDE);
        setOnMouseClicked(e -> controller.handleTileClick(this));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPlayerColor(int player) {
        if (player == GameModel.PLAYER_ONE) {
            setFill(Color.RED);
        } else if (player == GameModel.PLAYER_TWO) {
            setFill(Color.BLUE);
        }
    }

    public void setBorder(Color color) {
        setStroke(color);
        setStrokeWidth(2);
    }
}

