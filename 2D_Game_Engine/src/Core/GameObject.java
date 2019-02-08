package Core;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import GameGraphics.Piece;
import GameGraphics.PiecePair;
import GameGraphics.Structure;
import GameGraphics.Vector;
import Physics.CollisionInfo;

public class GameObject {
	
	protected String id; // a way to identify an object
	protected Structure structure; // geometric pieces of an object: rectangles, circles etc.
	protected Vector r; // r = (x,y) - current center of mass, structure elements are positioned relative to this point
	protected Vector v; // current velocity of the center of mass
	protected Vector a; // current acceleration of the center of mass
	// r' = v, r" = v' = a
	protected float omega; // current angular velocity
	protected float alpha; // current angular acceleration
	// omega' = alpha
	protected float m; // mass
	protected float I; // moment of inertia
	protected boolean fixedT; // fixed translational movement (walls, moving walls, bouncers etc)
	protected boolean fixedR; // fixed rotational movement (fans, windmills etc)
	
	protected ObjectHandler handler;
	
	// static objects (buildings, walls, portals etc)
	public GameObject(String id, Structure structure, Vector r, ObjectHandler handler) {
		this.id = id;
		this.structure = structure;
		this.r = r;
		this.v = new Vector(0,0);
		this.a = new Vector(0,0);
		this.omega = 0;
		this.alpha = 0;
		this.m = -1;
		// for simplicity, we actually consider the mass of objects with some sort of fixed motion (or lack of it) to be infinite
		this.I = -1; // same thing
		this.fixedT = true; // collisions with other objects do not affect the velocity of the center of mass
		this.fixedR = true; // collisions with other objects do not affect the angular velocity of the object
		this.handler = handler;
	}
	
	// moving walls etc
	public GameObject(String id, Structure structure, Vector r, Vector v, Vector a, ObjectHandler handler) {
		this.id = id;
		this.structure = structure;
		this.r = r;
		this.v = v;
		this.a = a;
		this.omega = 0;
		this.alpha = 0;
		this.m = -1;
		this.I = -1;
		this.fixedT = true;
		this.fixedR = true;
		this.handler = handler;
	}
	
	// windmills etc
	public GameObject(String id, Structure structure, Vector r, float omega, float alpha, ObjectHandler handler) {
		this.id = id;
		this.structure = structure;
		this.r = r;
		this.v = new Vector(0,0);
		this.a = new Vector(0,0);
		this.omega = omega;
		this.alpha = alpha;
		this.m = -1;
		this.I = -1;
		this.fixedT = true;
		this.fixedR = true;
		this.handler = handler;
	}
	
	// an object that does not rotate around its center of mass but its center of mass does move in the xy-plane freely
	public GameObject(String id, Structure structure, Vector r, Vector v, Vector a, float m, ObjectHandler handler) {
		this.id = id;
		this.structure = structure;
		this.r = r;
		this.v = v;
		this.a = a;
		this.omega = 0;
		this.alpha = 0;
		this.m = m;
		this.I = -1; // to disable rotation around the center of mass
		this.fixedT = false;
		this.fixedR = true;
		this.handler = handler;
	}
	
	// an object that rotates around its center of mass freely but its center of mass does not move in the xy-plane
	public GameObject(String id, Structure structure, Vector r, float omega, float alpha, float m, ObjectHandler handler) {
		this.id = id;
		this.structure = structure;
		this.r = r;
		this.v = new Vector(0,0);
		this.a = new Vector(0,0);
		this.omega = omega;
		this.alpha = alpha;
		this.m = -1; // to disable movement of the center of mass
		// just for beta testing, we will probably need to approximate moment of inertia better
		// that requires changing the way we build objects a bit
		// for now, we calculate moment of inertia of a thin minimal rectangle that bounds the whole object and has the same mass
		Rectangle2D totalBounds = this.structure.getTotalBounds();
		float w = (float)totalBounds.getWidth();
		float h = (float)totalBounds.getHeight();
		this.I = (m/12)*(w*w + h*h); // we must use the given m
		this.fixedT = true;
		this.fixedR = false;
		this.handler = handler;
	}
	
