package GameGraphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class Structure {
	
	private LinkedList<Piece> struct;

	public Structure() {
		//...
	}
	
	public Structure(LinkedList<Piece> struct) {
		this.struct = struct;
	}
	
	public void render(Graphics g) {
		Graphics2D G = (Graphics2D)g;
		for(Piece piece : struct) {
			piece.render(G);
		}
	}

	public LinkedList<Piece> getStruct() {
		return struct;
	}

	public void setStruct(LinkedList<Piece> struct) {
		this.struct = struct;
	}

	public void add(Piece piece) {
		this.struct.add(piece);
	}
	
	public void remove(int i) {
		this.struct.remove(i);
	}
	
}
