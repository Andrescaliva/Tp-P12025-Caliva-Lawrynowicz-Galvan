package juego;


import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
    private Image fondo;
	private Player player;
	private Murcielago[] murcielago;
	private Roca[] roca;
	private Hechizo[] hechizosActivos = new Hechizo[1]; // Máximo 1 hechizo activo
	private Boton botonFuego;
	private Boton botonHielo;
	private int costoManaFuego = 10;
	private int costoManaHielo = 0;
    private int murcielagosEliminados = 0;
	// Para saber si un hechizo está seleccionado esperando posición
    private boolean hechizoSeleccionado = false;
    private int contadorRecuperacion = 0;
    // Guardar el tipo/color del hechizo seleccionado
    private enum TipoHechizo { FUEGO, HIELO }
    private TipoHechizo hechizoTipoSeleccionado = null;


	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El camino de Gondolf - Grupo 2 - Caliva - Galvan - Lawrynowicz", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
        fondo = Herramientas.cargarImagen("imagenes/fondo.jpg");
		player = new Player(entorno.ancho() * 0.5, entorno.alto() * 0.7 - 20);
		murcielago = new Murcielago[5];
		for (int i = 0; i < murcielago.length; i++) {
			murcielago[i] = new Murcielago(entorno);
		}
		roca = new Roca[6]; 
		roca[0] = new Roca(200, 300);
		roca[1] = new Roca(400, 150);
		roca[2] = new Roca(600, 400);
		roca[3] = new Roca(300, 500);
		roca[4] = new Roca(500, 200);
        roca[5] = new Roca(110, 200);
        
        botonFuego = new Boton (650, 150, 120, 30, Color.RED, "Lanzar Fuego");
        botonHielo = new Boton(650, 200, 120, 30, Color.BLUE, "Lanzar Hielo");


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
    entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0, 0.5);
    
    if (player.getVida() <= 0) {
        entorno.cambiarFont("Arial", 30, Color.GREEN); 
        entorno.escribirTexto("¡Game Over!", entorno.ancho()/2 - 50, entorno.alto()/2);
        return;
    }
    if (murcielagosEliminados >= 10) {
    entorno.cambiarFont("Arial", 30, Color.GREEN); // 1 = Font.BOLD
    entorno.escribirTexto("¡Has ganado!", entorno.ancho()/2 - 50, entorno.alto()/2);
    return;
}

	// Dibujar panel derecho (HUD)
int panelX = (int)(entorno.ancho() * 0.8); // 80% de la pantalla
int panelAncho = entorno.ancho() - panelX;

entorno.dibujarRectangulo(panelX + panelAncho / 2, entorno.alto() / 2, panelAncho, entorno.alto(), 0, Color.DARK_GRAY);
entorno.dibujarRectangulo((int)(entorno.ancho() * 0.8), entorno.alto() / 2, 2, entorno.alto(), 0, Color.WHITE);
entorno.escribirTexto("Murciélagos eliminados: " + murcielagosEliminados, botonFuego.getX(), botonHielo.getY()+100);


// Mostrar info de vida y mana
player.dibujarVida(entorno, 650, 50);
player.dibujarMana(entorno, 650, 100);

// Dibujar botones de hechizos
// Botón de FUEGO
botonFuego.setColor(hechizoTipoSeleccionado == TipoHechizo.FUEGO ? Color.RED : Color.PINK);
botonFuego.Dibujar(entorno);

// Botón de HIELO
botonHielo.setColor(hechizoTipoSeleccionado == TipoHechizo.HIELO ? Color.BLUE : Color.CYAN);
botonHielo.Dibujar(entorno);

    for (Roca roca : roca) {
        roca.dibujar(entorno);
    }

    player.dibujar(entorno);
    contadorRecuperacion++;
    if (contadorRecuperacion >= 400) {
        player.recuperarMana(5); // Recupera 5 de mana cada 400 ticks 
        contadorRecuperacion = 0;
    }

    for (int i = 0; i < murcielago.length; i++) {
        if (murcielago[i] == null) {
            murcielago[i] = new Murcielago(entorno);
        } else {
            murcielago[i].actualizarEstado();
            murcielago[i].moverPersiguiendo(player, entorno);
            murcielago[i].dibujar(entorno);

            if (murcielago[i].colisionaCon(player)) {
                System.out.println("¡Colisión con murciélago " + i + "!");
                player.reducirVida();
                murcielago[i] = null;
            }
        }
    }



boolean mousePresionado = entorno.estaPresionado(entorno.BOTON_IZQUIERDO);
int mouseX = entorno.mouseX();
int mouseY = entorno.mouseY();

if (mousePresionado) {
    // Clic en botón FUEGO
    if (botonFuego.estaPresionado(mouseX, mouseY, mousePresionado)) {
    	
        if (player.tieneMana(costoManaFuego)) {
            hechizoSeleccionado = true;
            hechizoTipoSeleccionado = TipoHechizo.FUEGO;
        }
    }

    // Clic en botón HIELO
    else if (botonHielo.estaPresionado(mouseX, mouseY, mousePresionado)) {

        if (player.tieneMana(costoManaHielo)) {
            hechizoSeleccionado = true;
            hechizoTipoSeleccionado = TipoHechizo.HIELO;
        }
    }

    // Si ya se seleccionó un hechizo y el clic NO fue sobre un botón
    else if (hechizoSeleccionado && mouseX < entorno.ancho() * 0.8) {
        // Verificamos y lanzamos el hechizo según el tipo
        if (hechizoTipoSeleccionado == TipoHechizo.FUEGO && player.tieneMana(costoManaFuego)) {
            lanzarHechizo(mouseX, mouseY, 50, Color.RED,costoManaFuego);
            player.gastarMana(costoManaFuego);
        } else if (hechizoTipoSeleccionado == TipoHechizo.HIELO && player.tieneMana(costoManaHielo)) {
            lanzarHechizo(mouseX, mouseY, 50, Color.CYAN,costoManaHielo);
            player.gastarMana(costoManaHielo);
        }

        // Limpiar selección
        hechizoSeleccionado = false;
        hechizoTipoSeleccionado = null;
    }
}



for (int i = 0; i < hechizosActivos.length; i++) {
    Hechizo h = hechizosActivos[i];

    if (h != null && h.estaActivo()) {
        h.actualizar();
        h.dibujar(entorno);

        for (int j = 0; j < murcielago.length; j++) {
             if (murcielago[j] != null && h.afecta(murcielago[j].getX(), murcielago[j].getY())) {
                    if (h.getColor().equals(Color.RED)) {
                       murcielago[j] = null;
                       murcielagosEliminados++;
                } else if (h.getColor().equals(Color.CYAN)) {
                       murcielago[j].congelar(180);
            }
}
        }
    }
}
if (hechizoSeleccionado) {
    entorno.escribirTexto("Hechizo: " + hechizoTipoSeleccionado, botonFuego.getX(), botonHielo.getY() + 50);
}

	
    // Movimiento del jugador
if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
    player.moverIzquierda(entorno);
    if (colisionaConAlgunaRoca()) player.moverDerecha(entorno); 
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
	

private void lanzarHechizo(double x, double y, int radio, Color color, int costoMana) {
    for (int i = 0; i < hechizosActivos.length; i++) {
        if (hechizosActivos[i] == null || !hechizosActivos[i].estaActivo()) {
            hechizosActivos[i] = new Hechizo(x, y, radio, color,costoMana);
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
