
package com.mycompany.chess.Modelo;
public interface IPieceBuilder {
    PieceBuilder setColor(String color);
    PieceBuilder setCurrentPosition(Square currentPosition);
    Piece build();
}
