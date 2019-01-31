package Controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import Core.GameObject;
import Core.ObjectHandler;

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
				AffineTransform at = new AffineTransform();
				if(key == KeyEvent.VK_W) {
					at.setToTranslation(0, -step);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_S) {
					at.setToTranslation(0, step);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_A) {
					at.setToTranslation(-step, 0);
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_D) {
					at.setToTranslation(step, 0);
					tempObject.transform(at);
				}
			}
		}
		
	}
	
}
