package MapBuilder;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapObject extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6062519785537850338L;
	int x, y, width, height;
	String imagePath;
	boolean interactable;
	
	public MapObject(int x, int y, int width, int height, String imagePath, boolean interactable) {
		this.x = x; this.y = y; this.width = width; this.height = height;
		this.imagePath = imagePath;
		this.interactable = interactable;
	}

	

	public BufferedImage getImage() {
		BufferedImage newImage;
		for(int i = 0; i < TilePanel.assets.length; i++) {
			if(this.imagePath.equals(TilePanel.assets[i])) {
				try {
					newImage = ImageIO.read(new File(BuilderWindow.assetsPath + "LandTiles/" + TilePanel.assets[i]));
					return newImage;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	public String getImagePath() {
		return this.imagePath;
	}

	public boolean getInteractable() {
		return this.interactable;
	}
	public void setInteractable(boolean interactable) {
		this.interactable = interactable;
	}
	
	public int getXCoord() {
		return this.x;
	}
	public int getYCoord() {
		return this.y;
	}
	public int getWidthDim() {
		return this.width;
	}
	public int getHeightDim() {
		return this.height;
	}
	
	
	
}
