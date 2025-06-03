package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Murcielago {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private double velocidad;
    private Image imgMurcielago;
    private boolean congelado = false;
    private int tiempoCongelado = 0;


    // Constructor
public Murcielago(Entorno e) {
    Random random = new Random();
    this.ancho = 30;
    this.alto = 10;
    this.velocidad = 0.5 + random.nextDouble(); // Velocidad aleatoria
    this.imgMurcielago = Herramientas.cargarImagen("imagenes/murcielago.png");

    int limiteX = (int)(e.ancho() * 0.75); // límite horizontal para evitar el panel derecho
    int limiteY = e.alto();

    int borde = random.nextInt(4);
    switch (borde) {
        case 0: // Izquierda
            this.x = 0;
            this.y = random.nextInt(limiteY);
            break;
        case 1: // Derecha (ajustada para que no aparezca en HUD)
            this.x = limiteX - 1; // justo antes del HUD
            this.y = random.nextInt(limiteY);
            break;
        case 2: // Arriba
            this.x = random.nextInt(limiteX);
            this.y = 0;

            break;
        case 3: // Abajo
            this.x = random.nextInt(limiteX);
            this.y = limiteY - 1;
            break;
    }
}

    public void congelar(int ticks) {
        this.congelado = true;
        this.tiempoCongelado = ticks;
    }

    public void actualizarEstado() {
    if (congelado) {
        tiempoCongelado--;
        if (tiempoCongelado <= 0) {
            congelado = false;
        }
    }
}

    public void setPosicion(double nuevaX, double nuevaY) {
        this.x = nuevaX;
        this.y = nuevaY;
    }

    // Metodos
    public void dibujar(Entorno e) {
        e.dibujarImagen(this.imgMurcielago, this.x, this.y, 0, 0.015);
    }

public void moverPersiguiendo(Player jugador, Entorno e) {
    if (congelado)return;
    double dx = jugador.getX() - this.x;
    double dy = jugador.getY() - this.y;
    double distancia = Math.sqrt(dx*dx + dy*dy);

    if (distancia != 0) {
        double nuevaX = this.x + (dx / distancia) * velocidad;
        double nuevaY = this.y + (dy / distancia) * velocidad;

        if (nuevaX + ancho / 2 < e.ancho() * 0.8) {
            this.x = nuevaX;
        }
        this.y = nuevaY;
    }
}

public boolean colisionaCon(Player jugador) {
    return (this.x < jugador.getX() + jugador.getAncho() &&
            this.x + this.ancho > jugador.getX() &&
            this.y < jugador.getY() + jugador.getAlto() &&
            this.y + this.alto > jugador.getY());
}





	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
    
}