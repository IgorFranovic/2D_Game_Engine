package SpaceShooter;

import Core.GameObject;
import Core.ObjectHandler;
import GameGraphics.Structure;
import GameGraphics.Vector;

public class Missile extends GameObject {

	public Missile(String id, Structure structure, Vector r, Vector v, Vector a, float m, ObjectHandler handler) {
		super(id, structure, r, v, a, 0, 0, m, handler);
	}

}
