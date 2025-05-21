package juego;

import java.awt.Color;
import entorno.Entorno;

public class Roca {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private Color color;

    public Roca(double x, double y) {
        this.x = x;
        this.y = y;
        this.ancho = 30;
        this.alto = 30;
        this.color = Color.GRAY;
    }

    public void dibujar(Entorno e) {
        e.dibujarRectangulo(x, y, ancho, alto, 0, color);
    }

public boolean colisionaCon(Player player) {
    return (
        this.x - this.ancho / 2 < player.getX() + player.getAncho() / 2 &&
        this.x + this.ancho / 2 > player.getX() - player.getAncho() / 2 &&
        this.y - this.alto / 2 < player.getY() + player.getAlto() / 2 &&
        this.y + this.alto / 2 > player.getY() - player.getAlto() / 2
    );
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