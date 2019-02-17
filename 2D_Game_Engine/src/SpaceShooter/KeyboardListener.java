package SpaceShooter;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;

import Core.ObjectHandler;
import GameGraphics.Structure;
import GameGraphics.Vector;

public class KeyboardListener extends KeyAdapter {
	
	private Spacecraft spacecraft;
	private ObjectHandler handler;
	private final float step = 5.0f, deg = 0.1f;
	
	private final Set<Character> pressed;
	
	public KeyboardListener(Spacecraft spacecraft, ObjectHandler handler) {
		this.spacecraft = spacecraft;
		this.handler = handler;
		pressed = new HashSet<Character>();
	}
	
	public void keyPressed(KeyEvent e) {
		pressed.add(e.getKeyChar());
		AffineTransform at = new AffineTransform();
		if(pressed.contains('w')) {
			Vector dr = spacecraft.aim.mul(step);
			spacecraft.r = spacecraft.r.add(dr);
			spacecraft.leftLauncher = spacecraft.leftLauncher.add(dr);
			spacecraft.rightLauncher = spacecraft.rightLauncher.add(dr);
			at.setToTranslation(dr.x, dr.y);
			spacecraft.transform(at);
		}
		if(pressed.contains('a')) {
			Vector r = spacecraft.r;
			spacecraft.aim = spacecraft.aim.R(-deg);
			spacecraft.leftLauncher = spacecraft.leftLauncher.sub(r).R(-deg).add(r);
			spacecraft.rightLauncher = spacecraft.rightLauncher.sub(r).R(-deg).add(r);
			at.setToRotation(-deg, r.x, r.y);
			spacecraft.transform(at);
		}
		if(pressed.contains('d')) {
			Vector r = spacecraft.r;
			spacecraft.aim = spacecraft.aim.R(deg);
			spacecraft.leftLauncher = spacecraft.leftLauncher.sub(r).R(deg).add(r);
			spacecraft.rightLauncher = spacecraft.rightLauncher.sub(r).R(deg).add(r);
			at.setToRotation(deg, r.x, r.y);
			spacecraft.transform(at);
		}
		if(pressed.contains('m')) {
			Vector offset = spacecraft.aim.mul(10);
			float xl = spacecraft.leftLauncher.add(offset).x;
			float yl = spacecraft.leftLauncher.add(offset).y;
			float xr = spacecraft.rightLauncher.add(offset).x;
			float yr = spacecraft.rightLauncher.add(offset).y;
			handler.addObject(new Missile("missile", new Structure(new Ellipse2D.Float(xl-3, yl-3, 7, 7), Color.GREEN),
					new Vector(xl,yl), spacecraft.aim.mul(10*step), new Vector(0,0), 5, handler));
			handler.addObject(new Missile("missile", new Structure(new Ellipse2D.Float(xr-3, yr-3, 7, 7), Color.GREEN),
					new Vector(xr,yr), spacecraft.aim.mul(10*step), new Vector(0,0), 5, handler));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressed.remove(e.getKeyChar());
	}
	
}
