package MapBuilder;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1781340941936539002L;
	
	
	private Color color;
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public ColorPanel() {
		
	}
	
	public ColorPanel(Color color) {
		this.color = color;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(this.color);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
}
