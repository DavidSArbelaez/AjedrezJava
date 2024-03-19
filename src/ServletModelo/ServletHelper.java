package Modelo;

import java.io.PrintWriter;

public class ServletHelper {
	    public static void generarContenidoHTML(PrintWriter out) {
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Bienvenido</title>");
	        out.println("<script>");
	        out.println("function iniciarJuego() {");
	        out.println("   // Aquí puedes realizar alguna acción, como redireccionar a otra página");
	        out.println("   window.location.href = 'otra-pagina.html';");
	        out.println("}");
	        out.println("</script>");
	        out.println("</head>");
	        out.println("<body>");

	        // Mensaje de bienvenida
	        out.println("<h1>Bienvenido</h1>");

	        // Cargar imagen desde la carpeta "media"
	        out.println("<img src='media/menu_ventana.jpeg' alt='Menu de juego'>");

	        // Botón para iniciar el juego con evento onclick
	        out.println("<button onclick='iniciarJuego()'>Iniciar Juego</button>");

	        // Elementos decorativos (por ejemplo, una línea horizontal)
	        out.println("<hr>");

	        out.println("</body>");
	        out.println("</html>");
	    
	}
}
