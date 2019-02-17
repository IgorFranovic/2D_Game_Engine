package SpaceShooter;

import Core.GameObject;
import Core.ObjectHandler;
import GameGraphics.Structure;
import GameGraphics.Vector;

public class Spacecraft extends GameObject {

	public Vector aim; // a unit vector pointing in the direction of a missile
	public Vector leftLauncher, rightLauncher; // positions of launchers
	public PowerUp powerUp;
	
	public Spacecraft(String id, Structure structure, Vector r, ObjectHandler handler) {
		super(id, structure, r, handler);
		this.aim = new Vector(0, -1);
		this.leftLauncher = r.add(new Vector(-20, -20));
		this.rightLauncher = r.add(new Vector(20, -20));
		this.powerUp = null;
	}

}
