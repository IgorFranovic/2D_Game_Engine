package MapBuilder;


import java.awt.BasicStroke;
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7569698982642644401L;
	
	public static LinkedList<BufferedImage> tiles;
	public static int firstTileNumber = 0;
	public static int listSize = 0;
	public static int numberOfShownTiles = 8;
	
	public TilePanel() {
		File folder = new File(BuilderWindow.assetsPath + "LandTiles");
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

	
		g.setColor(Color.white);
		System.out.println(this.getHeight());
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		
		
		for(int i = firstTileNumber; i < firstTileNumber+numberOfShownTiles; i++) {
			System.out.println(i);
			
			g.drawLine((i-firstTileNumber)*this.getWidth()/numberOfShownTiles, 0, (i-firstTileNumber)*this.getWidth()/numberOfShownTiles, this.getHeight());
			if(i<tiles.size())
			g.drawImage(tiles.get(i), (i-firstTileNumber)*this.getWidth()/numberOfShownTiles, 0, this.getWidth()/numberOfShownTiles, this.getHeight(), this);
			
		}
		
		
		g.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1);
		
		System.out.println("Repainted");
	
		
		
	}
	
	

}
