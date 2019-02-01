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
	
	public DrawPanel() {
		this.shapes = new LinkedList<Shape>();
		this.colors = new LinkedList<Color>();
	}
	
	public void add(Shape shape, Color color) {
		this.shapes.addLast(shape);
		this.colors.addLast(color);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D G = (Graphics2D)g;
		G.clearRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < this.shapes.size(); i++) {
			G.setColor(this.colors.get(i));
			G.fill(this.shapes.get(i));
		}
	}
	
}
