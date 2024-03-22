// Variable para almacenar la coordenada actual seleccionada
var coordenadaActual = null;

// Función para manejar el clic en un cuadrado del tablero
function cuadradoClicado(coordenada) {
	// Si no hay ninguna coordenada actual seleccionada, establecer la coordenada actual
	if (coordenadaActual === null) {
		coordenadaActual = coordenada;
	} else if (coordenada != coordenadaActual) {
		let c = coordenadaActual;
		let coordenadas = {
			a: coordenadaActual.substring(1),
			b: coordenada.substring(1)
		};
		$(document).ready(function() {
			// Define la URL del servlet al que deseas llamar
			var servletUrl = 'get';
			// Realiza la solicitud AJAX
			$.ajax({
				url: servletUrl,
				type: 'POST', // Puedes usar 'GET' o 'POST' según lo requiera tu servlet
				data: coordenadas,
				success: function(response) {
					console.log("Datos enviados:", coordenadas);
					console.log('Respuesta del servlet:', response);
					let isResponseTrue = response === true || response === 'true'; // Verifica si response es true o 'true'
					if (isResponseTrue) {
						console.log(isResponseTrue)
						moverPieza(c, coordenada);
					} else {
						console.log('La respuesta del servlet no es true.');
					}

				},
				error: function(xhr, status, error) {

					console.error('Error en la solicitud AJAX:', status, error);
				}
			});
		});
		// Reiniciar la coordenada actual seleccionada
		coordenadaActual = null;
	}
}

// Función para mover la pieza en el tablero
function moverPieza(coordActual, coordNueva) {
	console.log(coordActual)
	console.log(coordNueva)
	var piezaActual = document.getElementById(coordActual);
	var piezaNueva = document.getElementById(coordNueva);

	if (piezaActual && piezaNueva) {
		piezaNueva.innerHTML = piezaActual.innerHTML;
		piezaActual.innerHTML = '';
	} else {
		console.log('Error: Coordenadas no válidas.');
	}
}



// Obtener todos los elementos del tablero y agregar eventos de clic a cada uno
function asignarEventosACuadrados() {
	var tablero = document.getElementById('tablero');
	var cuadrados = tablero.getElementsByTagName('div');

	for (var i = 0; i < cuadrados.length; i++) {
		cuadrados[i].addEventListener('click', function() {
			var coordenada = this.id;
			cuadradoClicado(coordenada);
		});
	}
}

// Llamar a la función para asignar eventos cuando el documento esté listo
document.addEventListener('DOMContentLoaded', function() {
	asignarEventosACuadrados();
});


function iniciarTablero() {
	// Define la URL del servlet desde el que deseas obtener el tablero
	var servletUrl = 'get'; // Ajusta la URL según la configuración de tu servidor

	$.ajax({
		url: servletUrl,
		type: 'GET',
		dataType: 'text', // Indica que esperas recibir texto plano
		success: function(response) {
			console.log('Tablero obtenido:', response);
			// Procesa la cadena de texto para obtener la información de cada ficha
			var filas = response.trim().split('|'); // Divide la cadena por saltos de línea para obtener las filas del tablero
			console.log(filas.length + "Hola")
			// Recorre cada fila del tablero
			for (var i = 0; i < 8; i++) {
				var fila = filas[i];
				var expReg1 = /&#\d+;/g; // Expresión regular para encontrar códigos HTML de ajedrez
				var expReg2 = /_/g; // Expresión regular para encontrar guiones bajos

				var ocurrenciasExpReg1 = [];
				var ocurrenciasExpReg2 = [];

				var matchExpReg1 = -1;
				while ((matchExpReg1 = expReg1.exec(fila)) !== null) {
					ocurrenciasExpReg1.push({
						ocurrencia: matchExpReg1[0],
						indice: matchExpReg1.index + 1 // Índice según la cadena original completa
					});
				}

				var matchExpReg2;
				while ((matchExpReg2 = expReg2.exec(fila)) !== null) {
					ocurrenciasExpReg2.push({
						ocurrencia: matchExpReg2[0],
						indice: matchExpReg2.index + 1 // Índice según la cadena original completa
					});
				}
				matchExpReg1 -1;
				for (var j = 0; j < ocurrenciasExpReg1.length; j++) {
					var ocurrencia = ocurrenciasExpReg1[j];
					var pieza = document.getElementById("_" + i + ocurrencia.indice);
					console.log("_" + i + ocurrencia.indice)
					if (pieza) {
						console.log("Ocurrencia " + (j + 1) + " de ExpReg1:");
						console.log("Índice:", ocurrencia.indice-1);
						console.log("Ocurrencia:", ocurrencia.ocurrencia);
						pieza.innerHTML = ocurrencia.ocurrencia;
					}

				}

				// Acceder a todas las ocurrencias de ExpReg2
				for (var k = 0; k < ocurrenciasExpReg2.length; k++) {
					var ocurrencia = ocurrenciasExpReg2[k];
					var pieza = document.getElementById("_" + i + ocurrencia.indice);
					console.log("_" + i + ocurrencia.indice)	
					if (pieza) {
						pieza.innerHTML = "";
						console.log("Ocurrencia " + (k + 1) + " de ExpReg1:");
						console.log("Índice:", ocurrencia.indice-1);
						console.log("Ocurrencia:", ocurrencia.ocurrencia);
					}
				}
				
				matchExpReg1 = 0;

			}
		},
		error: function(xhr, status, error) {
			console.error('Error en la solicitud AJAX:', status, error);
		}
	});
};


// Llamar a la función initTable al cargar la página
window.onload = function() {
	iniciarTablero();
};