package Modelo;

public class Square {
	protected Position cords;
	private IPiece piece;
	private boolean isNull;

	public Square(Position cord) {
		this.cords = cord;
		this.isNull=true;
	}

	public Square(Position cord,IPiece p){
		this.cords = cord;
		this.isNull=false;
		this.piece=p;
	}

	public void setPiece(IPiece piece) {
		this.piece = piece;
		this.piece.setPosition(cords);
		this.isNull=false;
	}

	public void resetSquare() {
		this.piece = null;
		this.isNull=true;
	}

	public Position getPosition() {
		return this.cords;
	}

	public IPiece getPiece() {
		if(this.isNull==true){
			return null;
		}else{
			return this.piece;
		}
		
	}

	@Override
    public String toString() {
        return "Square{" +
                "cords=" + cords +
                ", piece=" + piece +
                '}';
    }
}
