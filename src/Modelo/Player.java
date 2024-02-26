
public class Player {
	private String Color;
	private Boolean KingMate;
	public Player(String color) {
		super();
		Color = color;
		KingMate = false;
	}
	
	/*
	 * Método que informa que el rey de este jugador a sido puesto en hacke o ha sido movido por lo que no puede hacer enroque
	 */
	public void setKingMate() {
		this.KingMate=true;
	}

	public String getColor() {
		return Color;
	}

	public  Boolean getKingMate() {
		return KingMate;
	}
	
	
	
}
