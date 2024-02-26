package Controlador;
import Vista.Vista;
import Modelo.Modelo;

/**
 *
 * @author Labing
 */
public class controlador {
    Modelo modelo = new Modelo();
    Vista vista = new Vista();
    String tablero [][]; 
    int turnNum;
    
    public void startGame() {
    	this.modelo.startGame();
    	this.tablero = modelo.getBoard();
    	this.turnNum = 0;
    }
    
    public void turn() {
    	
    	//Se obtiene los datos de la vista
    	// los cuales son:
    	// - Columna actual
    	// - fila actual
    	// - Columna nueva
    	// - fila nueva
    	
    	int col=0,row=0,colNew=0,rowNew=0;
    	
    	this.modelo.Turn(turnNum, col, row,colNew,rowNew);
    	this.turnNum++;
    }
}
