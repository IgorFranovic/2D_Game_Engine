package GameGraphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Piece {
	
	private Shape shape; // broken encapsulation
	private Color color;
	
	public Piece(Shape shape, Color color) {
		this.shape = shape;
		this.color = color;
	}
	
	public void render(Graphics2D G) {
		G.setColor(this.color);
		G.fill(shape);
	}
	
	public void transform(AffineTransform at) {
		this.shape = at.createTransformedShape(this.shape);
	}
	
	public Shape getShape() {
		return shape;
	}

	public Color getColor() {
		return this.color;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}