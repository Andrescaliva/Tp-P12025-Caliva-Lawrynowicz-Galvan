package juego;

import java.awt.Color;

import entorno.Entorno;

public class Hechizo {
    private double x;
    private double y;
    private int radio;
    private Color color;
    private int duracion;
    private boolean activo;
    private int costoMana;

    public Hechizo(double x, double y, int radio, Color color, int costoMana) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
        this.duracion = 30; // dura 30 ticks (~0.5s)
        this.activo = true;
        this.costoMana = costoMana;
    }

    public void actualizar() {
        if (duracion > 0) {
            duracion--;
        } else {
            activo = false;
        }
    }

    public void dibujar(Entorno entorno) {
        if (activo) {
            entorno.dibujarCirculo(x, y, radio * 2, color);
        }
    }

    public boolean estaActivo() {
        return activo;
    }

    public boolean afecta(double px, double py) {
        return activo && Math.hypot(px - x, py - y) <= radio;
    }

    public Color getColor() {
      return this.color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public int getcostoMana() {
    	return costoMana;
    }

}