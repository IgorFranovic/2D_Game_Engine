package ObjectBuilder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.LinkedList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1364361661607878126L;
	
	private LinkedList<Shape> shapes;
	private LinkedList<Color> colors;
	private Shape currentShape;
	
	public DrawPanel() {
		this.shapes = new LinkedList<Shape>();
		this.colors = new LinkedList<Color>();
		this.currentShape = null;
	}
	
	public void add(Shape shape, Color color) {
		this.shapes.addLast(shape);
		this.colors.addLast(color);
	}
	
	public void remove() {
		this.shapes.removeLast();
		this.colors.removeLast();
	}
	
	public Shape getCurrentShape() {
		return this.currentShape;
	}
	
	public void setCurrentShape(Shape shape) {
		this.currentShape = shape;
	}
	
	public void clear() {
		this.shapes.clear();
		this.colors.clear();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D G = (Graphics2D)g;
		G.clearRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < this.shapes.size(); i++) {
			G.setColor(this.colors.get(i));
			G.fill(this.shapes.get(i));
		}
		if(this.currentShape != null) {
			G.setColor(new Color(100, 100, 100, 100));
			G.fill(this.currentShape);
		}
		G.setColor(Color.BLACK);
		G.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		G.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
		G.setColor(Color.LIGHT_GRAY);
		for(int i = 1; i <= this.getHeight()/100; i++) {
			G.drawLine(0, this.getHeight()/2-i*50, this.getWidth(), this.getHeight()/2-i*50);
			G.drawLine(0, this.getHeight()/2+i*50, this.getWidth(), this.getHeight()/2+i*50);
		}
		for(int j = 1; j <= this.getWidth()/100; j++) {
			G.drawLine(this.getWidth()/2-50*j, 0, this.getWidth()/2-50*j, this.getHeight());
			G.drawLine(this.getWidth()/2+50*j, 0, this.getWidth()/2+50*j, this.getHeight());
		}
	}
	
}
