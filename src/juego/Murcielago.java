package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Murcielago {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private Color color;
    private int direccionX; // Dirección horizontal (-1 izquierda, 1 derecha)
    private int direccionY; // Dirección vertical (-1 arriba, 1 abajo)
    private double velocidad;
    private int vidaActual;
    private final int VIDA_MAXIMA=1;


    // Constructor
public Murcielago(Entorno e) {
    Random random = new Random();
    this.ancho = 30;
    this.alto = 10;
    this.color = Color.GREEN;
    this.velocidad = 0.5 + random.nextDouble(); // Velocidad aleatoria
    this.vidaActual=VIDA_MAXIMA;

    int limiteX = (int)(e.ancho() * 0.75); // límite horizontal para evitar el panel derecho
    int limiteY = e.alto();

    int borde = random.nextInt(4);
    switch (borde) {
        case 0: // Izquierda
            this.x = 0;
            this.y = random.nextInt(limiteY);
            this.direccionX = 1;
            this.direccionY = 0;
            break;
        case 1: // Derecha (ajustada para que no aparezca en HUD)
            this.x = limiteX - 1; // justo antes del HUD
            this.y = random.nextInt(limiteY);
            this.direccionX = -1;
            this.direccionY = 0;
            break;
        case 2: // Arriba
            this.x = random.nextInt(limiteX);
            this.y = 0;
            this.direccionX = 0;
            this.direccionY = 1;
            break;
        case 3: // Abajo
            this.x = random.nextInt(limiteX);
            this.y = limiteY - 1;
            this.direccionX = 0;
            this.direccionY = -1;
            break;
    }
}

   public Murcielago(double x, double y) {
	   this.x=x;
	   this.y=y;
	   this.ancho=30;
	   this.alto=10;
	   this.color=Color.GREEN;
	   this.velocidad=0.5;
	   this.vidaActual=VIDA_MAXIMA;
   }

    public void setPosicion(double nuevaX, double nuevaY) {
        this.x = nuevaX;
        this.y = nuevaY;
    }

    // Metodos
    public void dibujar(Entorno e) {
        e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
    }

public void moverPersiguiendo(Player jugador, Entorno e) {
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

//Metodo para que el muercielago reciba daño
public void recicibirDanio(int cantidad) {
	this.vidaActual-=cantidad;
	if(this.vidaActual<0) {
		this.vidaActual=0;
        this.color = Color.Orange; // Cambia el color a naranja al morir
	}
}

//Verifica si hay un muercielago activo
public boolean estaActivo() {
	return this.vidaActual>0;
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