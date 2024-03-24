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
					//let isResponseTrue = response === true || response === 'true'; // Verifica si response es true o 'true'
					
					if (response.includes("true")) {
						iniciarTablero();
						location.reload();
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
			//console.log('Tablero obtenido:', response);
			// Procesa la cadena de texto para obtener la información de cada ficha
			var filas = response.trim().split('|'); // Divide la cadena por saltos de línea para obtener las filas del tablero
			// Recorre cada fila del tablero
			for (var i = 0; i < 8; i++) {
				let fila = filas[i];
				//console.log("fila " + i + ": " + fila)
				var expReg1 = /&#\d+;/g; // Expresión regular para encontrar códigos HTML de ajedrez
				var expReg2 = /_/g; // Expresión regular para encontrar guiones bajos

				let ocurrenciasExpReg1 = [];
				let ocurrenciasExpReg2 = [];
				let ocurrenciasAmbas = [];

				var matchExpReg1;
				while ((matchExpReg1 = expReg1.exec(fila)) !== null) {
					ocurrenciasExpReg1.push({
						ocurrencia: matchExpReg1[0],
						indice: matchExpReg1.index // Asigna el índice de la ocurrencia en la cadena
					});
					ocurrenciasAmbas.push({
						ocurrencia: matchExpReg1[0],
						indice: matchExpReg1.index,
						tipo: 'expReg1' // Marca la coincidencia como perteneciente a la expresión regular 1
					});
				}

				var matchExpReg2;
				while ((matchExpReg2 = expReg2.exec(fila)) !== null) {
					ocurrenciasExpReg2.push({
						ocurrencia: matchExpReg2[0],
						indice: matchExpReg2.index // Asigna el índice de la ocurrencia en la cadena
					});
					ocurrenciasAmbas.push({
						ocurrencia: matchExpReg2[0],
						indice: matchExpReg2.index,
						tipo: 'expReg2' // Marca la coincidencia como perteneciente a la expresión regular 2
					});
				}// Ordenar el array de coincidencias de ambas expresiones regulares por índice de manera ascendente
				ocurrenciasAmbas.sort((a, b) => a.indice - b.indice);
				// Asignar nuevos índices de forma secuencial
				let contador = 0;
				ocurrenciasAmbas.forEach((ocurrencia, index) => {
					if (index === 0 || ocurrencia.indice !== ocurrenciasAmbas[index - 1].indice) {
						contador++;
					}
					ocurrencia.indice = contador - 1;
				});

				// Actualizar los índices en los arrays originales según el orden resultante en el array de coincidencias de ambas
				
				let expR1=0,expR2=0;
				for (let j = 0; j < ocurrenciasAmbas.length; j++) {
					let ocurrencia = ocurrenciasAmbas[j];
					if (ocurrencia.tipo === 'expReg1') {
						
						ocurrenciasExpReg1[expR1].indice = ocurrencia.indice;
						expR1++;
					} else if (ocurrencia.tipo === 'expReg2') {
						ocurrenciasExpReg2[expR2].indice = ocurrencia.indice;
						expR2++;
					}

				}

				/*console.log("*********************LINEA " + i + "*********************")
				for (let j = 0; j < ocurrenciasAmbas.length; j++) {
					let ocurrencia = ocurrenciasAmbas[j];

					console.log("Coincidencia " + j + ":");
					console.log("Tipo:", ocurrencia.tipo);
					console.log("Ocurrencia:", ocurrencia.ocurrencia);
					console.log("Índice:", ocurrencia.indice);

				}
				console.log("*********************EXPREG1*********************")
				for (let j = 0; j < ocurrenciasExpReg1.length; j++) {
					let ocurrencia = ocurrenciasExpReg1[j];

					console.log("Coincidencia " + j + ":");
					console.log("Ocurrencia:", ocurrencia.ocurrencia);
					console.log("Índice:", ocurrencia.indice);

				}
				console.log("***************************************************************")
				console.log("*********************EXPREG2*********************")
				for (let j = 0; j < ocurrenciasExpReg2.length; j++) {
					let ocurrencia = ocurrenciasExpReg2[j];

					console.log("Coincidencia " + j + ":");
					console.log("Ocurrencia:", ocurrencia.ocurrencia);
					console.log("Índice:", ocurrencia.indice);

				}
				console.log("***************************************************************")*/


				for (let j = 0; j < ocurrenciasExpReg1.length; j++) {
					let ocurrencia = ocurrenciasExpReg1[j];
					let indice = ocurrencia.indice;
					let pieza = document.getElementById("_" + i + indice);
					/*console.log("_" + i + indice)*/
					if (pieza) {
						/*console.log("Ocurrencia " + j + " de ExpReg1:");
						console.log("Índice:", indice);
						console.log("Ocurrencia:", ocurrencia.ocurrencia);*/
						pieza.innerHTML = ocurrencia.ocurrencia;
					}

				}

				// Acceder a todas las ocurrencias de ExpReg2
				for (let k = 0; k < ocurrenciasExpReg2.length; k++) {
					let ocurrencia = ocurrenciasExpReg2[k];
					let indice = ocurrencia.indice;
					//console.log("indice:" + indice)
					let pieza = document.getElementById("_" + i + indice);
					//console.log("_" + i + indice)
					if (pieza) {
						pieza.innerHTML = "";
						/*console.log("Ocurrencia " + k + " de ExpReg2:");
						console.log("Índice:", indice);
						console.log("Ocurrencia:", ocurrencia.ocurrencia);*/
					}
				}

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