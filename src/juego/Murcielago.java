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


    // Constructor
        public Murcielago(double x, double y, Entorno e) {
        Random random = new Random();
        this.ancho = 10;
        this.alto = 30;
        this.color = Color.GREEN;
        this.velocidad = 0.5 + random.nextDouble() * 1; // Velocidad aleatoria ent
                // Aparece en un borde aleatorio
        int borde = random.nextInt(4);
        switch (borde) {
            case 0: // Izquierda
                this.x = 0;
                this.y = random.nextInt(e.alto());
                this.direccionX = 1;
                break;
            case 1: // Derecha
                this.x = e.ancho();
                this.y = random.nextInt(e.alto());
                this.direccionX = -1;
                break;
            case 2: // Arriba
                this.x = random.nextInt(e.ancho());
                this.y = 0;
                this.direccionY = 1;
                break;
            case 3: // Abajo
                this.x = random.nextInt(e.ancho());
                this.y = e.alto();
                this.direccionY = -1;
                break;
        }
    }



    public void setPosicion(double nuevaX, double nuevaY) {
        this.x = nuevaX;
        this.y = nuevaY;
    }

    // Metodos
    public void dibujar(Entorno e) {
        e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
    }

public void moverPersiguiendo(Player jugador) {
    // Determinar dirección hacia el jugador
    if (jugador.getX() > this.x) {
        this.x += velocidad; // Mueve hacia la derecha
    } else {
        this.x -= velocidad; // Mueve hacia la izquierda
    }

    if (jugador.getY() > this.y) {
        this.y += velocidad; // Mueve hacia abajo
    } else {
        this.y -= velocidad; // Mueve hacia arriba
    }
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