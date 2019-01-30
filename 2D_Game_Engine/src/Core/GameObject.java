package Core;

import java.awt.Graphics;
import GameGraphics.Piece;
import GameGraphics.Structure;

public abstract class GameObject {
	
	protected String id; // a way to identify an object
	protected float x, y; // (x,y) - current position, , structure elements should be positioned relative to this point
	protected float dx, dy; // (dx,dy) - current translation vector
	protected float theta; // current rotational angle
	protected Structure structure; // geometric pieces of an object: rectangles, circles etc.
	
	public GameObject(String id, float x, float y, float dx, float dy, float theta, Structure structure) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.theta = theta;
		this.structure = structure;
	}
	
	public void render(Graphics g) {
		this.structure.render(g);
	}
	
	public abstract void update(); // defines the changes to the object during time

	public String getId() {
		return id;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getDx() {
		return dx;
	}

	public float getDy() {
		return dy;
	}
	
	public float getTheta() {
		return theta;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}
	
	public void setTheta(float theta) {
		this.theta = theta;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public void add(Piece piece) {
		this.structure.add(piece);
	}
	
	public void remove(int i) {
		this.structure.remove(i);
	}
	
}