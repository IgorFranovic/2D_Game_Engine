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
		for(int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			if(tempObject.id.equals("player1")) {
				float step = 5.0f;
				float deg = 0.1f;
				AffineTransform at = new AffineTransform();
				if(key == KeyEvent.VK_W) {
					tempObject.r = tempObject.r.sub(new Vector(0, step));
					at.setToTranslation(0, -step);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_S) {
					tempObject.r = tempObject.r.add(new Vector(0, step));
					at.setToTranslation(0, step);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_A) {
					tempObject.r = tempObject.r.sub(new Vector(step, 0));
					at.setToTranslation(-step, 0);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_D) {
					tempObject.r = tempObject.r.add(new Vector(step, 0));
					at.setToTranslation(step, 0);
					tempObject.transform(at);
				}
				else if(key == KeyEvent.VK_Q) {
					at.setToRotation(-deg, tempObject.r.x, tempObject.r.y);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_E) {
					at.setToRotation(deg, tempObject.r.x, tempObject.r.y);
					tempObject.transform(at);
				}
			}
		}
		
	}
	
}