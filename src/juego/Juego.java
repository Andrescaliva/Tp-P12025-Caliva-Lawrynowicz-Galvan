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
	private Roca[] roca;
	private int botonFuegoX = 650;
	private int botonFuegoY = 150;
	private int botonHieloX = 650;
	private int botonHieloY = 200;
	private int botonAncho = 120;
	private int botonAlto = 30;

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Titulo de TP - Grupo N - Apellido1 - Apellido2 -Apellido3", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		player = new Player(entorno.ancho() * 0.5, entorno.alto() * 0.7 - 20);
		murcielago = new Murcielago[5];
		for (int i = 0; i < murcielago.length; i++) {
			murcielago[i] = new Murcielago(entorno);
		}
		roca = new Roca[5]; 
		roca[0] = new Roca(200, 300);
		roca[1] = new Roca(400, 150);
		roca[2] = new Roca(600, 400);
		roca[3] = new Roca(300, 500);
		roca[4] = new Roca(500, 200);



		// Inicia el juego!
		this.entorno.iniciar();
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick(){
		// Procesamiento de un instante de tiempo
		// ...

    if (player.getVida() <= 0) {
        entorno.escribirTexto("¡Game Over!", entorno.ancho()/2 - 50, entorno.alto()/2);
        return;
    }
	// Dibujar panel derecho (HUD)
int panelX = (int)(entorno.ancho() * 0.8); // 80% de la pantalla
int panelAncho = entorno.ancho() - panelX;

entorno.dibujarRectangulo(panelX + panelAncho / 2, entorno.alto() / 2, panelAncho, entorno.alto(), 0, Color.DARK_GRAY);
entorno.dibujarRectangulo((int)(entorno.ancho() * 0.8), entorno.alto() / 2, 2, entorno.alto(), 0, Color.WHITE);

// Mostrar info de vida y hechizos
entorno.escribirTexto("Vida: " + player.getVida(), panelX + 20, 40);
entorno.escribirTexto("Hechizo 1: Fuego", panelX + 20, 80);
entorno.escribirTexto("Hechizo 2: Congelar", panelX + 20, 110);

// Dibujar botones de hechizos
// Botón de Fuego
entorno.dibujarRectangulo(botonFuegoX + botonAncho/2, botonFuegoY + botonAlto/2, botonAncho, botonAlto, 0, Color.RED);
entorno.escribirTexto("Lanzar Fuego", botonFuegoX + 10, botonFuegoY + 20);

// Botón de Hielo
entorno.dibujarRectangulo(botonHieloX + botonAncho/2, botonHieloY + botonAlto/2, botonAncho, botonAlto, 0, Color.CYAN);
entorno.escribirTexto("Lanzar Hielo", botonHieloX + 10, botonHieloY + 20);



for (Roca roca : roca) {
    roca.dibujar(entorno);
}

    player.dibujar(entorno);
    player.dibujarVida(entorno);

    for (int i = 0; i < murcielago.length; i++) {
        if (murcielago[i] == null) {
            murcielago[i] = new Murcielago(entorno);
        } else {
            murcielago[i].moverPersiguiendo(player, entorno);
            murcielago[i].dibujar(entorno);

            if (murcielago[i].colisionaCon(player)) {
                System.out.println("¡Colisión con murciélago " + i + "!");
                player.reducirVida();
                murcielago[i] = null;
            }
        }
    }
	

    // Movimiento del jugador
if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
    player.moverIzquierda(entorno);
    if (colisionaConAlgunaRoca()) player.moverDerecha(entorno); // revierte si choca
}
if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
    player.moverDerecha(entorno);
    if (colisionaConAlgunaRoca()) player.moverIzquierda(entorno);
}
if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
    player.moverArriba(entorno);
    if (colisionaConAlgunaRoca()) player.moverAbajo(entorno);
}
if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
    player.moverAbajo(entorno);
    if (colisionaConAlgunaRoca()) player.moverArriba(entorno);
}
}
	

private boolean colisionaConAlgunaRoca() {
    for (Roca roca : roca) {
        if (player.colisionaCon(roca)) {
            return true;
        }
    }
    return false;
}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
