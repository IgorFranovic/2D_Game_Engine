package Controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
				if(key == KeyEvent.VK_W) {
					tempObject.setY(tempObject.getY()-5);
				} else if(key == KeyEvent.VK_S) {
					tempObject.setY(tempObject.getY()+5);
				} else if(key == KeyEvent.VK_A) {
					tempObject.setX(tempObject.getX()-5);
				} else if(key == KeyEvent.VK_D) {
					tempObject.setX(tempObject.getX()+5);
				}
			}
		}
		
	}
	
}
