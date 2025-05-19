package juego;

import java.awt.Color;

import entorno.Entorno;

public class Player {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private Color color;

    // Dirección del jugador (-1: izquierda, 1: derecha)
    private int direccion;

    // Constructor
    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        this.ancho = 10;
        this.alto = 30;
        this.color = Color.yellow;
        this.direccion = 1; // Por defecto hacia la derecha
    }

    // Metodos
    public void dibujar(Entorno e) {
        e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
    }

    

public void moverIzquierda(Entorno e) {
    if (x - ancho / 2 > 0) { // Verifica que no pase el borde izquierdo
        x -= 4;
        direccion = -1;
    }
}

public void moverDerecha(Entorno e) {
    if (x + ancho / 2 < e.ancho()) { // Verifica que no pase el borde derecho
        x += 4;
        direccion = 1;
    }
}

public void moverArriba(Entorno e) {
    if (y - alto / 2 > 0) { // Verifica que no pase el borde superior
        y -= 4;
    }
}

public void moverAbajo(Entorno e) {
    if (y + alto / 2 < e.alto()) { // Verifica que no pase el borde inferior
        y += 4;
    }
}

public boolean estaFueraDePantalla(Entorno e) {
    return x - ancho / 2 < 0 || x + ancho / 2 > e.ancho() || y - alto / 2 < 0 || y + alto / 2 > e.alto();
}

    public void setPosicion(double nuevaX, double nuevaY) {
        this.x = nuevaX;
        this.y = nuevaY;
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
