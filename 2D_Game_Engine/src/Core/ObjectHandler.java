package Core;

import java.awt.Graphics;
import java.util.LinkedList;

import Physics.CollisionHandler;
import Physics.CollisionInfo;

public class ObjectHandler {
	
	private LinkedList<GameObject> objectList;
	
	public ObjectHandler() {
		this.objectList = new LinkedList<GameObject>();
	}
	
	public void update() {
		GameObject player = objectList.get(0);
		player.update();
		for(int i = 1; i< objectList.size(); i++) {
			GameObject object = objectList.get(i);
			object.update();
			CollisionInfo c0i = player.getCollisionInfo(object);
			if(c0i != null) {
				System.out.println("collision happened");
				CollisionHandler.handleCollision(player, object, c0i);
			}
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