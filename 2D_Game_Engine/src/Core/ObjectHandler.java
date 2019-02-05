package Core;

import java.awt.Graphics;
import java.util.LinkedList;

public class ObjectHandler {
	
	private LinkedList<GameObject> objectList;
	
	public ObjectHandler() {
		this.objectList = new LinkedList<GameObject>();
	}
	
	public void update() {
		for(int i = 0; i< objectList.size(); i++) {
			objectList.get(i).update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i<objectList.size(); i++) {
			objectList.get(i).render(g);
		}
	}

	public void addObject(GameObject object) {
		this.objectList.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objectList.remove(object);
	}
	
	public void clearList() {
		this.objectList.clear();
	}
	
	public LinkedList<GameObject> getObjectList(){
		return this.objectList;
	}

}
