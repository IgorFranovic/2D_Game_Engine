package ObjectBuilder;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColorTest extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1663947954931760886L;
	
	private Color color;
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public ColorTest(Color color) {
		this.color = color;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(this.color);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
}
