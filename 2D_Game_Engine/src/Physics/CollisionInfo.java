package Physics;

import GameGraphics.Vector;

public class CollisionInfo {

	public float e; // elasticity coefficient, 0 <= e <= 1
	// e = 0 - totally non-elastic collision
	// e = 1 - totally elastic collision
	public Vector P; // radius vector of a point at which the objects collide
	public Vector n; // a unit vector perpendicular to the collision plane
	public Vector vAB; // relative velocity of colliding objects A and B
	
	public CollisionInfo(float e, Vector P, Vector n, Vector vAB) {
		this.e = e;
		this.P = P;
		this.n = n;
		this.vAB = vAB;
	}
	
	public CollisionInfo invert() {
		return new CollisionInfo(e, P, n.mul(-1), vAB.mul(-1));
	}
	/*
	public float getE() {
		return e;
	}

	public Vector getP() {
		return P;
	}

	public Vector getN() {
		return n;
	}

	public Vector getvAB() {
		return vAB;
	}
	
	public void setE(float e) {
		this.e = e;
	}

	public void setP(Vector p) {
		P = p;
	}

	public void setN(Vector n) {
		this.n = n;
	}

	public void setvAB(Vector vAB) {
		this.vAB = vAB;
	}
	*/
}