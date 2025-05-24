package juego;


import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;


public class Player {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private Color color;
    private int vida; // Vida del jugador
    private int mana;//Energia del jugador
    private Image imagenDerecha;
    private Image imagenIzquierda;
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
        this.vida = 3; // Vida inicial
        this.mana = 100;
        this.imagenDerecha = Herramientas.cargarImagen("imagenes/player-derecha.png");
        this.imagenIzquierda = Herramientas.cargarImagen("imagenes/player-izquierda.png");
    }

    // Metodos
public void dibujar(Entorno e) {
    Image imagenActual = (direccion == 1) ? imagenDerecha : imagenIzquierda;
    e.dibujarImagen(imagenActual, x, y, 0, 0.1); // 0.5 es escala, podés ajustarlo
}

public void dibujarVida(Entorno e) {
    int barraAncho = 60;
    int barraAlto = 10;
    int vidaMax = 3;

    // Fondo de barra
    e.dibujarRectangulo(50, 20, barraAncho, barraAlto, 0, Color.RED);

    // Vida actual (proporcional)
    int vidaActualAncho = (int)((vida / (double)vidaMax) * barraAncho);
    e.dibujarRectangulo(50 - (barraAncho - vidaActualAncho)/2.0, 20, vidaActualAncho, barraAlto, 0, Color.GREEN);

    // Texto
    e.escribirTexto("Vida: " + this.vida, 25, 45);
}

public void dibujarMana(Entorno e) {
	int barraAncho=60;
	int barraAlto=10;
	int manaMax=100;
	//Indicador de energia
	int manaActualAncho=(int)((mana/(double)manaMax) * barraAncho);
	e.dibujarRectangulo(50-(barraAncho - manaActualAncho)/2.0,20 , manaActualAncho, barraAlto, 0, Color.BLUE);
	e.escribirTexto("Mana: "+ this.mana,25, 45);
}



public void moverIzquierda(Entorno e) {
    if (x - ancho / 2 > 0) { // Verifica que no pase el borde izquierdo
        x -= 4;
        direccion = -1;
    }
}

public void moverDerecha(Entorno e) {
    if (x + ancho / 2 < e.ancho() * 0.8) { // Verifica que no pase el borde derecho limite en 80%
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

public void reducirVida() {
    if (vida > 0) {
        vida--;
        System.out.println("Vida reducida. Vida actual: " + this.vida);
    }
}

public void gastarMana(int cantidad) {
    if (mana >= cantidad) {
        mana -= cantidad;
    }
}

public boolean tieneMana(int cantidad) {
    return mana >= cantidad;
}

public boolean colisionaCon(Roca roca) {
    return (
        this.x - this.ancho / 2 < roca.getX() + roca.getAncho() / 2 &&
        this.x + this.ancho / 2 > roca.getX() - roca.getAncho() / 2 &&
        this.y - this.alto / 2 < roca.getY() + roca.getAlto() / 2 &&
        this.y + this.alto / 2 > roca.getY() - roca.getAlto() / 2
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

    public int getVida() {
        return vida;
    }

    public int getMana(){
        return mana;
    }
}
