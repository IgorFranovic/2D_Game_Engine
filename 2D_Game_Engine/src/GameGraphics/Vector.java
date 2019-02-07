package GameGraphics;

public class Vector {

	private final float x, y;
	// to avoid broken encapsulation (like in Piece and Structure)
	// we do this right now because bugs are much more likely to happen because of messing with vector coordinates than with shapes
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector add(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y);
	}
	
	public Vector sub(Vector v) {
		return new Vector(this.x - v.x, this.y - v.y);
	}
	
	public Vector mul(float f) {
		return new Vector(this.x * f, this.y * f);
	}
	
	public float dot(Vector v) {
		return this.x*v.x + this.y*v.y;
	}
	
	public float norm() {
		return (float)Math.sqrt(this.x*this.x + this.y*this.y);
	}
	
	public float normSquared() {
		return this.x*this.x + this.y*this.y;
	}
	
	public float tanArg() {
		if(this.x != 0) {
			return this.y / this.x;
		}
		else {
			return Float.MAX_VALUE;
		}
	}
	
	// rotation by pi/2 radians
	// we do not need a rotation by an arbitrary angle at the moment
	public Vector cap() {
		return new Vector(-y, x);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	/*
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	*/
	
}