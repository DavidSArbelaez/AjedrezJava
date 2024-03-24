package Controlador;

import Modelo.Modelo;

public class ControladorTurnos {
	private boolean endGame=false;
	private Modelo modelo = new Modelo();
	private int turnNum;
	String tablero[][]=null;
	
	public ControladorTurnos() {
		this.turnNum=0;
		this.modelo.startGame();
		this.tablero = modelo.getBoard();
	}
	
	public String[][] obtenerPiezasFormatohtml(){
		
		if (tablero.equals(null)) {
			return null;
		}
		
	    String[][] tableroHtml = new String[8][8];
	    for (int i = 0; i < tablero.length; i++) {
	        for (int j = 0; j < tablero[i].length; j++) {
	            if(!tablero[i][j].equals("")) {
	                switch (tablero[i][j]) {
	                    case "black_pawn":
	                        tableroHtml[i][j] = "&#9823;";
	                        break;
	                    case "black_rock":
	                        tableroHtml[i][j] = "&#9820;";
	                        break;
	                    case "black_knight":
	                        tableroHtml[i][j] = "&#9822;";
	                        break;
	                    case "black_bishop":
	                        tableroHtml[i][j] = "&#9821;";
	                        break;
	                    case "black_queen":
	                        tableroHtml[i][j] = "&#9819;";
	                        break;
	                    case "black_king":
	                        tableroHtml[i][j] = "&#9818;";
	                        break;
	                    case "white_pawn":
	                        tableroHtml[i][j] = "&#9817;";
	                        break;
	                    case "white_rock":
	                        tableroHtml[i][j] = "&#9814;";
	                        break;
	                    case "white_knight":
	                        tableroHtml[i][j] = "&#9816;";
	                        break;
	                    case "white_bishop":
	                        tableroHtml[i][j] = "&#9815;";
	                        break;
	                    case "white_queen":
	                        tableroHtml[i][j] = "&#9813;";
	                        break;
	                    case "white_king":
	                        tableroHtml[i][j] = "&#9812;";
	                        break;
	                    default:
	                        throw new IllegalArgumentException("Unexpected value: " + tablero[i][j]);
	                }
	            }
	            else {
	            	tableroHtml[i][j]="_";
	            }
	        }
	    }
	    return tableroHtml;
	}

	
	public boolean turn(int fromCol, int fromRow, int toCol, int toRow) {
		Boolean isValidTurn = false;
		System.out.println("From:" + fromRow + " " + fromCol);
		System.out.println("To:" + toRow + " " + toCol);
		if (toCol != fromCol || toRow != fromRow) {
			isValidTurn = this.modelo.Turn(turnNum, fromCol, fromRow, toCol, toRow);
			if (isValidTurn) {
				System.out.println("Movimiento válido. La pieza se ha movido.");
				this.turnNum = this.turnNum + 1;
				modelo.erracePiece(fromRow, fromCol);
			} else {
				if(modelo.endGame){
					this.endGame = true;
				}
				System.out.println("Movimiento no válido. Inténtelo de nuevo.");
			}
		} else {
			System.out.println("Debe ser una pocisión diferente");
		}
		if (isValidTurn) {
			tablero = modelo.getBoard();
			System.out.println("Tablero pos turno");
		}
		return isValidTurn;
	}

}
