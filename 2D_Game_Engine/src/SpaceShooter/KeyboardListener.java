package SpaceShooter;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import Core.ObjectHandler;
import GameGraphics.Structure;
import GameGraphics.Vector;

public class KeyboardListener extends KeyAdapter {
	
	private Spacecraft spacecraft;
	private ObjectHandler handler;
	private final float step = 5.0f, deg = 0.1f;
	
	public KeyboardListener(Spacecraft spacecraft, ObjectHandler handler) {
		this.spacecraft = spacecraft;
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		AffineTransform at = new AffineTransform();
		if(key == KeyEvent.VK_UP) {
			Vector dr = spacecraft.aim.mul(step);
			spacecraft.r = spacecraft.r.add(dr);
			spacecraft.leftLauncher = spacecraft.leftLauncher.add(dr);
			spacecraft.rightLauncher = spacecraft.rightLauncher.add(dr);
			at.setToTranslation(dr.x, dr.y);
			spacecraft.transform(at);
		}
		else if(key == KeyEvent.VK_LEFT) {
			Vector r = spacecraft.r;
			spacecraft.aim = spacecraft.aim.R(-deg);
			spacecraft.leftLauncher = spacecraft.leftLauncher.sub(r).R(-deg).add(r);
			spacecraft.rightLauncher = spacecraft.rightLauncher.sub(r).R(-deg).add(r);
			at.setToRotation(-deg, r.x, r.y);
			spacecraft.transform(at);
		} 
		else if(key == KeyEvent.VK_RIGHT) {
			Vector r = spacecraft.r;
			spacecraft.aim = spacecraft.aim.R(deg);
			spacecraft.leftLauncher = spacecraft.leftLauncher.sub(r).R(deg).add(r);
			spacecraft.rightLauncher = spacecraft.rightLauncher.sub(r).R(deg).add(r);
			at.setToRotation(deg, r.x, r.y);
			spacecraft.transform(at);
		}
		else if(key == KeyEvent.VK_SPACE) {
			Vector offset = spacecraft.aim.mul(10);
			float xl = spacecraft.leftLauncher.add(offset).x;
			float yl = spacecraft.leftLauncher.add(offset).y;
			float xr = spacecraft.rightLauncher.add(offset).x;
			float yr = spacecraft.rightLauncher.add(offset).y;
			handler.addObject(new Missile("missile", new Structure(new Ellipse2D.Float(xl-3, yl-3, 7, 7), Color.GREEN),
					new Vector(xl,yl), spacecraft.aim.mul(12*step), new Vector(0,0), 5, handler));
			handler.addObject(new Missile("missile", new Structure(new Ellipse2D.Float(xr-3, yr-3, 7, 7), Color.GREEN),
					new Vector(xr,yr), spacecraft.aim.mul(12*step), new Vector(0,0), 5, handler));
		}
	}
}
