package Core;

import java.awt.Graphics;
import java.util.LinkedList;

public class Object_Handler {
	
	
	LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	public void update() {
		GameObject tempObject;
		for(int i = 0; i< objectList.size(); i++) {
			tempObject = objectList.get(i);
			tempObject.update();
		}
	}
	public void render(Graphics g) {
		GameObject tempObject;
		for(int i = 0; i<objectList.size(); i++) {
			tempObject = objectList.get(i);
			tempObject.render(g);
		}
	}

	
	public void addObject(GameObject object) {
		this.objectList.add(object);
	}
	public void removeObject(GameObject object) {
		this.objectList.remove(object);
	}

}
