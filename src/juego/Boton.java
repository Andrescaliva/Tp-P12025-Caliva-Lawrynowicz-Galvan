package juego;

import java.awt.Color;
import entorno.Entorno;

public class Boton {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Color color;
	private String texto;
	
	public Boton(int x, int y, int ancho, int alto, Color color, String texto) {
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.color=color;
		this.texto=texto;
	}
	
	public void Dibujar(Entorno e) {
		e.dibujarRectangulo(x + ancho / 2, y + alto / 2, ancho, alto, 0, color);
		e.escribirTexto(texto, x + 10, y + alto / 2 + 5);
	}
	
	public boolean estaPresionado(int mouseX, int mouseY, boolean mousePresionado) {
		return mousePresionado && mouseX >= x && mouseX <= x + ancho && mouseY >= y && mouseY <= y + alto;
	}

	public int getX() {
		return x;
	}

	

	public int getY() {
		return y;
	}

	
	public int getAncho() {
		return ancho;
	}

	

	public int getAlto() {
		return alto;
	}


	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getTexto() {
		return texto;
	}

	

}
