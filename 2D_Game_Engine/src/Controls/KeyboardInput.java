package Controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import Core.GameObject;
import Core.ObjectHandler;
import GameGraphics.Vector;

public class KeyboardInput extends KeyAdapter {
	
	///basic player movement WSAD
	private ObjectHandler handler;
	
	public KeyboardInput(ObjectHandler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < handler.getObjectList().size(); i++) {
			GameObject tempObject = handler.getObjectList().get(i);
			if(tempObject.getId().equals("player1")) {
				float step = 5.0f;
				float deg = 0.1f;
				AffineTransform at = new AffineTransform();
				if(key == KeyEvent.VK_W) {
					tempObject.setR(tempObject.getR().sub(new Vector(0, step)));
					at.setToTranslation(0, -step);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_S) {
					tempObject.setR(tempObject.getR().add(new Vector(0, step)));
					at.setToTranslation(0, step);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_A) {
					tempObject.setR(tempObject.getR().sub(new Vector(step, 0)));
					at.setToTranslation(-step, 0);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_D) {
					tempObject.setR(tempObject.getR().add(new Vector(step, 0)));
					at.setToTranslation(step, 0);
					tempObject.transform(at);
				}
				else if(key == KeyEvent.VK_Q) {
					Vector r = tempObject.getR();
					at.setToRotation(-deg, r.getX(), r.getY());
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_E) {
					Vector r = tempObject.getR();
					at.setToRotation(deg, r.getX(), r.getY());
					tempObject.transform(at);
				}
			}
		}
		
	}
	
}