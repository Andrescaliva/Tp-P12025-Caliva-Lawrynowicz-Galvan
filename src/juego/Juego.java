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
	private Hechizos[] hechizosActivos = new Hechizos[5]; // Máximo 5 hechizos activos
	private int radioHechizo = 100;
	private int costoManaFuego = 1;
	private int costoManaHielo = 10;
	// Para saber si un hechizo está seleccionado esperando posición
private boolean hechizoSeleccionado = false;


private int totalEnemigosaDerrotar=50;
private int enemigosDerrotados=0;

// Guardar el tipo/color del hechizo seleccionado
private Color hechizoColorSeleccionado;
private enum TipoHechizo { FUEGO, HIELO }
private TipoHechizo hechizoTipoSeleccionado = null;

// estado de juego
private enum estadoJuego {JUGANDO,JUEGO_GANADO,JUEGO_PERDIDO}
private estadoJuego estadoActual;

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
		
		this.estadoActual=estadoJuego.JUGANDO;


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
		if(estadoActual==estadoJuego.JUEGO_PERDIDO) {
			entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, entorno.ancho(), entorno.alto(), 0, new Color(0, 0, 0, 150)); // Fondo oscuro semitransparente
            entorno.cambiarFont("Arial", 40, Color.RED);
            entorno.escribirTexto("¡GAME OVER!", entorno.ancho()/2 - 100, entorno.alto()/2);
            entorno.cambiarFont("Arial", 20, Color.WHITE);
            entorno.escribirTexto("Murciélagos derrotados: " + enemigosDerrotados, entorno.ancho()/2 - 120, entorno.alto()/2 + 50);
            return;
		}
		if (estadoActual == estadoJuego.JUEGO_GANADO) {
            entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, entorno.ancho(), entorno.alto(), 0, new Color(0, 150, 0, 150)); // Fondo verde oscuro semitransparente
            entorno.cambiarFont("Arial", 40, Color.YELLOW);
            entorno.escribirTexto("¡HAS GANADO!", entorno.ancho()/2 - 100, entorno.alto()/2);
            entorno.cambiarFont("Arial", 20, Color.WHITE);
            entorno.escribirTexto("Todos los murciélagos han sido derrotados.", entorno.ancho()/2 - 180, entorno.alto()/2 + 50);
            return; // Detiene toda la lógica del juego
        }
		
		


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
player.dibujarVida(entorno);
player.dibujarMana(entorno);
entorno.escribirTexto("Vida: " + player.getVida(), panelX + 20, 40);
entorno.escribirTexto("Hechizo 1: Fuego", panelX + 20, 80);
entorno.escribirTexto("Hechizo 2: Congelar", panelX + 20, 110);
entorno.escribirTexto("Mana: " + player.getMana(), panelX + 20, 60);
entorno.escribirTexto("Eenemigos derrotados"+enemigosDerrotados, panelX+20 ,80);//Contador de enemigos
entorno.escribirTexto("Total a derrotar"+totalEnemigosaDerrotar, panelX+20 , 100);

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
                enemigosDerrotados++;
                if(enemigosDerrotados >=totalEnemigosaDerrotar){
                    estadoActual = estadoJuego.JUEGO_GANADO;
                }
            }
        }
    }



boolean mousePresionado = entorno.estaPresionado(entorno.BOTON_IZQUIERDO);
int mouseX = entorno.mouseX();
int mouseY = entorno.mouseY();

if (mousePresionado) {
    // Clic en botón FUEGO
    if (mouseX >= botonFuegoX && mouseX <= botonFuegoX + botonAncho &&
        mouseY >= botonFuegoY && mouseY <= botonFuegoY + botonAlto) {

        if (player.tieneMana(costoManaFuego)) {
            hechizoSeleccionado = true;
            hechizoColorSeleccionado = Color.RED;
            hechizoTipoSeleccionado = TipoHechizo.FUEGO;
        }
    }

    // Clic en botón HIELO
    else if (mouseX >= botonHieloX && mouseX <= botonHieloX + botonAncho && mouseY >= botonHieloY && mouseY <= botonHieloY + botonAlto) {

        if (player.tieneMana(costoManaHielo)) {
            hechizoSeleccionado = true;
            hechizoColorSeleccionado = Color.CYAN;
            hechizoTipoSeleccionado = TipoHechizo.HIELO;
        }
    }

    // Si ya se seleccionó un hechizo y el clic NO fue sobre un botón
    else if (hechizoSeleccionado && mouseX < entorno.ancho() * 0.8) {
        // Verificamos y lanzamos el hechizo según el tipo
        if (hechizoTipoSeleccionado == TipoHechizo.FUEGO && player.tieneMana(costoManaFuego)) {
            lanzarHechizo(mouseX, mouseY, radioHechizo, Color.RED);
            player.gastarMana(costoManaFuego);
        } else if (hechizoTipoSeleccionado == TipoHechizo.HIELO && player.tieneMana(costoManaHielo)) {
            lanzarHechizo(mouseX, mouseY, radioHechizo, Color.CYAN);
            player.gastarMana(costoManaHielo);
        }

        // Limpiar selección
        hechizoSeleccionado = false;
        hechizoTipoSeleccionado = null;
    }
}



for (int i = 0; i < hechizosActivos.length; i++) {
    Hechizos h = hechizosActivos[i];

    if (h != null && h.estaActivo()) {
        h.actualizar();
        h.dibujar(entorno);

        for (int j = 0; j < murcielago.length; j++) {
            if (murcielago[j] != null && h.afecta(murcielago[j].getX(), murcielago[j].getY())) {
                murcielago[j] = null;
                enemigosDerrotados++;
                if(enemigosDerrotados>=totalEnemigosaDerrotar){
                    estadoActual=estadoJuego.JUEGO_GANADO;
                }
            }
        }
    }
}
if (hechizoSeleccionado) {
    entorno.escribirTexto("Hechizo seleccionado: " + hechizoTipoSeleccionado, botonFuegoX, botonHieloY + 50);
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
	

private void lanzarHechizo(double x, double y, int radio, Color color) {
    for (int i = 0; i < hechizosActivos.length; i++) {
        if (hechizosActivos[i] == null || !hechizosActivos[i].estaActivo()) {
            hechizosActivos[i] = new Hechizos(x, y, radio, color);
            break;
        }
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
