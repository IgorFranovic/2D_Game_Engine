package SpaceShooter;

import Core.GameObject;
import Core.ObjectHandler;
import GameGraphics.Structure;
import GameGraphics.Vector;

public class PowerUp extends GameObject {

	public PowerUp(String id, Structure structure, Vector r, Vector v, Vector a, float omega, float alpha, float m, ObjectHandler handler) {
		super(id, structure, r, v, a, omega, alpha, m, handler);
	}
	
}
