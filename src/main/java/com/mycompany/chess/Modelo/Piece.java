package com.mycompany.chess.Modelo;

import java.util.ArrayList;

public abstract class Piece {
    private String color;
    private Square currentPosition;

    public Piece(String color, Square currentPosition) {
        this.color = color;
        this.currentPosition = currentPosition;
    }

    public abstract boolean moveTo(Square square);

    public abstract ArrayList<Square> getValidMoves();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Square getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Square currentPosition) {
        this.currentPosition = currentPosition;
    }
}
