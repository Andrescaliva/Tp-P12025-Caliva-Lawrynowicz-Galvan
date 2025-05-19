package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	private Player player;
	private Murcielago[] murcielago;
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Titulo de TP - Grupo N - Apellido1 - Apellido2 -Apellido3", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		player = new Player(entorno.ancho() * 0.5, entorno.alto() * 0.7 - 20);
		murcielago = new Murcielago[5];
		for (int i = 0; i < murcielago.length; i++) {
			murcielago[i] = new Murcielago(i, i, entorno);
		}



		// Inicia el juego!
		this.entorno.iniciar();
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		player.dibujar(entorno);
		for (int i = 0; i < murcielago.length; i++) {
            murcielago[i].moverPersiguiendo(player); // Persigue al jugador
            murcielago[i].dibujar(entorno);
        }


		// movimiento del player
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			System.out.println("Desplazar hacia la izquierda");
			player.moverIzquierda(entorno);
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			System.out.println("Desplazar hacia la derecha");
			player.moverDerecha(entorno);
		}
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			System.out.println("Desplazar hacia arriba");
			player.moverArriba(entorno);
		}
		if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
			System.out.println("Desplazar hacia abajo");
			player.moverAbajo(entorno);
		}



	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
