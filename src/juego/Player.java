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
        this.ancho = 17;
        this.alto = 30;
        this.direccion = 1; // Por defecto hacia la derecha
        this.vida = 3; // Vida inicial
        this.mana = 100;
        this.imagenDerecha = Herramientas.cargarImagen("imagenes/player-derecha.png");
        this.imagenIzquierda = Herramientas.cargarImagen("imagenes/player-izquierda.png");
    }

    // Metodos
public void dibujar(Entorno e) {
    Image imagenActual = (direccion == 1) ? imagenDerecha : imagenIzquierda;
    e.dibujarImagen(imagenActual, x, y, 0, 0.08); // 0.5 es escala, podés ajustarlo
}

public void dibujarVida(Entorno e, int posX, int posY) {
    int barraAncho = 100;
    int barraAlto = 15;
    int vidaMax = 3;

    // Fondo de barra (rojo)
    e.dibujarRectangulo(posX + barraAncho / 2, posY + barraAlto / 2, barraAncho, barraAlto, 0, Color.RED);

    // Vida actual (verde)
    int vidaActualAncho = (int)((vida / (double)vidaMax) * barraAncho);
    e.dibujarRectangulo(posX + vidaActualAncho / 2, posY + barraAlto / 2, vidaActualAncho, barraAlto, 0, Color.GREEN);

    // Texto
    e.escribirTexto("Vida: " + this.vida, posX, posY - 5);
}

public void dibujarMana(Entorno e, int posX, int posY) {
    int barraAncho = 100;
    int barraAlto = 15;
    int manaMax = 100;

    // Fondo de barra (gris oscuro)
    e.dibujarRectangulo(posX + barraAncho / 2, posY + barraAlto / 2, barraAncho, barraAlto, 0, Color.DARK_GRAY);

    // Mana actual (azul)
    int manaActualAncho = (int)((mana / (double)manaMax) * barraAncho);
    e.dibujarRectangulo(posX + manaActualAncho / 2, posY + barraAlto / 2, manaActualAncho, barraAlto, 0, Color.BLUE);

    // Texto
    e.escribirTexto("Mana: " + this.mana, posX, posY - 5);
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

public void gastarMana(int cantidad) { //Reduce el mana de jugador
    if (mana >= cantidad) {
        mana -= cantidad;
    }
}

public boolean tieneMana(int cantidad) { //Indica si tiene mana o no
    return mana >= cantidad;
}

public void recuperarMana(int cantidad) {//	Regenera el mana del jugador
    if (mana < 100) {
        mana += cantidad;
        if (mana > 100) {
            mana = 100;
        }
    }
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
