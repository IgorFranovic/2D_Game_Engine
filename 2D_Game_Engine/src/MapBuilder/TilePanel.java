package MapBuilder;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TilePanel extends JPanel {
	
	private LinkedList<BufferedImage> tiles;
	private int firstTileNumber = 0;
	public TilePanel() {
		File folder = new File("Assets");
		String[] assets = folder.list();
		
		for (String asset : assets) {
            System.out.println(asset);
        }
		
		
		tiles = new LinkedList<BufferedImage>();
		try {
			for(int i = 0; i < assets.length; i++) {
				BufferedImage newImage = ImageIO.read(new File("Assets/" + assets[i]));	
				tiles.add(newImage);
			}
			
		} catch(IOException e) {
			
		}
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("Test " + tiles.size());
		g = (Graphics2D)g;
		g.setColor(Color.black);
		for(int i = firstTileNumber; i < firstTileNumber+10; i++) {
			g.drawLine(i*this.getWidth()/10, 0, i*this.getWidth()/10, this.getHeight());
			g.drawImage(tiles.get(i), i*this.getWidth()/10, 0, this.getWidth()/10, this.getHeight(), this);
		}
		
		
	}

}
