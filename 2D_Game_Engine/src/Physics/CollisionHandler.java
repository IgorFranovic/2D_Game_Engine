package Physics;

import Core.GameObject;
import GameGraphics.Vector;

public class CollisionHandler {

	// there are 16 cases in total, some of which should never happen
	// we will label them using binary strings of length 4: [t1,r1,t2,r2]
	// ti = oi.isFixedT(), ri = oi.isFixedR(), i = 1,2
	// cases where both colliding objects have some kind of a fixed motion should not happen
	// these are: 1111, 1110, 1101, 1011, 1010, 1001, 0111, 0110, 0101
	// we are left with 7 cases: 0000, 0001, 0010, 0011, 0100, 1000, 1100
	// 0001 is the same as 0100, just with switched objects
	// similarly, 0010 is the same as 1000, and 0011 is the same as 1100
	// so we will need 4 methods in total, which we will call from one central method
	
	private static void collision0000(GameObject o1, GameObject o2, CollisionInfo c12) {
		Vector r1cap = c12.P.sub(o1.r).cap();
		Vector r2cap = c12.P.sub(o2.r).cap();
		Vector n = c12.n;
		// compute the impact
		float i00 = -(1 + c12.e)*c12.vAB.dot(n)/(n.normSquared()*(1/o1.m + 1/o2.m) +
															(float)Math.pow(r1cap.dot(n), 2)/o1.I +
															(float)Math.pow(r2cap.dot(n), 2)/o2.I);
		o1.v = o1.v.add(n.mul(i00 / o1.m));
		o1.omega = o1.omega + (i00 / o1.I)*(r1cap.dot(n));
		o2.v = o2.v.sub(n.mul(i00 / o2.m));
		o2.omega = o2.omega - (i00 / o2.I)*(r2cap.dot(n));
	}
	
	private static void collision0001(GameObject o1, GameObject o2, CollisionInfo c12) {
		Vector r1cap = c12.P.sub(o1.r).cap();
		Vector n = c12.n;
		// i01 = limit of i00 as I2 -> inf
		float i01 = -(1 + c12.e)*c12.vAB.dot(n)/(n.normSquared()*(1/o1.m + 1/o2.m) +
															(float)Math.pow(r1cap.dot(n), 2)/o1.I);
		o1.v = o1.v.add(n.mul(i01 / o1.m));
		o1.omega = o1.omega + (i01 / o1.I)*(r1cap.dot(n));
		o2.v = o2.v.sub(n.mul(i01 / o2.m));
	}
	
	private static void collision0010(GameObject o1, GameObject o2, CollisionInfo c12) {
		Vector r1cap = c12.P.sub(o1.r).cap();
		Vector r2cap = c12.P.sub(o2.r).cap();
		Vector n = c12.n;
		// i10 = limit of i00 as m2 -> inf
		float i10 = -(1 + c12.e)*c12.vAB.dot(n)/(n.normSquared()/o1.m +
															(float)Math.pow(r1cap.dot(n), 2)/o1.I +
															(float)Math.pow(r2cap.dot(n), 2)/o2.I);
		o1.v = o1.v.add(n.mul(i10 / o1.m));
		o1.omega = o1.omega + (i10 / o1.I)*(r1cap.dot(n));
		o2.omega = o2.omega - (i10 / o2.I)*(r2cap.dot(n));
	}
	
	private static void collision0011(GameObject o1, GameObject o2, CollisionInfo c12) {
		Vector r1cap = c12.P.sub(o1.r).cap();
		Vector n = c12.n;
		// i11 = limit of i00 as m2 -> inf, I2 -> inf
		float i11 = -(1 + c12.e)*c12.vAB.dot(n)/(n.normSquared()/o1.m +
															(float)Math.pow(r1cap.dot(n), 2)/o1.I);
		o1.v = o1.v.add(n.mul(i11 / o1.m));
		o1.omega = o1.omega + (i11 / o1.I)*(r1cap.dot(n));
	}
	
	public static void handleCollision(GameObject o1, GameObject o2, CollisionInfo c12) {
		// case 0000
		if(!o1.fixedT && !o1.fixedR && !o2.fixedT && !o2.fixedR) {
			collision0000(o1, o2, c12);
		}
		// case 0001
		else if(!o1.fixedT && !o1.fixedR && !o2.fixedT && o2.fixedR) {
			collision0001(o1, o2, c12);
		}
		// case 0010
		else if(!o1.fixedT && !o1.fixedR && o2.fixedT && !o2.fixedR) {
			collision0010(o1, o2, c12);
		}
		// case 0011
		else if(!o1.fixedT && !o1.fixedR && o2.fixedT && o2.fixedR) {
			collision0011(o1, o2, c12);
		}
		// case 0100
		else if(!o1.fixedT && o1.fixedR && !o2.fixedT && !o2.fixedR) {
			collision0001(o2, o1, c12.invert()); // c21
		}
		// case 1000
		else if(o1.fixedT && !o1.fixedR && !o2.fixedT && !o2.fixedR) {
			collision0010(o2, o1, c12.invert()); // c21
		}
		// case 1100
		else if(o1.fixedT && o1.fixedR && !o2.fixedT && !o2.fixedR) {
			collision0011(o2, o1, c12.invert()); // c21
		}
		else {
			// do nothing (undefined behavior)
		}
	}
	
}