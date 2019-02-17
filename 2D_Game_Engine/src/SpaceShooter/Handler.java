package SpaceShooter;

import java.util.LinkedList;

import Core.GameObject;
import Core.ObjectHandler;
import GameGraphics.Structure;
import Physics.CollisionHandler;
import Physics.CollisionInfo;

public class Handler extends ObjectHandler {

	@Override
	public void update() {
		for(int i = 0; i < this.objectList.size(); i++) {
			this.objectList.get(i).update();
		}
		LinkedList<GameObject> toRemove = new LinkedList<GameObject>();
		LinkedList<GameObject> toAdd = new LinkedList<GameObject>();
		for(int i = 0; i < this.objectList.size()-1; i++) {
			for(int j = i+1; j < this.objectList.size(); j++) {
				GameObject o1 = this.objectList.get(i);
				GameObject o2 = this.objectList.get(j);
				CollisionInfo c12 = o1.getCollisionInfo(o2);
				if(c12 != null) {
					if(o1.id.matches("^.*asteroid$")) {
						if(o2.id.equals("player")) {
							Spacecraft s = (Spacecraft)o2;
							s.health -= 10;
							CollisionHandler.handleCollision(o1, o2, c12);
						}
						else if(o2.id.equals("missile")) {
							toRemove.add(o1);
							toRemove.add(o2);
							toAdd.add(new Explosion("explosion", new Structure(), c12.P, this));
						}
						else {
							CollisionHandler.handleCollision(o1, o2, c12);
						}
					}
					else if(o2.id.matches("^.*asteroid$")) {
						if(o1.id.equals("player")) {
							Spacecraft s = (Spacecraft)o1;
							s.health -= 10;
							CollisionHandler.handleCollision(o1, o2, c12);
						}
						else if(o1.id.equals("missile")) {
							toRemove.add(o1);
							toRemove.add(o2);
							toAdd.add(new Explosion("explosion", new Structure(), c12.P, this));
						}
						else {
							CollisionHandler.handleCollision(o1, o2, c12);
						}
					}
					else if(o1.id.equals("missile") && o2.id.matches("^.*bound$")) {
						toRemove.add(o1);
						toAdd.add(new Explosion("explosion", new Structure(), c12.P, this));
					}
					else if(o1.id.matches("^.*bound$") && o2.id.equals("missile")) {
						toRemove.add(o2);
						toAdd.add(new Explosion("explosion", new Structure(), c12.P, this));
					}
					else {
						CollisionHandler.handleCollision(o1, o2, c12);
					}
				}
			}
		}
		this.objectList.removeAll(toRemove);
		this.objectList.addAll(toAdd);
	}
	
}
