package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Roca {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private Image imgRoca;

    public Roca(double x, double y) {
        this.x = x;
        this.y = y;
        this.ancho = 30;
        this.alto = 30;
        this.imgRoca = Herramientas.cargarImagen("imagenes/roca.png");
    }

    public void dibujar(Entorno e) {
        e.dibujarImagen(this.imgRoca, this.x, this.y, 0, 0.033);
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