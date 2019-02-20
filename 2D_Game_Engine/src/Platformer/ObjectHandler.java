package Platformer;

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
		for(GameObject object : this.objectList) {
			object.update();
		}
		for(int i = 0; i < this.objectList.size()-1; i++) {
			for(int j = i+1; j < this.objectList.size(); j++) {
				GameObject Oi = this.objectList.get(i);
				GameObject Oj = this.objectList.get(j);
				CollisionInfo Cij = Oi.getCollisionInfo(Oj);
				if(Cij != null) {
					//System.out.println(Cij.getN());
					CollisionHandler.handleCollision(Oi, Oj, Cij);
				}
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