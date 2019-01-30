package GameGraphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class Piece {
	
	private Shape shape;
	private int r, g, b;
	
	public Piece(Shape shape, int r, int g, int b) {
		this.shape = shape;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void render(Graphics2D G) {
		G.setColor(new Color(r, g, b));
		G.draw(shape);
	}

	public Shape getShape() {
		return shape;
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public void setR(int r) {
		this.r = r;
	}

	public void setG(int g) {
		this.g = g;
	}

	public void setB(int b) {
		this.b = b;
	}
	
}