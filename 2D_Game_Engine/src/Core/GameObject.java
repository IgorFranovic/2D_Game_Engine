package Core;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import GameGraphics.Piece;
import GameGraphics.Structure;

public abstract class GameObject {
	
	protected String id; // a way to identify an object
	protected Structure structure; // geometric pieces of an object: rectangles, circles etc.
	protected float x, y; // (x,y) - current center of mass, structure elements are positioned relative to this point
	protected float vx, vy; // (vx,vy) - current velocity vector
	protected float ax, ay; // (ax,ay) - current acceleration vector
	protected float omega; // current angular velocity
	protected float m; // mass
	protected boolean fixedT; // fixed translational movement (walls, moving walls, bouncers etc)
	protected boolean fixedR; // fixed rotational movement (fans, windmills etc)
	
	// static objects (buildings, walls, portals etc)
	public GameObject(String id, Structure structure, float x, float y) {
		this.id = id;
		this.structure = structure;
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
		this.ax = 0;
		this.ay = 0;
		this.omega = 0;
		this.m = 0;
		this.fixedT = true;
		this.fixedR = true;
	}
	
	// moving walls etc
	public GameObject(String id, Structure structure, float x, float y, float vx, float vy) {
		this.id = id;
		this.structure = structure;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = 0;
		this.ay = 0;
		this.omega = 0;
		this.m = 0;
		this.fixedT = true;
		this.fixedR = true;
	}
	
	// windmills etc
	public GameObject(String id, Structure structure, float x, float y, float omega) {
		this.id = id;
		this.structure = structure;
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
		this.ax = 0;
		this.ay = 0;
		this.omega = omega;
		this.m = 0;
		this.fixedT = true;
		this.fixedR = true;
	}
	
	// an object that moves in the xy-plane but does not rotate around its center of mass
	public GameObject(String id, Structure structure, float x, float y, float vx, float vy, float ax, float ay, float m) {
		this.id = id;
		this.structure = structure;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
		this.omega = 0;
		this.m = m;
		this.fixedT = false;
		this.fixedR = true;
	}
	
	// all kinds of objects
	public GameObject(String id, Structure structure, float x, float y, float vx, float vy, float ax, float ay, float omega, float m) {
		this.id = id;
		this.structure = structure;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
		this.omega = omega;
		this.m = m;
		this.fixedT = false;
		this.fixedR = false;
	}
	
	public abstract void update(); // defines the changes to the object during time (x += vx; y += vy; vx += ax; vy += ay; etc)
	
	public void render(Graphics g) {
		this.structure.render(g);
	}
	
	// current version of collision detection system uses the simplest form of AABB algorithm
	// structure of each object consists of several simple geometric shapes
	// we check for intersections between bounding rectangles of those shapes
	// next candidate for collision detection is ray casting algorithm
	public boolean intersects(GameObject object) {
		if(this.structure.intersects(object.structure)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void transform(AffineTransform at) {
		this.structure.transform(at);
	}
	
	public float getDistance(float x, float y) {
		return (float)Math.sqrt(Math.pow(this.x-x, 2) + Math.pow(this.y-y, 2));
	}
	
	public float getVelocity() {
		return (float)Math.sqrt(vx*vx + vy*vy);
	}
	
	public float getSquareVelocity() {
		return vx*vx + vy*vy;
	}
	
	public float getMomentumX() {
		return m * vx;
	}
	
	public float getMomentumY() {
		return m * vy;
	}
	
	public float getMomentOfInertia() {
		Rectangle2D totalBounds = this.structure.getTotalBounds();
		float x1 = (float)totalBounds.getX();
		float y1 = (float)totalBounds.getY();
		float x2 = x1 + (float)totalBounds.getWidth();
		float y2 = y1 + (float)totalBounds.getHeight();
		float maxDistance = Math.max(Math.max(this.getDistance(x1, y1), this.getDistance(x1, y2)), 
									Math.max(this.getDistance(x2, y1), this.getDistance(x2, y2)));
		return m * maxDistance * maxDistance / 2;
	}
	
	public float getAngularMomentum() {
		return this.getMomentOfInertia() * omega;
	}
	
	public float getKineticEnergy() {
		return m*this.getSquareVelocity()/2 + this.getMomentOfInertia()*omega*omega/2;
	}
	
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
		return vx;
	}

	public float getDy() {
		return vy;
	}
	
	public float getOmega() {
		return omega;
	}
	
	public float getM() {
		return m;
	}
	
	public float getAx() {
		return ax;
	}
	
	public float getAy() {
		return ay;
	}

	public Structure getStructure() {
		return structure;
	}

	public boolean isFixedT() {
		return fixedT;
	}

	public boolean isFixedR() {
		return fixedR;
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
		this.vx = dx;
	}

	public void setDy(float dy) {
		this.vy = dy;
	}
	
	public void setOmega(float omega) {
		this.omega = omega;
	}
	
	public void setM(float m) {
		this.m = m;
	}
	
	public void setAx(float ax) {
		this.ax = ax;
	}
	
	public void setAy(float ay) {
		this.ay = ay;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}
	
	public void setFixedT(boolean fixedT) {
		this.fixedT = fixedT;
	}

	public void setFixedR(boolean fixedR) {
		this.fixedR = fixedR;
	}

	public void add(Piece piece) {
		this.structure.add(piece);
	}
	
	public void remove(int i) {
		this.structure.remove(i);
	}
	
}