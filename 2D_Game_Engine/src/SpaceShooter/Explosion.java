package SpaceShooter;

import Core.GameObject;
import Core.ObjectHandler;
import GameGraphics.Animation;
import GameGraphics.Structure;
import GameGraphics.Vector;

public class Explosion extends GameObject {

	public Explosion(String id, Structure structure, Vector r, ObjectHandler handler) {
		super(id, structure, r, handler);
		Structure [] frames = new Structure[3];
		frames[0] = new Structure("./2D_Game_Engine/src/SpaceShooter/explosion1.dat", r.x, r.y);
		frames[1] = new Structure("./2D_Game_Engine/src/SpaceShooter/explosion2.dat", r.x, r.y);
		frames[2] = new Structure("./2D_Game_Engine/src/SpaceShooter/explosion3.dat", r.x, r.y);
		this.animation = new Animation(frames, 5, false);
	}

	@Override
	public void update() {
		this.animation.update();
		if(!this.animation.isRunning()) {
			handler.removeObject(this);
		}
	}

}
