package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import Modelo.Modelo;

/**
 * Servlet implementation class controladorLogica
 */
@WebServlet("/get")
public class ControladorEntrada extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControladorTurnos c= new ControladorTurnos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorEntrada() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar si el tablero no es nulo y tiene al menos una fila
    	response.setContentType("text/xml");
        if (c.obtenerPiezasFormatohtml() != null) {
            String[][] tableroHtml = c.obtenerPiezasFormatohtml();
            
         // Construir la cadena de texto HTML que representa el tablero
            StringBuilder htmlBuilder = new StringBuilder();

            for (String[] fila : tableroHtml) {
                for (String cuadrado : fila) {
                    htmlBuilder.append(cuadrado);
                }
                htmlBuilder.append("|");
            }
            String tableroTexto = htmlBuilder.toString();

            // Configurar la respuesta para enviar texto HTML
            response.setContentType("text/plain");
            
            // Obtener el objeto PrintWriter para escribir en la respuesta
            PrintWriter out = response.getWriter();

            // Escribir la cadena de texto HTML con el tablero en la respuesta
            out.println(tableroTexto);
        } else {
            response.getWriter().println("Error: No se pudo obtener el tablero en formato HTML");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		Modelo m = new Modelo();
		String pa=request.getParameter("a");
		String pb=request.getParameter("b");
		// Quitar guiones bajos (_) al principio de las variables
		System.out.println(pa+":"+pa.charAt(0)+pa.charAt(1));
		System.out.println(pb+":"+pb.charAt(0)+pb.charAt(1));
		out.println(c.turn(pa.charAt(1)- '0',pa.charAt(0)- '0',pb.charAt(1)- '0',pb.charAt(0)- '0'));
		doGet(request, response);
	}

}
