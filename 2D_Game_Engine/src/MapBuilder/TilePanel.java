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
	
	public static LinkedList<BufferedImage> tiles;
	public static int firstTileNumber = 0;
	public static int listSize = 0;
	public static int numberOfShownTiles = 8;
	public TilePanel() {
		File folder = new File("Assets/LandTiles");
		String[] assets = folder.list();
		
		for (String asset : assets) {
            System.out.println(asset);
        }
		
		
		tiles = new LinkedList<BufferedImage>();
		try {
			for(int i = 0; i < assets.length; i++) {
				BufferedImage newImage = ImageIO.read(new File("Assets/LandTiles/" + assets[i]));	
				tiles.add(newImage);
				listSize++;
			}
			System.out.println(listSize + " SIZE");
			
		} catch(IOException e) {
			
		}
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
//		System.out.println("Test " + tiles.size());
		g = (Graphics2D)g;
		g.setColor(Color.white);
		System.out.println(this.getHeight());
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		
		
		for(int i = firstTileNumber; i < firstTileNumber+numberOfShownTiles; i++) {
			System.out.println(i);
			g.drawLine(i*this.getWidth()/numberOfShownTiles, 0, i*this.getWidth()/numberOfShownTiles, this.getHeight());
			g.drawImage(tiles.get(i), (i-firstTileNumber)*this.getWidth()/numberOfShownTiles, 0, this.getWidth()/numberOfShownTiles, this.getHeight(), this);
		
		}
		
		System.out.println("Repainted");
	
		
		
	}
	
	

}
