package MapBuilder;

import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	
	

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3862371684457001135L;

	@Override
	protected void paintComponent(Graphics g) {
		
	//	Graphics2D G = (Graphics2D)g;
		
	//	G.clearRect(0, 0, this.getWidth(), this.getHeight());
	//	G.clearRect(0, 0, this.getWidth(), 50);
	//	for(int i = 0; i < this.shapes.size(); i++) {
	//		G.setColor(this.colors.get(i));
	//		G.fill(this.shapes.get(i));
			
//		}
		g.drawImage(TilePanel.tiles.get(0), BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, 40, 40, this);
	}
	
}
