package Core;

import java.awt.Graphics;
import java.util.LinkedList;

public class Object_Handler {
	
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	public void tick() {
		GameObject tempObject;
		for(int i = 0; i< object.size(); i++) {
			tempObject = object.get(i);
			tempObject.update();
		}
	}
	public void render(Graphics g) {
		GameObject tempObject;
		for(int i = 0; i<object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}
