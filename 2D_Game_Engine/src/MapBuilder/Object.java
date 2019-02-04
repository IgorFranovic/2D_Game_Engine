package MapBuilder;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Object extends Rectangle {
	int x, y, width, height;
	BufferedImage image;
	boolean interactable;
	
	public Object(int x, int y, int width, int height, BufferedImage image, boolean interactable) {
		this.x = x; this.y = y; this.width = width; this.height = height;
		this.image = image;
		this.interactable = interactable;
	}

	

	public BufferedImage getImage() {
		return image;
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
