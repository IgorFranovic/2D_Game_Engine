package SpaceShooter;

import java.awt.Color;
import java.awt.Graphics;

import Core.GameObject;
import Core.ObjectHandler;
import GameGraphics.Structure;
import GameGraphics.Vector;

public class Spacecraft extends GameObject {

	public Vector aim; // a unit vector pointing in the direction of a missile
	public Vector leftLauncher, rightLauncher; // positions of launchers
	public float health;
	
	public Spacecraft(String id, Structure structure, Vector r, ObjectHandler handler) {
		super(id, structure, r, handler);
		this.aim = new Vector(0, -1);
		this.leftLauncher = r.add(new Vector(-20, -20));
		this.rightLauncher = r.add(new Vector(20, -20));
		this.health = 100;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.CYAN);
		g.drawRect((int)(this.r.x-51), (int)(this.r.y+74), 102, 10);
		if(this.health > 75) {
			g.setColor(Color.GREEN);
		}
		else if(this.health > 50) {
			g.setColor(Color.YELLOW);
		}
		else if(this.health > 25) {
			g.setColor(Color.ORANGE);
		}
		else {
			g.setColor(Color.RED);
		}
		g.fillRect((int)(this.r.x-50), (int)(this.r.y+75), (int)this.health, 8);
	}

}
