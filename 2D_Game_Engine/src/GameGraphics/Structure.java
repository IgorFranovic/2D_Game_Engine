package GameGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Structure {
	
	private LinkedList<Piece> struct;
	
	public Structure() {
		this.struct = new LinkedList<Piece>();
		//...
	}
	
	// impractical
	public Structure(LinkedList<Piece> struct) {
		this.struct = struct;
	}
	// creating bounding walls easily
	public Structure(Shape shape, Color color) {
		this.struct = new LinkedList<Piece>();
		this.struct.add(new Piece(shape, color));
	}
	//NEW
	public Structure(Rectangle rectangle, String imgPath) {
		this.struct = new LinkedList<Piece>();

		Piece p = new Piece(rectangle, imgPath, (int)rectangle.getX(), (int)rectangle.getY());
		this.struct.add(p);
	}
	//NEW (not being used)
	public Structure(Rectangle rectangle) {
		this.struct = new LinkedList<Piece>();
		Piece p = new Piece(rectangle, Color.black);
		this.struct.add(p);
	}
	
	// generate a structure from a file created using ObjectBuilder
	public Structure(String file, float x0, float y0) {
		this.struct = new LinkedList<Piece>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(file)));
			int cnt = Integer.parseInt(br.readLine());
			for(int i = 0; i < cnt; i++) {
				String line = br.readLine();
				String [] temp = line.split(" ");
				if(temp[0].equals("Polygon")) {
					String [] coords = temp[1].split(";");
					int [] xp = new int[coords.length];
					int [] yp = new int[coords.length];
					for(int j = 0; j < coords.length; j++) {
						String [] xy = coords[j].split(",");
						xp[j] = Integer.parseInt(xy[0]) + (int)x0;
						yp[j] = Integer.parseInt(xy[1]) + (int)y0;
					}
					Shape shape = new Polygon(xp, yp, coords.length);
					String [] rgb = temp[2].split(",");
					int r = Integer.parseInt(rgb[0]);
					int g = Integer.parseInt(rgb[1]);
					int b = Integer.parseInt(rgb[2]);
					Color color = new Color(r, g, b);
					this.struct.add(new Piece(shape, color));
				}
				else {
					String [] coords = temp[1].split(",");
					float x = Integer.parseInt(coords[0]) + x0;
					float y = Integer.parseInt(coords[1]) + y0;
					float w = Integer.parseInt(coords[2]);
					float h = Integer.parseInt(coords[3]);
					Shape shape;
					if(temp[0].equals("Circle") || temp[0].equals("Ellipse")) {
						shape = new Ellipse2D.Float(x, y, w, h);
					}
					else {
						shape = new Rectangle2D.Float(x, y, w, h);
					}
					String [] rgb = temp[2].split(",");
					int r = Integer.parseInt(rgb[0]);
					int g = Integer.parseInt(rgb[1]);
					int b = Integer.parseInt(rgb[2]);
					Color color = new Color(r, g, b);
					this.struct.add(new Piece(shape, color));
				}
			}
			br.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		Graphics2D G = (Graphics2D)g;	
		
		for(Piece piece : struct) {
			//G.setColor(new Color(100, 100, 100, 100));
			//G.fill(piece.getShape().getBounds2D());
			// just to demonstrate that the use of AABB algorithm is justified for objects that have an optimal structure
			// it should work very well for avatar.dat structure 
			// but the performance will probably be poor for object.dat structure (rotate the plane for pi/4 radians to see the issue)
			
			piece.render(G);
		}
	}
	
	public PiecePair getCollidingPieces(Structure s) {
		for(Piece piece1 : this.struct) {
			for(Piece piece2 : s.struct) {
				if(piece1.shape.intersects(piece2.shape.getBounds2D())) {
					return new PiecePair(piece1, piece2);
				}
			}
		}
		return null;
	}
	
	public void transform(AffineTransform at) {
		for(Piece piece : this.struct) {
			piece.transform(at);
		}
	}
	
	public Rectangle2D getTotalBounds() {
		Rectangle2D union = this.struct.get(0).shape.getBounds2D();
		for(int i = 1; i < this.struct.size(); i++) {
			union = union.createUnion(this.struct.get(i).shape.getBounds2D());
		}
		return union;
	}
	/*
	public LinkedList<Piece> getStruct() {
		return struct;
	}

	public void setStruct(LinkedList<Piece> struct) {
		this.struct = struct;
	}
	*/
	public void add(Piece piece) {
		this.struct.add(piece);
	}
	
	public void remove(int i) {
		this.struct.remove(i);
	}
	
}