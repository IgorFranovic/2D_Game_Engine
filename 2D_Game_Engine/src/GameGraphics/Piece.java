package GameGraphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import MapBuilder.BuilderWindow;
import MapBuilder.TilePanel;

public class Piece {
	
	public Shape shape; // broken encapsulation
	public Color color;
	public String imgPath = "";
	private int x, y; // no getters/setters
	
	public Piece(Shape shape, Color color) {
		this.shape = shape;
		this.color = color;
		this.imgPath = "";
	}
	public Piece(Shape shape, String imgPath, int x, int y) {
		this.shape = shape;
		this.imgPath = imgPath;
		this.x = x; this.y = y;
		
	}
	
	public void render(Graphics2D G) {
		G.setColor(this.color);
		if(this.imgPath == "") {
			G.fill(shape);
		}
		else {
			BufferedImage newImage;
			newImage = getImage();
			
			if(newImage != null)
				G.drawImage(newImage, this.x, this.y, (int)this.shape.getBounds().getWidth(),(int) this.shape.getBounds().getWidth(),  null);
		}
	}
	public BufferedImage getImage() {
		BufferedImage newImage = null;
		File folder = new File("Assets/LandTiles");
		String[] assets = folder.list();
		
		for (int i = 0; i < assets.length; i ++) {
            if(assets[i].equals(this.imgPath)) {
            	try {
					newImage = ImageIO.read(new File("Assets/LandTiles/" + assets[i]));					
				} catch (IOException e) {					
					e.printStackTrace();
				}
            }
        }

		return newImage;
	}
	/*
	public void render(Graphics2D G) {
		G.setColor(this.color);
		G.fill(shape);
	}
	*/
	public void transform(AffineTransform at) {
		this.shape = at.createTransformedShape(this.shape);
	}
	
	public Shape getShape() {
		return shape;
	}

	public Color getColor() {
		return this.color;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgPath() {
		return this.imgPath;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return this.x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return this.y;
	}
	
}