	// all kinds of objects that can both translate and rotate freely in the xy-plane
	public GameObject(String id, Structure structure, Vector r, Vector v, Vector a, float omega, float alpha, float m, ObjectHandler handler) {
		this.id = id;
		this.structure = structure;
		this.r = r;
		this.v = v;
		this.a = a;
		this.omega = omega;
		this.alpha = alpha;
		this.m = m;
		Rectangle2D totalBounds = this.structure.getTotalBounds();
		float w = (float)totalBounds.getWidth();
		float h = (float)totalBounds.getHeight();
		this.I = (m/12)*(w*w + h*h); // we must use the given m
		this.fixedT = false;
		this.fixedR = false;
		this.handler = handler;
	}
	
	private final float h = 0.1f;
	
	// defines the changes to the object during time (x += vx; y += vy; vx += ax; vy += ay; etc)
	public void update() {
		Vector dr = this.v.mul(h);
		this.r = this.r.add(dr);
		this.v = this.v.add(this.a.mul(h));
		AffineTransform at = new AffineTransform();
		at.setToTranslation(dr.getX(), dr.getY());
		this.transform(at);
		at.setToRotation(this.omega*h, this.r.getX(), this.r.getY());
		this.transform(at);
	}
	
	public void render(Graphics g) {
		this.structure.render(g);
	}
	
	// current version of collision detection system uses the simplest form of AABB algorithm
	// structure of each object consists of several simple geometric shapes
	// we check for intersections between bounding rectangles of those shapes
	// next candidate for collision detection is ray casting algorithm
	public CollisionInfo getCollisionInfo(GameObject object) {
		PiecePair collidingPieces = this.structure.getCollidingPieces(object.structure);
		if(collidingPieces != null) {
			Piece A = collidingPieces.A;
			Piece B = collidingPieces.B;
			Rectangle2D intersection = A.getShape().getBounds2D().createIntersection(B.getShape().getBounds2D());
			float x = (float)intersection.getX();
			float y = (float)intersection.getY();
			float w = (float)intersection.getWidth();
			float h = (float)intersection.getHeight();
			
			Vector P = new Vector(x + w/2, y + h/2); 
			Vector vAB = this.getV().sub(object.getV());
			
			float nx = 0, ny = 0;
			
			if(w < h) {
				if(A.getShape().getBounds2D().getX() < B.getShape().getBounds2D().getX()) {
					nx = -1;
				}
				else {
					nx = 1;
				}
			}
			else {
				if(A.getShape().getBounds2D().getY() < B.getShape().getBounds2D().getY()) {
					ny = -1;
				}
				else {
					ny = 1;
				}
			}
			
			Vector n = new Vector(nx, ny);
			return new CollisionInfo(1, P, n, vAB); // e = 1 just for beta testing
		}
		else {
			return null;
		}
	}
	
	public void transform(AffineTransform at) {
		this.structure.transform(at);
	}
	
	public float getDistance(Vector r) {
		Vector temp = this.r.sub(r);
		return temp.norm();
	}
	
	public float getSquareVelocity() {
		return this.v.normSquared();
	}
	
	public Vector getMomentum() {
		return this.v.mul(this.m);
	}
	
	public float getAngularMomentum() {
		return this.I * omega;
	}
	
	public String getId() {
		return id;
	}
	
	public Structure getStructure() {
		return structure;
	}

	public Vector getR() {
		return r;
	}

	public Vector getV() {
		return v;
	}
	
	public Vector getA() {
		return a;
	}
	
	public float getOmega() {
		return omega;
	}
	
	public float getAlpha() {
		return alpha;
	}
	
	public float getM() {
		return m;
	}
	
	public float getI() {
		return I;
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
	
	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public void setR(Vector r) {
		this.r = r;
	}

	public void setV(Vector v) {
		this.v = v;
	}
	
	public void setA(Vector a) {
		this.a = a;
	}
	
	public void setOmega(float omega) {
		this.omega = omega;
	}
	
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	public void setM(float m) {
		this.m = m;
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