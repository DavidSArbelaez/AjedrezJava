package Modelo;

public class Square {
	protected Position cords;
	private IPiece piece;

	public Square(Position cord, IPiece piece) {
		this.cords = cord;
		if (piece==null) {
			this.piece=null;
		}else {
			this.piece=piece;
		}
	}

	public void setPiece(IPiece piece) {
		this.piece = piece;
		this.piece.setPosition(cords);
	}

	public void resetSquare() {
		this.piece = null;
	}

	public Position getPosition() {
		return this.cords;
	}

	public IPiece getPiece() {
		return this.piece;
	}

	@Override
    public String toString() {
        return "Square{" +
                "cords=" + cords +
                ", piece=" + piece +
                '}';
    }
}
